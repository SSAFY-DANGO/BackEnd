import { tokenApi } from "./Api";

const END_POINT = "log";

export const logAPI = {
  // id 에 해당하는 로그 삭제
  delete(id) {
    return tokenApi({
      method: "delete",
      url: `${END_POINT}/${id}`,
    });
  },

  // 로그 상세정보 조회
  getDetail(id) {
    return tokenApi({
      method: "get",
      url: `${END_POINT}/${id}`,
    });
  },

  // 로그 추가
  register(registerRequest) {
    return tokenApi({
      method: "post",
      url: `${END_POINT}`,
      data: registerRequest,
    });
  },

  // 로그 수정
  edit(editRequest) {
    return tokenApi({
      method: "put",
      url: `${END_POINT}`,
      data: editRequest,
    });
  },
};
