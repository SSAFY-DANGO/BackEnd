import React, {useEffect, useState} from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import InfoBox from '../components/InfoBox'
import { useRecoilValue } from 'recoil';
import { loginUserState } from '../recoil/atoms/userState';
import { userAPI  } from '../api/userAPI'

function Mypage() {
  const loginUser = useRecoilValue(loginUserState);
  const [email, setEmail] = useState("");
  const [nickname, setNickname] = useState("");

  const handleLogout = () => {
    localStorage.removeItem('loginUser')
    localStorage.removeItem('id')
    window.location.reload();
  }

  const getProfileInfo = () => {
    try {
      const response = userAPI.profile(loginUser.accessToken);
      setEmail(response.data.username);
      setNickname(response.data.nickname);

    } catch (error) {
      console.log("프로필 정보 조회 실패", error)
    }
  }

  useEffect(() => {
    getProfileInfo();
  })

  return (
    <>
      <Header text="예은이의 냉장고"/>
      <div className="flex flex-col justify-center items-center h-[70vh]">
      <InfoBox boxName="이름" content={nickname} modifybool={false} deletebool={false}/>
      <InfoBox boxName="이메일 주소" content={email} modifybool={false} deletebool={false}/>
      <InfoBox boxName="냉장고 정보" content={loginUser.refrigeratorNickname} modifybool={true} deletebool={true}/>
      <button className="w-24 rounded-lg border-2 border-slate-200 hover:bg-slate-100" onClick={handleLogout}>로그아웃</button>
      </div>
      <Footer />
    </>
  );
}

export default Mypage;
