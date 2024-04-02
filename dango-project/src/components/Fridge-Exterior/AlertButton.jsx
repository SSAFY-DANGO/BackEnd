import React, { useState , useEffect} from 'react';

export default function AlertButton({isOpen}) {
    
    useEffect(() => {
        setIsChecked(isOpen)
      });

    const [isChecked, setIsChecked] = useState()

return (
    <>
      <label className='themeSwitcherTwo relative inline-flex cursor-pointer select-none items-center mb-5'>
        <input
          type='checkbox'
          checked={isChecked}
          disabled = 'true'
          className='sr-only'
        />
        <span className='label flex items-center text-sm font-medium text-black'>
          열림
        </span>
        <span
          className={`slider mx-4 flex h-8 w-[60px] items-center rounded-full p-1 duration-200 ${
            isChecked ? 'bg-[#212b36]' : 'bg-[#CCCCCE]'
          }`}
        >
          <span
            className={`dot h-6 w-6 rounded-full bg-white duration-200 ${
              isChecked ? 'translate-x-[28px]' : ''
            }`}
          ></span>
        </span>
        <span className='label flex items-center text-sm font-medium text-black'>
        닫힘
        </span>
      </label>
    </>
  );
}
