import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import FridgeFrame from '../components/Fridge-Inside/FridgeFrame';
import FridgeButton from '../components/FridgeButton'
import {useEffect, useState} from 'react';
import { useRecoilValue } from 'recoil';
import { loginUserState } from '../recoil/atoms/userState';
import {trashAPI} from '../api/trashAPI'
function FridgeTrashcan() {
  const loginUser = useRecoilValue(loginUserState);
  const [trashItems, setTrashItems] = useState([]);

  useEffect(() => {
    const fetchData =  async () => {
      // 랜더링될 때 백에서 냉장고 품목 정보 받아오기
    try {
      const res = await trashAPI.get(loginUser.refrigeratorNickname);
      console.log('쓰레기통 품목 조회 성공', res.data);
      setTrashItems(res.data);
    } catch (err) { 
      console.log(err)
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
            예은이의 냉장고 휴지통 : 복원하고싶은 항목을 클릭하세요
        </div>

        <div className="mb-[2vh]">
          <FridgeFrame buttonText="복원" foodItems={trashItems}/>
          
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
