import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import FridgeFrame from '../components/Fridge-Inside/FridgeFrame';
import TrashButton from '../components/Fridge-Inside/TrashButton';
import AddButton from '../components/Fridge-Inside/AddButton';
import DropDownComponent from '../components/Fridge-Inside/DropDown';
import SearchComponent from '../components/Fridge-Inside/SearchComponent';
import SearchButton from '../components/Fridge-Inside/SearchButton';
import { useEffect, useState } from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import { foodItemsState, loginUserState } from '../recoil/atoms/userState';
import { refrigeratorAPI } from '../api/refrigeratorAPI';

function FridegeInside() {
  const loginUser = useRecoilValue(loginUserState);
  const [foodItems, setFoodItems] = useRecoilState(foodItemsState);
  const [selectedCategory, setSelectedCategory] = useState('전체');
  useEffect(() => {
    const fetchData = async () => {
      // 랜더링될 때 백에서 냉장고 품목 정보 받아오기
      try {
        console.log(loginUser.refrigeratorNickname);
        const res = await refrigeratorAPI.getItems(
          loginUser.refrigeratorNickname
        );
        console.log('냉장고 품목 조회 완료', res);
        let items = res.data.data;

        items = items.map((item) => {
          if (item.category === '과일류' || item.category === '채소류') {
            return { ...item };
          } else {
            return { ...item, category: '기타' };
          }
        });

        setFoodItems(items);
      } catch (err) {
        console.log(err);
      }
      return;
    };
    fetchData();
  }, []);

  return (
    <>
      <Header text={loginUser.nickname + '의 냉장고'} />

      <div className='flex-col ml-[9.5vw]'>
        <div className='flex mb-[1vh] mt-[1vh]'>
          <DropDownComponent onSelectCategory={setSelectedCategory} />
          <SearchComponent />
          <SearchButton />
        </div>
        <div className='mb-[2vh]'>
          <FridgeFrame
            buttonText='삭제'
            foodItems={foodItems}
            selectedCategory={selectedCategory}
          />
        </div>
        <div className='mb-[1vh] flex'>
          <AddButton />
          <div className='w-[2vw]'></div>
          <TrashButton />
        </div>
      </div>

      <Footer />
    </>
  );
}

export default FridegeInside;
