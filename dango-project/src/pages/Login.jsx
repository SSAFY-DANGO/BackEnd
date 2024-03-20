import React from 'react';
import '../styles/Landing.css';
import '../styles/Common.css';
import { useNavigate } from 'react-router-dom';
import { RiKakaoTalkFill } from 'react-icons/ri';

export default function Login() {
  const navigate = useNavigate();

  const navigateSignup = () => {
    navigate('/sign-up');
  };

  return (
    <>
      <div className=''>
        <div className='landing-background '>
          <div className='flex flex-col justify-center items-center '>
            <div className='text-5xl font-bold pb-10'>당신의 냉장고, 당고</div>
            <div className='text-3xl pb-10'>문을 열어 주세요</div>

            <div className='m-4 w-3/4 flex justify-center flex-col'>
              <div>이메일</div>
              <input
                placeholder='ssafy@gmail.com'
                className='rounded-lg p-3 w-full'
              />
            </div>
            <div className='m-4 w-3/4 flex justify-center flex-col'>
              <div>비밀번호</div>
              <input placeholder='********' className='rounded-lg p-3 w-full' />
            </div>
            <div className='w-3/4 flex justify-center'>
              <button className='long-thick-button'>
                <RiKakaoTalkFill
                  color='#3A1D1D'
                  className='inline-block align-top translate-y-1 mr-1'
                />
                <span>카카오 로그인</span>
              </button>
            </div>
            <div className='w-3/4 flex justify-center'>
              <button className='long-thick-button'>로그인</button>
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
