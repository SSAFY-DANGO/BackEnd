import React, { useState } from 'react';

function SearchInput({ onSearch }) {
  const [searchTerm, setSearchTerm] = useState('');

  const handleChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSearch(searchTerm);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="검색해주세요."
        value={searchTerm}
        onChange={handleChange}
        className="h-[4vh] w-[64vw] p-[2px]"
      />
    </form>
  );
}

export default SearchInput;