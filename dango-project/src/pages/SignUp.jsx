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
        <div> &lt;&lt; 회원가입 </div>
        <div>
          <div>이메일 주소</div>
          <input />
        </div>
        <div>
          <div>이메일 주소</div>
          <input />
        </div>
        <div>
          <div>이메일 주소</div>
          <input />
        </div>
        <button onClick={navigateConnectFridge}> 가입하기 </button>
      </div>
    </>
  );
}
