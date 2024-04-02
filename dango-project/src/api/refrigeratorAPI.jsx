import { tokenApi } from "./Api";

const END_POINT = "refrigerator";

export const refrigeratorAPI = {
  // 냉장고 등록
  register(refrigeratorNickname) {
    return tokenApi({
      method: "post",
      url: `${END_POINT}`,
      data: refrigeratorNickname,
    });
  },

  // 내 냉장고 조회
  get() {
    return tokenApi({
      method: "get",
      url: `${END_POINT}`,
    });
  },

  // 내 냉장고 안에 들어있는 품목 모두 조회
  getItems(refrigeratorNickname) {
    return tokenApi({
      method: "get",
      url: `${END_POINT}/${refrigeratorNickname}`,
    });
  },

  // 내 냉장고 안에 들어있는 품목 중 오래된 것 조회
  getOldItems(refrigeratorNickname, time) {
    return tokenApi({
      method: "get",
      url: `${END_POINT}/${refrigeratorNickname}`,
      params: {
        t: time,
      },
    });
  },

  // 내 냉장고 삭제
  delete() {
    return tokenApi({
      method: "delete",
      url: `${END_POINT}`,
    });
  },

  // 문 열림 조회
  isDoorOpen() {
    return tokenApi({
      method: "get",
      url: `${END_POINT}/open`,
    });
  },

  // 내 냉장고 수정
  edit(refrigeratorNickname) {
    return tokenApi({
      method: "put",
      url: `${END_POINT}`,
      data: refrigeratorNickname,
    });
  },
};
