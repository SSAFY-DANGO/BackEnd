import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { foodItemsState } from '../../recoil/atoms/userState';

function SearchComponent({ onSearch }) {
  const [searchTerm, setSearchTerm] = useState('');
  const [foodItems, setFoodItems] = useRecoilState(foodItemsState);

  const handleChange = (e) => {
    console.log(e.target.value);
    setSearchTerm(e.target.value);

    updateFilteredItems(searchTerm);
  };
  console.log(searchTerm);
  const handleSubmit = (event) => {
    event.preventDefault();
    // onSearch(searchTerm);
  };
  // const filterItems = foodItems.filter((value) => {
  //   return value.toLowerCase().includes(searchTerm.toLowerCase());
  // });
  const updateFilteredItems = (searchTerm) => {
    const filteredItems = foodItems.filter((element) =>
      // console.log(value.name.includes(searchTerm))
      // console.log(value.name)
      //element.toString().includes(searchTerm)
      element.name.includes(searchTerm)
    );
    setFoodItems(filteredItems);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type='text'
        placeholder='검색해주세요.'
        value={searchTerm}
        onChange={handleChange}
        className='h-[4vh] w-[64vw] p-[2px]'
      />
    </form>
  );
}

export default SearchComponent;
