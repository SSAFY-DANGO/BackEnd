// import React, { useState } from 'react';
// import { useNavigate } from 'react-router-dom';
// import CartFilledIcon from '../../public/cartFilledIcon.svg';
// import FridgeIcon from '../../public/fridgeIcon.svg';
// import MypageEmptyIcon from '../../public/mypageEmptyIcon.svg';
// import '../styles/Footer.css';
// import { BsPerson } from 'react-icons/bs';
// import { BsFillPersonFill } from 'react-icons/bs';

// export default function Footer() {
//   const navigate = useNavigate();
//   const [cartIcon, setCartIcon] = useState(false);
//   const [personIcon, setPersonIcon] = useState(<BsPerson />);

//   const handleCartIcon = () => {
//     setCartIcon(!cartIcon);
//   };

//   const handlePersonIcon = () => {
//     setPersonIcon(!personIcon);
//   };

//   const goToMypage = () => {
//     navigate('/mypage');
//   };

//   return (
//     <>
//       <footer className='fixed bottom-0 left-0 right-0 h-24 overflow-hidden border-t-2 border-solid'>
//         <div>
//           <img src={CartFilledIcon} />
//           <div>장바구니</div>
//         </div>
//         <div>
//           <img src={FridgeIcon} />
//           <div>냉장고</div>
//         </div>
//         {/* <div>
//           <BsPerson size='40' className='inline-block' />
//           <div>마이페이지</div>
//         </div> */}
//         <div>
//           <button onClick={() => goToMypage()}></button>
//           <div>마이페이지</div>
//         </div>
//       </footer>
//     </>
//   );
// }

import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import CartFilledIcon from '../../public/cartFilledIcon.svg';
import FridgeIcon from '../../public/fridgeIcon.svg';
import MypageEmptyIcon from '../../public/mypageEmptyIcon.svg';
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
