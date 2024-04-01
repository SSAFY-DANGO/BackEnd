import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { refrigeratorAPI } from "../api/refrigeratorAPI";

export default function ConnectFridge() {
  const navigate = useNavigate();
  const [fridgeKey, setFridgeKey] = useState("");

  const handleConnectKey = async () => {
    if (!fridgeKey) {
      alert("냉장고 키를 입력하세요.");
      return;
    }
    try {
      const res = await refrigeratorAPI.register(fridgeKey);
      if (res.data.status === 200) {
        alert("냉장고 연결 성공");
        navigate("/fridge-exterior");
      } else {
        alert(res.data.message);
      }
    } catch (error) {
      alert("냉장고 연결 요청이 실패했습니다.");
      console.log("냉장고 연결 요청 실패", error);
    }
  };
  return (
    <>
      <div className="landing-background flex flex-col justify-center items-center h-screen">
        <div className="text-5xl font-bold pb-10 font-title">
          냉장고를 연결해주세요
        </div>
        <div className="m-4 w-3/4 flex justify-center flex-col">
          <div className="mb-[1vh]">냉장고Key</div>
          <input
            placeholder="48sdfuwjcv@#"
            className="rounded-lg p-3 w-[70vw]"
            value={fridgeKey}
            onChange={(e) => setFridgeKey(e.target.value)}
          />

          <button
            className="long-thick-button w-[70vw]"
            onClick={handleConnectKey}
          >
            냉장고 Key 연결하기
          </button>
        </div>
      </div>
    </>
  );
}
