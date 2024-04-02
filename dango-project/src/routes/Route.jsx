import { createBrowserRouter, useNavigate } from "react-router-dom";
import Login from "../pages/Login";
import SignUp from "../pages/SignUp";
import ConnectFridge from "../pages/ConnectFridge";
import FridegeInside from "../pages/FridegeInside";
import Mypage from "../pages/Mypage";
import FridgeExterior from "../pages/FridgeExterior";
import FridgeTrashcan from "../pages/FridgeTrashcan";
import { isAuthed } from "../utils/authUtil";


const isProd = process.env.NODE_ENV === "production";
let rootUrl = "";
if (isProd) {
  rootUrl = "https://j10a106.p.ssafy.io";
} else {
  rootUrl = "http://localhost:3000";
}

// 로그인 안됐으면 첫화면으로
function routerGuard() {
  const flag = isAuthed();
  if (!flag) {
    alert("로그인 된 유저만 접근 가능합니다.");
    window.location.replace(rootUrl);
  }
  return flag;
}

const routes = createBrowserRouter([
  { path: "", element: <Login></Login> }, // 첫 시작화면-> 무조건 로그인 화면으로
  { path: "/sign-up", element: <SignUp></SignUp> },
  {
    path: "/connect-fridge",
    element: <ConnectFridge></ConnectFridge>,
    loader: routerGuard,
  },
  {
    path: "/fridge-exterior",
    element: <FridgeExterior></FridgeExterior>,
    loader: routerGuard,
  },
  {
    path: "/fridge-inside",
    element: <FridegeInside></FridegeInside>,
    loader: routerGuard,
  },
  { path: "/mypage", element: <Mypage></Mypage>, loader: routerGuard },
  {
    path: "/fridge-trashcan",
    element: <FridgeTrashcan></FridgeTrashcan>,
    loader: routerGuard,
  },
]);
export default routes;
