import React from "react";
import "../styles/Landing.css";
import "../styles/Common.css";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { signUpUser } from "../api/Api";
import { userAPI } from './../api/userAPI';


export default function SignUp() {
  const navigate = useNavigate();

  const [nickname, setNickname] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSignUp = async () => {
    try {
      const response = await signUpUser({ nickname, username, password });
      // 회원가입 제대로 된 경우
      if (response.status === 200) {
        // 로그인하고 냉장고 페이지로
        const loginRes = await userAPI.login({ username, password });
        // 로그인 잘 됐으면 냉장고 등록 페이지, 아니면 첫화면으로
        if (loginRes.status === 200) {
          console.log(loginRes);
          const loginUser = loginRes.data.data;
          localStorage.setItem("loginUser", JSON.stringify(loginUser));
          navigate("/connect-fridge");
        } else {
          navigate("/");
        }
      } else {
        alert(response.message);
      }
    } catch (error) {
      console.log("로그인 실패", error);
    }
  };

  const navigateLogin = () => {
    navigate("/");
  };

  return (
    <>
      <div className="flex justify-center items-center h-[100vh] landing-background">
        <div
          className="text-5xl font-bold font-noto-sans-kr p-10"
          style={{ position: "absolute", top: "20px", left: "20px" }}
        >
          {" "}
          <button onClick={navigateLogin}>&lt;</button> 회원가입{" "}
        </div>
        {/* 형식 form - requestbody임으로 submit 불가 */}
        <div className="w-full ml-[14vw]">
          <div className="m-4 w-3/4 flex justify-center flex-col mb-10">
            <div>닉네임</div>
            <input
              placeholder="김싸피"
              className="rounded-lg p-3 w-full"
              value={nickname}
              onChange={(e) => setNickname(e.target.value)}
            />
          </div>
          <div className="m-4 w-3/4 flex justify-center flex-col mb-10">
            <div>이메일 주소</div>
            <input
              placeholder="ssafy@gmail.com"
              className="rounded-lg p-3 w-full"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className="m-4 w-3/4 flex justify-center flex-col mb-10">
            <div>비밀번호</div>
            <input
              type="password"
              placeholder="**********"
              className="rounded-lg p-3 w-full"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button
            onClick={handleSignUp}
            className="w-[75%] ml-4 long-thick-button"
          >
            {" "}
            가입하기{" "}
          </button>
        </div>
      </div>
    </>
  );
}
