
import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const loginUser = JSON.parse(localStorage.getItem('loginUser'));
const { persistAtom } = recoilPersist();

export const userState = atom({
  key: 'userState',
  default: [],
  effects_UNSTABLE: [persistAtom],
});

export const loginUserState = atom({
  key: 'loginUserState',
  default: {
    nickname: loginUser ? loginUser.nickname : '', //닉네임
    refrigeratorNickname: loginUser ? loginUser.refrigeratorNickname : '', //냉장고 닉네임
    accessToken: loginUser ? loginUser.accessToken : '', // AccessToken 조회
    refreshToken: loginUser ? loginUser.refreshToken : '', // RefreshToken 조회
  },
  effects_UNSTABLE: [persistAtom],
});

export const foodItemsState = atom({
  key: 'foodItemsState',
  default: [],
  effects_UNSTABLE: [persistAtom],
});
export const trashItemsState = atom({
  key: 'trashItemsState',
  default: [],
  effects_UNSTABLE: [persistAtom],
});
export const userInputState = atom({
  key: 'userInputState',
  default: '',
  effects_UNSTABLE: [persistAtom],
});

export const foodOldItemsState = atom({
  key: 'foodOldItemsState',
  default: [],
  effects_UNSTABLE: [persistAtom],
});