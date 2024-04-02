import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import FridgeSVG from '../components/FridgeSVG';
import { useState } from 'react';
import AlertModal from '../components/Fridge-Exterior/AlertModal';
import { getRefrigeratorOld, getRefrigeratorOpen } from '../api/Api';
import { useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import { loginUserState, userState } from '../recoil/atoms/userState';
import AlertButton from '../components/Fridge-Exterior/AlertButton';
import { refrigeratorAPI } from '../api/refrigeratorAPI';

function FridgeExterior() {
  useEffect(() => {
    getRefrigeratorInfo();
    getRefrigeratorOpen();
  }, []);
  const loginUser = useRecoilValue(loginUserState);
  
  const { nickname, refrigeratorNickname } = useRecoilValue(loginUserState);

  const getRefrigeratorInfo = async () => {
    try {
      const response = await getRefrigeratorOld(
        refrigeratorNickname, loginUser.accessToken
      );
      console.log('냉장고 조회 성공', response);
      localStorage.setItem('id', response.data.id);
    } catch (error) {
      console.log('냉장고 조회 실패', error);
    }
  };

  const getRefrigeratorOpen = async() => {
    try{
      const isOpen = await refrigeratorAPI.isDoorOpen();
      setIsRefrigeratorOpen(isOpen);
      console.log(isOpen)
    }catch(error){
      console.log('냉장고 조회 실패', error);
    }
    
  }

  
  const navigate = useNavigate();
  const location = useLocation();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isRefrigeratorOpen,setIsRefrigeratorOpen] = useState(false);

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
      
      <div className='relative h-[590px] pt-[50px]'>
        {/* relative h-screen */}
        {/* absolute left-1/2 transform -translate-x-1/2 translate-y-1/2 */}
        <div className='absolute left-1/2 transform -translate-x-1/2 '>
        <div>
        <AlertButton/>
        </div>
         <div>
         <FridgeSVG
            time={currentTime}
            onAlarmClick={handleAlarmClick}
            onFridgeClick={goToInside}
          />
         </div>  
        </div>
        <AlertModal isOpen={isModalOpen} closeModal={closeModal} />
      </div>
      <Footer />
    </>
  );
}

export default FridgeExterior;
