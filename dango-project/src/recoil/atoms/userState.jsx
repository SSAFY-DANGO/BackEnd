import { atom } from 'recoil';
const loginUser = JSON.parse(localStorage.getItem('loginUser'));

export const userState = atom({
  key: 'userState',
  default: [],
});

export const loginUserState = atom({
  key: 'loginUserState',
  default: {
    nickname: loginUser ? loginUser.nickname : '', //닉네임
    refrigeratorNickname: loginUser ? loginUser.refrigeratorNickname : '', //냉장고 닉네임
    accessToken: loginUser ? loginUser.accessToken : '', // AccessToken 조회
    refreshToken: loginUser ? loginUser.refreshToken : '', // RefreshToken 조회
  },
});

export const foodItemsState = atom({
  key: 'foodItemsState',
  default: [],
});

export const userInputState = atom({
  key: 'userInputState',
  default: '',
});

export const foodOldItemsState = atom({
  key: 'foodOldItemsState',
  default: [],
});
