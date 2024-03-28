import { createBrowserRouter } from 'react-router-dom';
import Login from '../pages/Login';
import SignUp from '../pages/SignUp';
import ConnectFridge from '../pages/ConnectFridge';
import FridegeInside from '../pages/FridegeInside';
import Mypage from '../pages/Mypage';
import FridgeExterior from '../pages/FridgeExterior';
import FridgeTrashcan from '../pages/FridgeTrashcan';

const routes = createBrowserRouter([
  { path: '', element: <Login></Login> }, // 첫 시작화면-> 무조건 로그인 화면으로
  { path: '/sign-up', element: <SignUp></SignUp> },
  { path: '/connect-fridge', element: <ConnectFridge></ConnectFridge> },
  { path: '/fridge-exterior', element: <FridgeExterior></FridgeExterior> },
  { path: '/fridge-inside', element: <FridegeInside></FridegeInside> },
  { path: '/mypage', element: <Mypage></Mypage> },
  { path: '/fridge-trashcan', element: <FridgeTrashcan></FridgeTrashcan>}
]);
export default routes;
