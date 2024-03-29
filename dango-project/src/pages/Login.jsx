import React, { useState, useEffect } from 'react';
import '../styles/Landing.css';
import '../styles/Common.css';
import { useNavigate } from 'react-router-dom';
import { RiKakaoTalkFill } from 'react-icons/ri';
import { loginUser } from '../api/Api';

export default function Login() {
  const navigate = useNavigate();

  const [password, setPassword] = useState('');
  const [username, setUsername] = useState('');
  // type 변경 여부를 알리는 state
  const [showPswd, setShowPswd] = useState(false);

  useEffect(() => {
    if (localStorage.getItem('accessToken')) {
      navigate('/fridge-exterior');
    }
  }, []);

  const handleLogin = async () => {
    try {
      const response = await loginUser({ username, password });
      console.log('로그인 성공', response);
      navigate('/fridge-exterior');
    } catch (error) {
      console.log('로그인 실패', error);
    }
  };

  // Enter 키를 누를 때 handleLogin 함수 호출
  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      handleLogin();
    }
  };

  const navigateSignup = () => {
    navigate('/sign-up');
  };

  return (
    <>
      <div className=''>
        <div className='landing-background '>
          <div className='flex flex-col justify-center items-center '>
            <div className='text-5xl font-bold pb-10 font-title'>
              당신의 냉장고, 당고
            </div>

            <div className='text-3xl pb-10 font-aggroS'>문을 열어 주세요</div>
            <div className='m-4 w-3/4 flex justify-center flex-col'>
              <div>이메일</div>
              <input
                placeholder='ssafy@gmail.com'
                className='rounded-lg p-3 w-full'
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                onKeyDown={handleKeyDown}
              />
            </div>
            <div className='m-4 w-3/4 flex justify-center flex-col'>
              <div>비밀번호</div>
              <input
                type={showPswd ? 'text' : 'password'}
                placeholder='********'
                className='rounded-lg p-3 w-full'
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                onKeyDown={handleKeyDown}
              />
            </div>

            <div className='w-3/4 flex justify-center'>
              <button className='long-thick-button' onClick={handleLogin}>
                로그인
              </button>
            </div>
            <div className='w-3/4 flex justify-center'>
              <button
                className='long-thick-button'
                onClick={() => navigateSignup()}
              >
                회원가입
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
