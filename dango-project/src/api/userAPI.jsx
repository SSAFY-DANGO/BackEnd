import { noneTokenApi } from "./Api";

const END_POINT = "users";

export const userAPI = {
  // 유저 로그인
  login(loginData) {
    return noneTokenApi({
      method: "post",
      url: `${END_POINT}/login`,
      data: loginData,
    });
  },

  // 유저 회원가입
  signUp(signUpdata) {
    return noneTokenApi({
      method: "post",
      url: `${END_POINT}/register/profile`,
      data: signUpdata,
    });
  },

  // 이메일 중복확인
  checkEmail(email) {
    return noneTokenApi({
      method: "get",
      url: `${END_POINT}/check`,
      params: {
        email: email,
      },
    });
  },
};
