import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import FridgeFrame from '../components/Fridge-Inside/FridgeFrame';
import FridgeButton from '../components/FridgeButton'
import {useEffect, useState} from 'react';
import { useRecoilValue } from 'recoil';
import { loginUserState } from '../recoil/atoms/userState';
import {trashList} from '../api/Api'
function FridgeTrashcan() {
  const loginUser = useRecoilValue(loginUserState);
  const [trashItems, setTrashItems] = useState([]);

  useEffect(() => {
    trashInfo();
  }, []);

  const trashInfo = async () => {
    try {
      const response = await trashList("dango", loginUser.accessToken);
      console.log('쓰레기통 품목 조회 성공', response.data);
      setTrashItems(response.data);
      
    } catch (error) {
      console.log('쓰레기통 품목 조회 실패', error);

    }
  }
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
