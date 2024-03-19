import React from 'react';
import CartFilledIcon from '../../public/cartFilledIcon.svg';
import FridgeIcon from '../../public/fridgeIcon.svg';
import MypageEmptyIcon from '../../public/mypageEmptyIcon.svg';

export default function Footer() {
  return (
    <>
      <div>Footer</div>
      <div className='flex justify-center items-center'>
        <div className=''>
          <img src={CartFilledIcon} />
          <img src={FridgeIcon} />
          <img src={MypageEmptyIcon} />
        </div>
      </div>
    </>
  );
}
