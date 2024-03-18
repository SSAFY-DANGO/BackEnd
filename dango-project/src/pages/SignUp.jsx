import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function SignUp() {
  const navigate = useNavigate();

  const navigateConnectFridge = () => {
    navigate('/connect-fridge');
  };
  return (
    <>
      <div className='landing-background flex flex-col justify-center items-center h-screen'>
        <div
          className='text-5xl font-bold font-noto-sans-kr p-10'
          style={{ position: 'absolute', top: '20px', left: '20px' }}
        >
          {' '}
          &lt; 회원가입{' '}
        </div>
        <form className='text-3xl'>
          <div className='p-10'>
            <div>이메일 주소</div>
            <input />
          </div>

          <button onClick={navigateConnectFridge}> 가입하기 </button>
        </form>
      </div>
    </>
  );
}
