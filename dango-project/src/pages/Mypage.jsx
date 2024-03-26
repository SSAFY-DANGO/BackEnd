import React from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import InfoBox from '../components/InfoBox'

function Mypage() {
  return (

    <>
      <Header text="예은이의 냉장고"/>
      <div className="flex flex-col justify-center items-center h-[70vh]">
      <InfoBox boxName="이름" content="예은"/>
      <InfoBox boxName="이메일 주소" content="dango@ssafy.com"/>
      <InfoBox boxName="냉장고 정보" content="38df239dfjkewn"/>
      </div>
      <Footer />
    </>
  );
}

export default Mypage;
