import { tokenApi } from "./Api";

const END_POINT = "trash";

export const trashAPI = {
  // 휴지통에 있는 식재료 조회
  async get(refrigeratorNickname) {
    return await tokenApi({
      method: "get",
      url: `${END_POINT}/${refrigeratorNickname}`,
    });
  },

  // 휴지통에 있는 식재료 복원
  restore(id) {
    return tokenApi({
      method: "put",
      url: `${END_POINT}/${id}`,
    });
  },
};
