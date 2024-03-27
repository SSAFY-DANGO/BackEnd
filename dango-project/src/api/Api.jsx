import axios from 'axios';

const DEV = 'http://localhost:8080/api/';
const HOST = 'https://j10a702.p.ssafy.io/api/';

const api = axios.create({
  baseURL: HOST,
  withCredentials: true,
});
// 요청 인터셉터
api.interceptors.request.use(
  function (config) {
    // 요청 성공 직전 호출됩니다.
    // console.log(config);
    return config;
  }
  // function (error) {
  //   // 요청 에러 직전 호출됩니다.
  //   // console.log(error);
  //   if (error.response.status === 404 || error.response.status === 429) {
  //     window.location.href = "/error";
  //   }
  //   return Promise.reject(error);
  // }
);

// 응답 인터셉터
api.interceptors.response.use(
  function (response) {
    // console.log(response);
    return response;
  }
  // function (error) {
  //   // console.log(error);
  //   if (error.response.status === 404 || error.response.status === 429) {
  //     window.location.href = "/error";
  //   }
  //   return Promise.reject(error);
  // }
);

export const loginUser = async (userLoginRequest) => {
  try {
    const response = await api.post('/user/login', userLoginRequest);
    return response.data;
  } catch (error) {
    console.error('로그인 실패:', error);
    throw error;
  }
};

export const signUpUser = async (signUpRequest) => {
  try {
    const response = await api.post('/user/register/profile', signUpRequest);
    return response.data;
  } catch (error) {
    console.error('회원가입 실패:', error);
    throw error;
  }
};

export const getRefrigerator = async () => {
  try {
    const response = await api.get('/refrigerator');
    return response.data;
  } catch (error) {
    console.error('냉장고 조회 실패:', error);
    throw error;
  }
}

export const postRefrigerator = async (postRequest) => {
  try {
    const response = await api.post('/refrigerator', postRequest);
    return response.data;
  } catch (error) {
    console.error('냉장고 등록 실패:', error);
    throw error;
  }
}

export const putRefrigerator = async (putRequest) => {
  try {
    const response = await api.put('/refrigerator', putRequest);
    return response.data;
  } catch (error) {
    console.error('냉장고 수정 실패:,', error);
    throw error;
  }
}

export const deleteRefrigerator = async (deleteRequest) => {
  try {
    const response = await api.delete('/refrigerator', deleteRequest);
    return response.data;
  } catch (error) {
    console.error('냉장고 삭제 실패:', error);
    throw error;
  }
}

export default api;
