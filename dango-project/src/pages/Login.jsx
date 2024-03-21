import React from 'react';
import '../styles/Landing.css';
import '../styles/Common.css';
// import CommonButton from '../components/CommonButton'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../api/Api';

export default function Login() {
  const navigate = useNavigate();

  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');

  const handleLogin = async () => {
    try {
      const response = await loginUser({ email, password });
      console.log('로그인 성공', response);
    } catch (error) {
      console.log('로그인 실패', error);
    }
  };

  const navigateSignup = () => {
    navigate('/sign-up');
  };

  return (
    <>
      <div className='landing-background flex flex-col justify-center items-center h-[150vh]'>
        <div className='text-7xl font-bold '>당신의 냉장고, 당고</div>
        <div className='text-3xl'>문을 열어 주세요</div>
        <div className='m-4 w-3/4 flex justify-center flex-col'>
          <div>이메일</div>
          <input
            placeholder='ssafy@gmail.com'
            className='rounded-lg p-3 w-full'
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className='m-4 w-3/4 flex justify-center flex-col'>
          <div>비밀번호</div>
          <input
            placeholder='********'
            className='rounded-lg p-3 w-full'
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {/* <div className='w-[90%] flex-row justify-center'>
          <div>이메일 주소</div>
          <input
            className='common-input'
            placeholder=' ssafy@email.com'
          ></input>
        </div> */}
        {/* <div className='w-[90%] flex-row justify-center'>
          비밀번호
          <div></div>
          <input className='common-input' placeholder=' *********'></input>
        </div> */}
        <button className='long-thick-button' onClick={navigateSignup}>
          회원가입
        </button>
        <button className='long-thick-button' onClick={handleLogin}>
          로그인
        </button>
      </div>
    </>
  );
}
