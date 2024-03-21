import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import CartFilledIcon from '../assets/imgs/cartFilledIcon.svg';
import FridgeIcon from '../assets/imgs/fridgeIcon.svg';
import '../styles/Footer.css';
import { BsPerson, BsFillPersonFill } from 'react-icons/bs';

export default function Footer() {
  const navigate = useNavigate();
  const location = useLocation();
  const [cartIcon, setCartIcon] = useState(false);

  const handleCartIcon = () => {
    setCartIcon(!cartIcon);
  };

  const goToMypage = () => {
    navigate('/mypage');
  };

  return (
    <>
      <footer className='fixed bottom-0 left-0 right-0 h-24 overflow-hidden border-t-2 border-solid'>
        <div>
          <img src={CartFilledIcon} />
          <div>장바구니</div>
        </div>
        <div>
          <img src={FridgeIcon} />
          <div>냉장고</div>
        </div>
        <div>
          <button onClick={goToMypage}>
            {location.pathname === '/mypage' ? (
              <BsFillPersonFill />
            ) : (
              <BsPerson />
            )}
          </button>
          <div>마이페이지</div>
        </div>
      </footer>
    </>
  );
}
