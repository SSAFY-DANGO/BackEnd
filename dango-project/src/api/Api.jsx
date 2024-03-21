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
    console.log('여기옴');
    const response = await api.post('/user/register/profile', signUpRequest);
    return response.data;
  } catch (error) {
    console.error('회원가입 실패:', error);
    throw error;
  }
};

export default api;
