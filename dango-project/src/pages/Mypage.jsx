import React from 'react';
import Footer from '../components/Footer';
import InfoBox from '../components/InfoBox'

function Mypage() {
  return (
    <>
      <div className="flex flex-col justify-center items-center h-[100vh]">
      <InfoBox boxName="이름" content="예은"/>
      <InfoBox boxName="이메일 주소" content="dango@ssafy.com"/>
      <InfoBox boxName="냉장고 정보" content="38df239dfjkewn"/>
      </div>
      <Footer />
    </>
  );
}

export default Mypage;
