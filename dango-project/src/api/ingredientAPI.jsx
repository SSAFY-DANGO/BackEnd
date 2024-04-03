import { tokenApi } from "./Api";

const END_POINT = "ingredient";

// pageable 형태
// {
//     "page": 0,
//     "size": 1,
//     "sort": [
//       "string"
//     ]
//   }

export const ingredientAPI = {
  // 원재료 모두 조회 page size sort direction 등 쿼리스트링으로
  getAll() {
    return tokenApi({
      method: "get",
      url: `${END_POINT}/search?page=0&size=700`
    });
  },

  // 타입(카테고리)로 원재료 조회
  getAllByType(keyword, pageable) {
    return tokenApi({
      method: "get",
      url: `${END_POINT}/search/type/${keyword}`,
      params: pageable,
    });
  },

  // 이름 기준으로 원재료 조회
  getAllByName(keyword, pageable) {
    return tokenApi({
      method: "get",
      url: `${END_POINT}/search/name/${keyword}`,
      params: pageable,
    });
  },
};
