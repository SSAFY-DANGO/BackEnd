import React from 'react';

function Header({ text }) {
  return (
    <>
      <header className='self-stretch rounded-t-none rounded-b-xl [background:linear-gradient(180deg,_rgba(202,_249,_249,_0.42),_rgba(202,_249,_249,_0.42)_0.01%,_rgba(203,_180,_202,_0.28)_69%,_rgba(203,_149,_182,_0.21))] flex flex-row items-start justify-center pt-[29px] px-5 pb-7 box-border top-[0] z-[99] sticky whitespace-nowrap max-w-full text-left text-6xl text-black1 '>
        <div className='h-[87px] w-[430px] relative rounded-t-none rounded-b-xl [background:linear-gradient(180deg,_rgba(202,_249,_249,_0.42),_rgba(202,_249,_249,_0.42)_0.01%,_rgba(203,_180,_202,_0.28)_69%,_rgba(203,_149,_182,_0.21))] hidden max-w-full' />
        <h3 className='m-0 relative text-inherit tracking-[-0.04em] font-medium font-inherit z-[1] font-aggroM'>
          {text}
        </h3>
      </header>
    </>
  );
}

export default Header;
