import { IoMdSearch } from "react-icons/io";

function SearchButton() {


    return (
      <div className="w-[5vw] h-[4vh] items-center flex justify-center">
        <button className="text-3xl"><IoMdSearch /></button>
      </div>
    );
  }
  
  export default SearchButton;