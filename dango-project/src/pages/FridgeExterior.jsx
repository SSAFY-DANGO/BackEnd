import React from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Header from "../components/Header";
import Footer from "../components/Footer";
import FridgeSVG from "../components/FridgeSVG";
import { useState } from "react";
import AlertModal from "../components/Fridge-Exterior/AlertModal";
import { refrigeratorAPI } from "../api/refrigeratorAPI";
import { useEffect } from "react";
import { loginUserState } from "../recoil/atoms/userState";
import { foodOldItemsState } from "../recoil/atoms/userState";
import { useRecoilValue, useRecoilState } from "recoil";

import 아보카도 from "../assets/imgs/groceries/아보카도.png";
import 감자 from "../assets/imgs/groceries/감자.png";
import 고추 from "../assets/imgs/groceries/고추.png";
import 귤 from "../assets/imgs/groceries/귤.png";
import 당근 from "../assets/imgs/groceries/당근.png";
import 대파 from "../assets/imgs/groceries/대파.png";
import 돼지고기 from "../assets/imgs/groceries/돼지고기.png";
import 딸기 from "../assets/imgs/groceries/딸기.png";
import 마늘 from "../assets/imgs/groceries/마늘.png";
import 무 from "../assets/imgs/groceries/무.png";
import 방울토마토 from "../assets/imgs/groceries/방울토마토.png";
import 배 from "../assets/imgs/groceries/배.png";
import 배추 from "../assets/imgs/groceries/배추.png";
import 버섯 from "../assets/imgs/groceries/버섯.png";
import 복숭아 from "../assets/imgs/groceries/복숭아.png";
import 사과 from "../assets/imgs/groceries/사과.png";
import 슬라이스치즈 from "../assets/imgs/groceries/슬라이스 치즈.png";
import 애호박 from "../assets/imgs/groceries/애호박.png";
import 양배추 from "../assets/imgs/groceries/양배추.png";
import 양파 from "../assets/imgs/groceries/양파.png";
import 토마토 from "../assets/imgs/groceries/토마토.png";
import 포도 from "../assets/imgs/groceries/포도.png";
import 파프리카 from "../assets/imgs/groceries/파프리카.png";
import 고구마 from "../assets/imgs/groceries/고구마.png";
import 망고 from "../assets/imgs/groceries/망고.png";
import 멜론 from "../assets/imgs/groceries/멜론.png";
import 블루베리 from "../assets/imgs/groceries/블루베리.png";
import 수박 from "../assets/imgs/groceries/수박.png";
import 랜덤 from "../assets/imgs/mark_question.png";
import AlertButton from "../components/Fridge-Exterior/AlertButton";

function FridgeExterior() {
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
    고구마: 고구마,
    망고: 망고,
    멜론: 멜론,
    블루베리: 블루베리,
    수박: 수박,
    랜덤: 랜덤,
  };

  const loginUser = useRecoilValue(loginUserState);
  const [foodOldItems, setFoodOldItems] = useRecoilState(foodOldItemsState);
  const [isRefrigeratorOpen, setIsRefrigeratorOpen] = useState(false);
  // const { nickname, refrigeratorNickname } = useRecoilValue(loginUserState);

  useEffect(() => {
    const fetchOldFood = async () => {
      // 랜더링될 때 백에서 냉장고 오래된 품목 받아오기
      try {
        const res = await refrigeratorAPI.getOldItems(
          loginUser.refrigeratorNickname
        );
        setFoodOldItems(res.data.data.map((item) => item.name));
        localStorage.setItem("id", res.data.id);
      } catch (err) {
      }
      return;
    };

    const fetchOpenData = async () => {
      try {
        const response = await refrigeratorAPI.isDoorOpen();
        const isOpen = response.data.data;
        setIsRefrigeratorOpen(isOpen);
      } catch (error) {
      }
    };

    fetchOldFood();
    fetchOpenData();
  }, []);

  const navigate = useNavigate();
  const location = useLocation();
  const [isModalOpen, setIsModalOpen] = useState(false);
  // const [isRefrigeratorOpen, setIsRefrigeratorOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const goToInside = () => {
    navigate("/fridge-inside");
  };

  const goToMypage = () => {
    navigate("/mypage");
  };

  const handleAlarmClick = () => {
    openModal();
  };
  let selectedImage = [];

  // 이미지 맵에서 해당 항목의 이미지 가져오기
  const selectedImages = foodOldItems.map((item) => {
    selectedImage = imageMap[item];
    if (!selectedImage) {
      selectedImage = imageMap["랜덤"];
    }
    return selectedImage;
  });

  // 현재 시간을 가져오는 함수
  const getCurrentTime = () => {
    const now = new Date();
    const hours = now.getHours();
    const minutes = now.getMinutes();
    // 시간과 분을 두 자리 숫자로 포맷팅
    const formattedTime = `${hours < 10 ? "0" + hours : hours}:${
      minutes < 10 ? "0" + minutes : minutes
    }`;
    return formattedTime;
  };

  // 현재 시간을 계산하여 Fridge 컴포넌트에 전달
  const currentTime = getCurrentTime();

  return (
    <>
      <Header text={"예은이의 냉장고"} />

      <div className="relative h-[590px] pt-[50px]">
        {/* relative h-screen */}
        {/* absolute left-1/2 transform -translate-x-1/2 translate-y-1/2 */}
        <div className="absolute left-1/2 transform -translate-x-1/2 ">
          <AlertButton />
          <div>
            <FridgeSVG
              time={currentTime}
              onAlarmClick={handleAlarmClick}
              onFridgeClick={goToInside}
              selectedImages={selectedImages}
              // selectedImage={selectedImage}
              // foodOldItems={foodOldItems}
            />
          </div>
        </div>
        <AlertModal
          isOpen={isModalOpen}
          closeModal={closeModal}
          selectedImages={selectedImages}
          // foodOldItems={foodOldItems}
        />
      </div>
      <Footer />
    </>
  );
}

export default FridgeExterior;
