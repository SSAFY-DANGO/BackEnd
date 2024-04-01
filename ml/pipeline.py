from confluent_kafka import Consumer, KafkaError
import base64
from PIL import Image
import io
# kafka -> server

from datetime import datetime
import torch
import os
import csv

# ml process

from confluent_kafka import Producer
import json

def save_image(image_data, image_path):
    with open(image_path, "wb") as f:
        f.write(image_data)
    print(f"Image saved to {image_path}")

def create_image_folder(output_directory, image_name):
    image_folder = os.path.join(output_directory, image_name)
    if not os.path.exists(image_folder):
        os.makedirs(image_folder)
    return image_folder

def consume_images(topic_name, output_directory):
    conf = {
        'bootstrap.servers': 'j10a702.p.ssafy.io:49092',  # 카프카 브로커의 주소 설정
        'group.id': 'my_consumer_group',  # 컨슈머 그룹 ID 설정
        'auto.offset.reset': 'earliest'  # 컨슈머 옵셋 설정 (가장 초기부터 메시지 소비)
    }

    consumer = Consumer(conf)
    consumer.subscribe([topic_name])

    model = modelLoad('../model/yolov5','best.pt')
    producer = producerLoad()

    try:
        while True:
            msg = consumer.poll(timeout=1.0)

            if msg is None:
                continue
            if msg.error():
                if msg.error().code() == KafkaError._PARTITION_EOF:
                    continue
                else:
                    print(msg.error())
                    break

            header = msg.headers()

            device = header[0][1].decode("utf-8")
            cam = header[1][1].decode("utf-8")
            time = header[2][1].decode("utf-8")

            image = msg.value()

            image_data = base64.b64decode(image)

            image_path = "LowImages/{}/{}/{}.jpg".format(device,cam, time)

            # 이미지 파일로 저장
            with open(image_path, "wb") as file:
                file.write(image_data)
                print("image save!")


            detection = detectImage(model,cam,image_path)
            result = {}
            result[time] = detection
            result['type'] = cam

            print(result)

            produce_message(producer,"dango-json",detection)

    except KeyboardInterrupt:
        pass

    finally:
        consumer.close()
        #producer.close()

def consumer():
    topic_name = "images"
    ouput_directory = "../images"
    consume_images(topic_name,ouput_directory)


def modelLoad(modelPath,ptPath):
    model = torch.hub.load(modelPath,'custom',path=ptPath,source='local')
    return model 


def detectImage(model,viewType,imagePath):
    # 클래스명 영어에서 한글로 변환하는 딕셔너리
    print('dectect start!')
    class_name_to_korean = {
        'cabbage': '양배추',
        'napa cabbage': '배추',
        'pepper': '피망',
        'potato': '감자',
        'peach': '복숭아',
        'radish': '무',
        'lemon': '레몬',
        'grape': '포도',
        'mango': '망고',
        'apple': '사과',
        'carrot': '당근',
    }

    results = model(imagePath)

            # 감지 결과를 저장할 딕셔너리 초기화
    detections_dict = {}

            # 감지된 객체 수 계산
    for detection in results.xyxy[0]:
        class_id = int(detection[5])  # 클래스 ID
        class_name = model.names[class_id]  # 클래스 이름

        # 클래스명을 한글로 변환
        class_name_korean = class_name_to_korean.get(class_name, class_name)

        if class_name_korean in detections_dict:
            detections_dict[class_name_korean] += 1
        else:
            detections_dict[class_name_korean] = 1

    print(detections_dict)
    return detections_dict

def producerLoad():
    producer_conf = {
        'bootstrap.servers': 'j10a702.p.ssafy.io:49092'  # 카프카 브로커의 주소 설정
    }
    producer = Producer(producer_conf)
    return producer

def produce_message(producer,topic_name, message):
    # 카프카 프로듀서 설정
    # 메시지를 JSON 형식으로 변환
    json_message = json.dumps(message)

    try:
        # 메시지를 카프카 토픽으로 전송
        producer.produce(topic_name, value=json_message)
        producer.flush()
        print("Message sent successfully")
    except Exception as e:
        print(f"Failed to send message: {e}")
    

if __name__ == "__main__":
  consumer()