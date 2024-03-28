import React, {useState} from 'react';
import Modal from './Modal'

import 아보카도 from '../../assets/imgs/groceries/아보카도.png'
import 감자 from '../../assets/imgs/groceries/감자.png'
import 고추 from '../../assets/imgs/groceries/고추.png'
import 귤 from '../../assets/imgs/groceries/귤.png'
import 당근 from '../../assets/imgs/groceries/당근.png'
import 대파 from '../../assets/imgs/groceries/대파.png'
import 돼지고기 from '../../assets/imgs/groceries/돼지고기.png'
import 딸기 from '../../assets/imgs/groceries/딸기.png'
import 마늘 from '../../assets/imgs/groceries/마늘.png'
import 무 from '../../assets/imgs/groceries/무.png'
import 방울토마토 from '../../assets/imgs/groceries/방울토마토.png'
import 배 from '../../assets/imgs/groceries/배.png'
import 배추 from '../../assets/imgs/groceries/배추.png'
import 버섯 from '../../assets/imgs/groceries/버섯.png'
import 복숭아 from '../../assets/imgs/groceries/복숭아.png'
import 사과 from '../../assets/imgs/groceries/사과.png'
import 슬라이스치즈 from '../../assets/imgs/groceries/슬라이스 치즈.png'
import 애호박 from '../../assets/imgs/groceries/애호박.png'
import 양배추 from '../../assets/imgs/groceries/양배추.png'
import 양파 from '../../assets/imgs/groceries/양파.png'
import 토마토 from '../../assets/imgs/groceries/토마토.png'
import 포도 from '../../assets/imgs/groceries/포도.png'
import 파프리카 from '../../assets/imgs/groceries/파프리카.png'
import { getGroceryDetail } from '../../api/Api'
import '../../styles/Landing.css';
import '../../styles/Common.css';

const imageMap = {
  '아보카도': 아보카도,
  '감자': 감자,
  '고추': 고추,
  '귤': 귤,
  '당근': 당근,
  '대파': 대파,
  '돼지고기': 돼지고기,
  '딸기': 딸기,
  '마늘': 마늘,
  '무': 무,
  '방울토마토': 방울토마토,
  '배': 배,
  '배추': 배추,
  '버섯': 버섯,
  '복숭아': 복숭아,
  '사과': 사과,
  '슬라이스치즈': 슬라이스치즈,
  '애호박': 애호박,
  '양배추': 양배추,
  '양파': 양파,
  '토마토': 토마토,
  '포도': 포도,
  '파프리카': 파프리카
}

function Groceries({nameText, buttonText}) {
    const [isModalOpen, setIsModalOpen] = useState(false); 
    const openModal = () => {
      setIsModalOpen(true);
    }

    const openGroceryDetail = async () => {
      try {
        const response = await getGroceryDetail();
        console.log('식재료 조회 성공', response)
        openModal();
        
      } catch (error) {
        console.log('식재료 조회 실패', error);
        openModal();
  
      }
    }

    const closeModal = () => {
      setIsModalOpen(false);
    }
  const selectedImage = imageMap[nameText];

  return (
    <div>
    <div className='w-[18vw] h-[15vh] flex-col justify-center items-center py-[3vh] px-[1vw]' onClick={openGroceryDetail}>
        <div className="h-[3vh] flex justify-center items-center pt-[2vh]"><img src={selectedImage} alt="logo" className="max-w-12"/></div>
    <div className="text-center h-[2vh] mt-[3vh] text-sm">{nameText}</div>
  </div>
  <Modal bool= {isModalOpen} onClose={closeModal} nameText = {nameText} buttonText = {buttonText}/>
  </div>
  );
}

export default Groceries;