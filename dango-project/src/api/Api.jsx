import axios from 'axios';


const DEV = 'http://localhost:8080/api/';
const HOST = 'https://j10a702.p.ssafy.io/api/';

const createApiInstance = (accessToken) => {
  console.log(accessToken);
  return axios.create({
    baseURL: HOST,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
      'Authorization': `Bearer ${accessToken}`
    }
  });
};

export const loginUser = async (userLoginRequest) => {
  const api = axios.create({
    baseURL: HOST,
    withCredentials: true,
  });
  try {
    const response = await api.post('/users/login', userLoginRequest);
    return response.data;
  } catch (error) {
    console.error('로그인 실패:', error);
    throw error;
  }
};

export const signUpUser = async (signUpRequest) => {
  const api = axios.create({
    baseURL: HOST,
    withCredentials: true,
  });
  try {
    const response = await api.post('/users/register/profile', signUpRequest);
    return response.data;
  } catch (error) {
    console.error('회원가입 실패:', error);
    throw error;
  }
};

export const getRefrigerator = async (accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    console.log(api);
    const response = await api.get('/refrigerator');
    return response.data;
  } catch (error) {
    console.error('냉장고 조회 실패:', error);
    throw error;
  }
}

export const postRefrigerator = async (postRequest) => {
  const api = createApiInstance();
  try {
    const response = await api.post('/refrigerator', postRequest);
    return response.data;
  } catch (error) {
    console.error('냉장고 등록 실패:', error);
    throw error;
  }
}

export const putRefrigerator = async (putRequest) => {
  const api = createApiInstance();
  try {
    const response = await api.put('/refrigerator', putRequest);
    return response.data;
  } catch (error) {
    console.error('냉장고 수정 실패:,', error);
    throw error;
  }
}

export const deleteRefrigerator = async (deleteRequest) => {
  const api = createApiInstance();
  try {
    const response = await api.delete('/refrigerator', deleteRequest);
    return response.data;
  } catch (error) {
    console.error('냉장고 삭제 실패:', error);
    throw error;
  }
}

export const getRefrigeratorDetail = async (refrigeratorId, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.get(`/refrigerator/${refrigeratorId}`);
    return response.data;
  } catch (error) {
    console.error("냉장고 품목 정보 얻어오기 실패:", error);
    throw error;
  }
}


export const deleteGrocery = async (id, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.delete(`/log/${id}`);
    return response.data;

  } catch (error) {
    console.error("식재료 삭제 실패", error);
    throw error;
  }
}

export const getGroceryDetail = async (groceryId, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.get(`/log/${groceryId}`);
    return response.data;
  } catch (error) {
    console.error("식재료 정보 얻어오기 실패:", error);
    throw error;
  }
}


export const addGrocery = async (addData, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.post(`/log`, addData);
    return response.data;
  } catch (error) {
    console.error("식재료 정보 추가하기 실패:", error);
    throw error;
  }
}

export const trashList = async (nickname, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.get(`/trash/${nickname}`);
    
    return response.data;
  } catch (error) {
    console.error("쓰레기통 조회 실패: ", error);
    throw error;
  }
}

export const trashRecover = async (id, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.put(`/trash/${id}`);
    return response.data;
  } catch (error) {
    console.error("쓰레기 복원 실패: ", error);
    throw error;
  }
}

export const allGroceriesData = async (pageInfo, accessToken) => {
  const api = createApiInstance(accessToken);
  try {
    const response = await api.get(`/ingredient/search`, pageInfo);
    return response.data;
  } catch (error) {
    console.error("모든 식재료 정보 반환 실패: ", error);
    throw error;
  }
}




