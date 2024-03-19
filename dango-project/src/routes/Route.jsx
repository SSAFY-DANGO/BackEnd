import { createBrowserRouter } from 'react-router-dom';
import Login from '../pages/Login';
import SignUp from '../pages/SignUp';
import ConnectFridge from '../pages/ConnectFridge';
import Cart from '../pages/Cart';
import FridegeInside from '../pages/FridegeInside';
import Mypage from '../pages/Mypage';

const routes = createBrowserRouter([
  { path: '', element: <Login></Login> }, // 첫 시작화면-> 무조건 로그인 화면으로
  { path: '/sign-up', element: <SignUp></SignUp> },
  { path: '/connect-fridge', element: <ConnectFridge></ConnectFridge> },
  { path: '/fridge-exterior', element: <FridgeExterior></FridgeExterior> },
  { path: '/cart', element: <Cart></Cart> },
  { path: '/fridge-inside', element: <FridegeInside></FridegeInside> },
  { path: '/mypage', element: <Mypage></Mypage> },
]);
export default routes;
