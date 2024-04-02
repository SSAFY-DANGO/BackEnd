import axios from "axios";

const DEV = import.meta.env.VITE_DANGO_API_DEV;
const HOST = import.meta.env.VITE_DANGO_API_PROD;

export const noneTokenApi = axios.create({
  baseURL: HOST,
  withCredentials: true,
});

export const tokenApi = axios.create({
  baseURL: HOST,
  withCredentials: true,
});

// 토큰 API는 호출 전 헤더에 토큰 추가
tokenApi.interceptors.request.use((config) => {
  config.headers.Authorization = `Bearer ${
    JSON.parse(localStorage.getItem("loginUser")).accessToken
  }`;
  return config;
});
// 토큰 API 응답에 jwt 만료 flag 왔는지 체크
tokenApi.interceptors.response.use(
  // 정상 -> 걍 고
  (config) => {
    return config;
  },

  // 에러 -> jwt 만료인지 확인
  async (err) => {
    console.log("zz")
    
    const flag = err.response?.headers?.["Expired-Token"] ?? false;
    console.log("토큰만료")
    // 토큰 만료인 경우에만
    if (flag) {
      try {
        // 토큰 재발급 요청
        console.log("재발급요청")
        const res = await noneTokenApi({
          method: "get",
          url: "users/reissue",
          headers: {
            "Refresh-Token": JSON.parse(localStorage.getItem("loginUser"))
              .refreshToken,
          },
        });
        // 잘 왔으면 토큰 갱신
        if (res.data.status === 200) {
          const refresh = res.data.data.refreshToken;
          const access = res.data.data.accessToken;

          let loginUser = JSON.parse(localStorage.getItem("loginUser"));
          loginUser.accessToken = access;
          loginUser.refreshToken = refresh;

          localStorage.setItem("loginUser", JSON.stringify(loginUser));
          console.log("갱신완료")

          // 갱신한 토큰 기준으로 원래 요청 다시 보내기
          const originalRequest = err.config;
          originalRequest.headers['Authorization'] = `Bearer ${access}`;
          console.log("다시보내기")
          return tokenApi(originalRequest)

        } else {
          alert("다시 로그인 해주세요");
          localStorage.removeItem("loginUser");
          window.location.replace(import.meta.env.VITE_DANGO_URL_PROD);
        }
      } catch (reissueErr) {
        console.log("토큰재발급요청오류")
        console.log(reissueErr);
      }
    }
  }
);

export const createApiInstance = (accessToken) => {
  console.log(accessToken);
  return axios.create({
    baseURL: HOST,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      Authorization: `Bearer ${accessToken}`,
    },
  });
};

export const getRefrigerator = async (accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    console.log(api);
    const response = await api.get("/refrigerator");
    return response.data;
  } catch (error) {
    console.error("냉장고 조회 실패:", error);
    throw error;
  }
};

export const postRefrigerator = async (postRequest) => {
  const accessToken = JSON.parse(localStorage.getItem("loginUser")).accessToken;
  const api = createApiInstance(accessToken);
  try {
    const response = await api.post("/refrigerator", postRequest);
    return response.data;
  } catch (error) {
    console.error("냉장고 등록 실패:", error);
    throw error;
  }
};

export const putRefrigerator = async (putRequest) => {
  const api = createApiInstance();
  try {
    const response = await api.put("/refrigerator", putRequest);
    return response.data;
  } catch (error) {
    console.error("냉장고 수정 실패:,", error);
    throw error;
  }
};

export const deleteRefrigerator = async (deleteRequest) => {
  const api = createApiInstance();
  try {
    const response = await api.delete("/refrigerator", deleteRequest);
    return response.data;
  } catch (error) {
    console.error("냉장고 삭제 실패:", error);
    throw error;
  }
};

export const getRefrigeratorDetail = async (
  refrigeratorNickname,
  accessToken
) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.get(`/refrigerator/${refrigeratorNickname}`);
    return response.data;
  } catch (error) {
    console.error("냉장고 품목 정보 얻어오기 실패:", error);
    throw error;
  }
};

export const getRefrigeratorOld = async (refrigeratorNickname, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const user = JSON.parse(localStorage.getItem("loginUser"));

    const response = await api.get(
      `/refrigerator/old/${refrigeratorNickname.refrigeratorNickname}`
    );
    return response.data;
  } catch (error) {
    console.error("냉장고 오래된 품목 얻어오기 실패:", error);
    throw error;
  }
};

export const deleteGrocery = async (id, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.delete(`/log/${id}`);
    return response.data;
  } catch (error) {
    console.error("식재료 삭제 실패", error);
    throw error;
  }
};

export const getGroceryDetail = async (groceryId, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.get(`/log/${groceryId}`);
    return response.data;
  } catch (error) {
    console.error("식재료 정보 얻어오기 실패:", error);
    throw error;
  }
};

export const addGrocery = async (addData, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.post(`/log`, addData);
    return response.data;
  } catch (error) {
    console.error("식재료 정보 추가하기 실패:", error);
    throw error;
  }
};

export const trashList = async (nickname, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.get(`/trash/${nickname}`);

    return response.data;
  } catch (error) {
    console.error("쓰레기통 조회 실패: ", error);
    throw error;
  }
};

export const trashRecover = async (id, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.put(`/trash/${id}`);
    return response.data;
  } catch (error) {
    console.error("쓰레기 복원 실패: ", error);
    throw error;
  }
};

export const allGroceriesData = async (pageInfo, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.get(`/ingredient/search`, pageInfo);
    return response.data;
  } catch (error) {
    console.error("모든 식재료 정보 반환 실패: ", error);
    throw error;
  }
};
