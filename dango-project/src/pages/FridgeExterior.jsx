import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import FridgeSVG from '../components/FridgeSVG';
import { useState } from 'react';
import AlertModal from '../components/Fridge-Exterior/AlertModal';
import { getRefrigeratorOld } from '../api/Api';
import { useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import { loginUserState } from '../recoil/atoms/userState';

import 아보카도 from '../assets/imgs/groceries/아보카도.png';
import 감자 from '../assets/imgs/groceries/감자.png';
import 고추 from '../assets/imgs/groceries/고추.png';
import 귤 from '../assets/imgs/groceries/귤.png';
import 당근 from '../assets/imgs/groceries/당근.png';
import 대파 from '../assets/imgs/groceries/대파.png';
import 돼지고기 from '../assets/imgs/groceries/돼지고기.png';
import 딸기 from '../assets/imgs/groceries/딸기.png';
import 마늘 from '../assets/imgs/groceries/마늘.png';
import 무 from '../assets/imgs/groceries/무.png';
import 방울토마토 from '../assets/imgs/groceries/방울토마토.png';
import 배 from '../assets/imgs/groceries/배.png';
import 배추 from '../assets/imgs/groceries/배추.png';
import 버섯 from '../assets/imgs/groceries/버섯.png';
import 복숭아 from '../assets/imgs/groceries/복숭아.png';
import 사과 from '../assets/imgs/groceries/사과.png';
import 슬라이스치즈 from '../assets/imgs/groceries/슬라이스 치즈.png';
import 애호박 from '../assets/imgs/groceries/애호박.png';
import 양배추 from '../assets/imgs/groceries/양배추.png';
import 양파 from '../assets/imgs/groceries/양파.png';
import 토마토 from '../assets/imgs/groceries/토마토.png';
import 포도 from '../assets/imgs/groceries/포도.png';
import 파프리카 from '../assets/imgs/groceries/파프리카.png';
// import 랜덤 from '../assets/imgs/groceries/랜덤.png';

function FridgeExterior() {
  useEffect(() => {
    getRefrigeratorInfo();
  }, []);

  const imageMap = {
    아보카도: 아보카도,
    감자: 감자,
    고추: 고추,
    귤: 귤,
    당근: 당근,
    대파: 대파,
    돼지고기: 돼지고기,
    딸기: 딸기,
    마늘: 마늘,
    무: 무,
    방울토마토: 방울토마토,
    배: 배,
    배추: 배추,
    버섯: 버섯,
    복숭아: 복숭아,
    사과: 사과,
    슬라이스치즈: 슬라이스치즈,
    애호박: 애호박,
    양배추: 양배추,
    양파: 양파,
    토마토: 토마토,
    포도: 포도,
    파프리카: 파프리카,
    // 랜덤: 랜덤,
  };
  const [foodOldItems, setFoodOldItems] = useState([]);

  const loginUser = useRecoilValue(loginUserState);

  const { refrigeratorNickname } = useRecoilValue(loginUserState);

  const getRefrigeratorInfo = async () => {
    try {
      const response = await getRefrigeratorOld(
        refrigeratorNickname,
        loginUser.accessToken
      );
      console.log('냉장고 조회 성공', response);
      console.log(response.data.map((item) => item.name));
      setFoodOldItems(response.data.map((item) => item.name));
      localStorage.setItem('id', response.data.id);
    } catch (error) {
      console.log('냉장고 조회 실패', error);
    }
  };
  const navigate = useNavigate();
  const location = useLocation();
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const goToInside = () => {
    navigate('/fridge-inside');
  };

  const goToMypage = () => {
    navigate('/mypage');
  };

  const handleAlarmClick = () => {
    openModal();
  };
  let selectedImage = [];

  // 이미지 맵에서 해당 항목의 이미지 가져오기
  const selectedImages = foodOldItems.map((item) => {
    selectedImage = imageMap[item];
    if (!selectedImage) {
      selectedImage = imageMap['랜덤'];
    }
    return selectedImage;
  });

  console.log(selectedImages);
  console.log(selectedImage);
  // selectedImages.forEach((elem) => console.log(elem));
  // 현재 시간을 가져오는 함수
  const getCurrentTime = () => {
    const now = new Date();
    const hours = now.getHours();
    const minutes = now.getMinutes();
    // 시간과 분을 두 자리 숫자로 포맷팅
    const formattedTime = `${hours < 10 ? '0' + hours : hours}:${
      minutes < 10 ? '0' + minutes : minutes
    }`;
    return formattedTime;
  };

  // 현재 시간을 계산하여 Fridge 컴포넌트에 전달
  const currentTime = getCurrentTime();

  return (
    <>
      <Header text={'예은이의 냉장고'} />
      <div className='relative h-[590px] pt-[50px]'>
        {/* relative h-screen */}
        {/* absolute left-1/2 transform -translate-x-1/2 translate-y-1/2 */}
        <div className='absolute left-1/2 transform -translate-x-1/2 '>
          <FridgeSVG
            time={currentTime}
            onAlarmClick={handleAlarmClick}
            onFridgeClick={goToInside}
            selectedImages={selectedImages}
            // selectedImage={selectedImage}
            foodOldItems={foodOldItems}
          />
        </div>
        <AlertModal isOpen={isModalOpen} closeModal={closeModal} />
      </div>
      <Footer />
    </>
  );
}

export default FridgeExterior;
