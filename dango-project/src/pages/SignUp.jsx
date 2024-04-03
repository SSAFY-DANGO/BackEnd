import React, { useEffect } from "react";
import "../styles/Landing.css";
import "../styles/Common.css";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { userAPI } from "./../api/userAPI";

export default function SignUp() {
  const navigate = useNavigate();

  const [nickname, setNickname] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [emailCheck, setEmailCheck] = useState(false);

  const regexLimit = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

  useEffect(() => {
    setEmailCheck(false);
  }, [username]);

  const handleSignUp = async () => {
    if (!emailCheck) {
      alert("이메일 중복체크를 진행해주세요.");
      return;
    }
    if (!nickname) {
      alert("닉네임을 작성해주세요.");
      return;
    }
    if (!username) {
      alert("이메일을 작성해주세요.");
      return;
    }

    if (!regexLimit.test(password)) {
      console.log(password)
      alert("비밀번호는 최소 8자, 최소 하나의 특수문자 및 하나의 숫자를 포함해야합니다.");
      return;
    }

    try {
      const response = await userAPI.signUp({ username, password, nickname });
      // 회원가입 제대로 된 경우
      if (response.data.status === 200) {
        // 로그인하고 냉장고 페이지로
        const loginRes = await userAPI.login({ username, password });
        // 로그인 잘 됐으면 냉장고 등록 페이지, 아니면 첫화면으로
        if (loginRes.data.status === 200) {
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

  async function checkIfEmailDuplicated() {
    try {
      const res = await userAPI.checkEmail(username);
      if (res.data.status === 200) {
        setEmailCheck(true);
        alert("중복체크 완료");
      } else {
        alert(res.data.message);
      }
    } catch (e) {
      console.log(e);
      alert("이메일 중복 체크 실패");
    }
  }

  return (
    <>
      <div className="flex justify-center items-center h-[100vh] landing-background">
        <div
          className="text-5xl font-bold font-noto-sans-kr p-10"
          style={{ position: "absolute", top: "20px", left: "20px" }}
        >
          <button onClick={() => navigate("/")}>&lt;</button> 회원가입
        </div>
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
            <div className="flex justify-normal">
              <input
                placeholder="ssafy@gmail.com"
                className="rounded-lg p-3 w-full"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
              <button
                className={`${
                  emailCheck ? "bg-green-100" : "bg-orange-100"
                } ml-2 rounded text-xs`}
                onClick={() => {
                  if (emailCheck) {
                    alert("이미 이메일 중복 체크가 완료되었습니다.");
                  } else {
                    checkIfEmailDuplicated();
                  }
                }}
              >
                이메일 중복체크
              </button>
            </div>
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
            가입하기
          </button>
        </div>
      </div>
    </>
  );
}
