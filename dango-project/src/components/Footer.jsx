import React from 'react';
import CartFilledIcon from '../../public/cartFilledIcon.svg';
import FridgeIcon from '../../public/fridgeIcon.svg';
import MypageEmptyIcon from '../../public/mypageEmptyIcon.svg';
import '../styles/Footer.css';

export default function Footer() {
  return (
    <>
      <div>Footer</div>
      <footer className='fixed bottom-0 left-0 right-0 h-11 overflow-hidden border-t-2 border-solid'>
        <div>
          <img src={CartFilledIcon} />
        </div>
        <div>
          <img src={FridgeIcon} />
        </div>
        <div>
          <img src={MypageEmptyIcon} />
        </div>
      </footer>
    </>
  );
}
