import React from 'react';
import '../styles/Landing.css';
import '../styles/Common.css';
import { useNavigate } from 'react-router-dom';

export default function Login() {
  const navigate = useNavigate();

  const navigateSignup = () => {
    navigate('/sign-up');
  };

  return (
    <>
      <div className='landing-background flex flex-col justify-center items-center h-screen'>
        <div className='text-7xl font-bold '>당신의 냉장고, 당고</div>
        <div className='text-3xl'>문을 열어 주세요</div>
        <div className='w-full flex-row justify-center'>
          <div>이메일 주소</div>
          <input
            className='common-input'
            placeholder=' ssafy@email.com'
          ></input>
        </div>
        <div className='w-full'>
          비밀번호
          <div></div>
          <input className='common-input' placeholder=' *********'></input>
        </div>
        <button className='long-thick-button'>구글로 로그인하기</button>
        <button className='long-thick-button'>로그인</button>
        <button className='long-thick-button' onClick={navigateSignup}>
          회원가입
        </button>
      </div>
    </>
  );
}
