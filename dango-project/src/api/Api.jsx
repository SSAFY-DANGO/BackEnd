import axios from "axios";

const DEV = import.meta.env.VITE_DANGO_API_DEV
const HOST = import.meta.env.VITE_DANGO_API_PROD

export const noneTokenApi = axios.create({
  baseURL: HOST,
  withCredentials: true,
});

export const tokenApi = axios.create({
  baseURL: HOST,
  withCredentials: true,
});

tokenApi.interceptors.request.use((config) => {
  config.headers.Authorization = `Bearer ${
    JSON.parse(localStorage.getItem("loginUser")).accessToken
  }`;
  return config;
});

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

export const getRefrigeratorDetail = async (refrigeratorId, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.get(`/refrigerator/${refrigeratorId}`);
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
