import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import FridgeSVG from '../components/FridgeSVG';
import { useState } from 'react';
import AlertModal from '../components/Fridge-Exterior/AlertModal';

function FridgeExterior() {
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
      <div className='relative h-screen'>
        <div className='absolute  left-1/2 transform -translate-x-1/2 translate-y-1/2'>
          <FridgeSVG
            time={currentTime}
            onAlarmClick={handleAlarmClick}
            onFridgeClick={goToInside}
          />
        </div>
        <AlertModal isOpen={isModalOpen} closeModal={closeModal} />
      </div>
      <Footer />
    </>
  );
}

export default FridgeExterior;
