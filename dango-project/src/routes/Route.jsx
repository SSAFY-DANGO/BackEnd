import { createBrowserRouter } from "react-router-dom";
import Login from "../pages/Login";
import SignUp from "../pages/SignUp";



const routes = createBrowserRouter([
    { path: "", element: <Login></Login> }, // 첫 시작화면-> 무조건 로그인 화면으로
    { path: "/sign-up", element: <SignUp></SignUp> }, // 첫 시작화면-> 무조건 로그인 화면으로
  ]);
  export default routes;
  