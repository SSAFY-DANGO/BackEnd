import React, { useState } from 'react';
function DropDownComponent({ onSelectCategory }) {
  const [isOpenDropDown, setIsOpenDropDown] = useState(false);
  const [selectedOption, setSelectedOption] = useState('전체');
  const [selectedFilter, setSelectedFilter] = useState(null);

  const options = ['전체', '과일류', '채소류', '기타']; // Define category options

  const optDropDown = (option) => {
    console.log(`${option}`);
    setIsOpenDropDown(false);
    setSelectedOption(option);
    onSelectCategory(option);
  };

  return (
    <div className='flex-col'>
      <button
        className='border-violet-200 border-4 border-solid rounded-lg mr-5 hover:bg-violet-200 h-[4vh] w-[10vw]'
        onClick={() => setIsOpenDropDown(!isOpenDropDown)}
      >
        {selectedOption}
      </button>
      {isOpenDropDown && (
        <div className='mr-5'>
          <ul className='absolute w-[10vw] mt-1 border-gray-200 bg-violet-300 border-2 border-solid rounded-lg'>
            {options.map((option, index) => (
              <li
                key={index}
                className='h-10 hover:bg-slate-100 rounded-lg flex z-40'
              >
                <button
                  className='w-[10vw]'
                  onClick={() => optDropDown(option)}
                >
                  {option}
                </button>
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default DropDownComponent;
