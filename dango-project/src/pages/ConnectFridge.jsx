import React from 'react';
import {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { postRefrigerator } from '../api/Api'

export default function ConnectFridge() {

  const navigate = useNavigate();
  const [fridgeKey, setFridgeKey] = useState('');

  const handleConnectKey = async () => {

    try {
      console.log(fridgeKey)
      const response = await postRefrigerator(fridgeKey);
      console.log('냉장고 연결 성공', response)
      navigate('/fridge-exterior')
    } catch (error) {
      alert('냉장고 연결에 실패했습니다.')
      console.log('냉장고 연결 실패', error);
    }
  }
  return (
    <>
      <div className='landing-background flex flex-col justify-center items-center h-screen'>
        <div className='text-5xl font-bold pb-10 font-title'>냉장고를 연결해주세요</div>
        <div className='m-4 w-3/4 flex justify-center flex-col'>
        <div className='mb-[1vh]'>냉장고Key</div>
            <input
              placeholder='48sdfuwjcv@#'
              className='rounded-lg p-3 w-[70vw]'
              value={fridgeKey}
              onChange={(e) => setFridgeKey(e.target.value)}
            />
           
        <button className='long-thick-button w-[70vw]' onClick={handleConnectKey}> 냉장고 Key 연결하기 </button>    
        </div>
      </div>
    </>
  );
}
