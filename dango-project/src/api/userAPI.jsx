import { api } from "./Api";

export const userAPI = {
  login(loginData) {
    return api({
      method: "post",
      url: "users/login",
      data: loginData,
    });
  },
};
