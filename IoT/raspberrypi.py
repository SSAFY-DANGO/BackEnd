import cv2
import numpy as np
from serial import Serial
from kafka import KafkaProducer
import time
import os
import json
from datetime import datetime
import base64

# Refrigerator
refrigerator = 'dango'

# Door Status Flag
door = False

# Arduino - Setting Serial Port
port = '/dev/ttyACM0'
ser = Serial(port, 9600)

# Webcam
cap1 = cv2.VideoCapture(0) # Top
cap2 = cv2.VideoCapture(2) # Back

# Kafka Producer
producer = KafkaProducer(bootstrap_servers='j10a702.p.ssafy.io:49092', value_serializer=None)

# Frame No
frame = 0

while(True):
    # Distance
    distance = int(ser.readline().decode().strip())

    # Send focus
    if 7 < distance and distance < 80:
        if not door:
            door = True
            headers0 = [
                ('device', str.encode(refrigerator)),
                ('time', str.encode(datetime.now().strftime('%Y-%m-%d_%H-%M-%S-%f')))
            ]
            producer.send("dango-door", value=b'True', headers=headers0)
            print("door True")
        
        ret1, frame1 = cap1.read()
        ret2, frame2 = cap2.read()
        #if ret1:
        if ret1 and ret2:
            # JPEG 형식으로 인코딩
            _, img_encoded1 = cv2.imencode('.jpg', frame1)
            _, img_encoded2 = cv2.imencode('.jpg', frame2)
            
            # 프레임을 Base64로 인코딩
            img_base64_1 = base64.b64encode(img_encoded1)
            img_base64_2 = base64.b64encode(img_encoded2)

            # Kafka에 프레임 전송
            headers1 = [
                ('device', str.encode(refrigerator)),
                ('cam', str.encode('Top')),
                ('time', str.encode(datetime.now().strftime('%Y-%m-%d_%H-%M-%S-%f')))
            ]
            headers2 = [
                ('device', str.encode(refrigerator)),
                ('cam', str.encode('Back')),
                ('time', str.encode(datetime.now().strftime('%Y-%m-%d_%H-%M-%S-%f')))
            ]
            producer.send("images", value=img_base64_1, headers=headers1)
            producer.send("images", value=img_base64_2, headers=headers2)
            frame += 1
        else:
            if not ret1:
                print('No Camera0')
            if not ret2:
                print('No Camera2')
    elif door:
        door = False
        headers0 = [
            ('device', str.encode(refrigerator)),
            ('time', str.encode(datetime.now().strftime('%Y-%m-%d_%H-%M-%S-%f')))
        ]
        producer.send("dango-door", value=b'False', headers=headers0)
        print("door close")

cap1.release()
cap2.release()
cv2.destroyAllWindows()