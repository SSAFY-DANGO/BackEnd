import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import TrashFrame from '../components/Fridge-Inside/TrashFrame';
import FridgeButton from '../components/FridgeButton'
import {useEffect, useState} from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import { loginUserState, trashItemsState } from '../recoil/atoms/userState';
import {trashAPI} from '../api/trashAPI'
function FridgeTrashcan() {
  const loginUser = useRecoilValue(loginUserState);
  const [trashItems, setTrashItems] = useRecoilState(trashItemsState);

  useEffect(() => {
    const fetchData =  async () => {
      // 랜더링될 때 백에서 냉장고 품목 정보 받아오기
    try {
      const res = await trashAPI.get(loginUser.refrigeratorNickname);
      setTrashItems(res.data.data);
    } catch (err) { 
      alert("쓰레기통 품목 가져오기 실패!")
    }
    return;
    }
    fetchData();
  }, []);

  return (
    <>
      <Header text={'예은이의 냉장고'} />

      <div className='flex-col ml-[9.5vw]'>

        <div className="mt-5 mx-auto">
            냉장고 휴지통 : 복원하고싶은 항목을 클릭하세요
        </div>

        <div className="mb-[2vh]">
          <TrashFrame buttonText="복원"/>
          
        </div>
        <div className="mb-[1vh] flex">
          <FridgeButton/>
        </div>
       
      </div>

      <Footer />
    </>
  );
}

export default FridgeTrashcan;
