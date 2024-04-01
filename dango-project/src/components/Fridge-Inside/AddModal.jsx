import { useState, useEffect } from 'react';
import {allGroceriesData} from '../../api/Api' 
import { useRecoilValue } from 'recoil';
import { loginUserState } from '../../recoil/atoms/userState';
function AddModal({ bool, onClose, mainText, subText, buttonText, placeText, customHandler }) {

    const loginUser = useRecoilValue(loginUserState);
    const [searchText, setSearchText] = useState('');
    const [selectedOption, setSelectedOption] = useState(null);
    const [boolList, setBoolList] = useState(false);
    const [loading, setLoading] = useState(false);
    const [AllGroceries, setAllGroceries] = useState([]);
    const openDropDownList = () => {
        setBoolList(true);
    }



    const handleInputChange = (event) => {
        setSearchText(event.target.value);
    };

    const handleOptionSelect = (option) => {
        setSelectedOption(option);
        setSearchText(option.name);
        setBoolList(false);
    };

    const handleModalClose = () => {
        onClose();
    };

    const handleButtonClick = () => {
        if (selectedOption) {
            customHandler(selectedOption);
        }
    };

    const filteredOptions = AllGroceries.filter(option =>
        option.name.includes(searchText)
    );
    
    const getOptions = async (page, size) => {
        try {
            const response = await allGroceriesData({ page, size }, loginUser.accessToken);
            console.log(`전체 식재료 조회 성공 page=${page} size=${size}`, response);
            setAllGroceries(response.data.content);
        } catch (error) {
            console.log('전체 식재료 조회 실패', error);
        }
    }

    useEffect(() => {
        let page = 0;
        let size = 10;
        getOptions(page, size);

        function handleScroll() {
            if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
                console.log("새로고침")
                // const nextPage = Math.ceil(AllGroceries.length / 10);
                // console.log(nextPage);
                page = page + 1;
                getOptions(page, size);
            }
        }

        window.addEventListener('scroll', handleScroll);
        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    return (
        <>
            {bool && (
                <div className='fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50'>
                    <div className='w-[380px] h-[160px] border-slate-500 border-4 rounded-xl bg-white pt-[4vh]'>
                        <p className="flex justify-center items-center pb-[2vh]">{mainText}</p>
                        <div className="h-[3vh] pt-[2vh] flex justify-center items-center">
                            {subText}
                            <input
                                placeholder={placeText}
                                value={searchText}
                                onChange={handleInputChange}
                                onClick={setBoolList}
                                className='ml-3 rounded-lg p-3 w-[250px] h-[10px]'
                            />
                        </div>
                        {boolList && (<div className="relative">
                            <ul className="absolute w-[250px] mt-[10px] overflow-y-auto ml-[100px] max-h-[100px] bg-white border rounded-lg mt-1 shadow-lg">
                                {filteredOptions.map((option, index) => (
                                    <li
                                        key={index}
                                        onClick={() => handleOptionSelect(option)}
                                        className="px-3 py-2 cursor-pointer hover:bg-gray-100"
                                    >
                                        {option.name}
                                    </li>
                                ))}
                            </ul>
                        </div>)}
                        <div className="flex justify-center items-center mt-[2vh] ml-60">
                            <button onClick={handleButtonClick} className="hover:bg-slate-200 rounded-xl border-solid border-2 w-12 border-slate-200">
                                {buttonText}
                            </button>
                            <button onClick={handleModalClose} className="rounded-xl border-solid border-2 w-12 border-slate-200 ml-3 hover:bg-slate-200">
                                닫기
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}

export default AddModal;
