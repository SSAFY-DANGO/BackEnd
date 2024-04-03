import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { foodItemsState, userInputState } from '../../recoil/atoms/userState';

function SearchComponent({}) {
  const [userInput, setUserInput] = useRecoilState(userInputState);
  const [foodItems, setFoodItems] = useRecoilState(foodItemsState);
  const handleSubmit = (event) => {
    event.preventDefault();
  };

  const updateFilteredItems = () => {
    const filteredItems = foodItems.filter((element) =>
      element.name.includes(userInput)
    );
  };
  return (
    <form onSubmit={handleSubmit}>
      <input
        type='text'
        placeholder='검색해주세요.'
        // value={userInput}
        onChange={(e) => {
          setUserInput(e.target.value);
        }}
        className='h-[4vh] w-[64vw] p-[2px]'
      />
    </form>
  );
}

export default SearchComponent;
