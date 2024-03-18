import { createBrowserRouter } from 'react-router-dom';
import Login from '../pages/Login';
import SignUp from '../pages/SignUp';
import ConnectFridge from '../pages/ConnectFridge';
import Cart from '../pages/Cart';

const routes = createBrowserRouter([
  { path: '', element: <Login></Login> }, // 첫 시작화면-> 무조건 로그인 화면으로
  { path: '/sign-up', element: <SignUp></SignUp> },
  { path: '/connect-fridge', element: <ConnectFridge></ConnectFridge> },
  { path: '/cart', element: <Cart></Cart> },
]);
export default routes;
