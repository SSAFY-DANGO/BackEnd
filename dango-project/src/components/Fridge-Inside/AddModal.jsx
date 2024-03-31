import { useState } from 'react';

function AddModal({ bool, onClose, mainText, subText, buttonText, placeText, options, customHandler }) {
    const [searchText, setSearchText] = useState('');
    const [selectedOption, setSelectedOption] = useState(null);

    const handleInputChange = (event) => {
        setSearchText(event.target.value);
    };

    const handleOptionSelect = (option) => {
        setSelectedOption(option);
    };

    const handleModalClose = () => {
        onClose();
    };

    const handleButtonClick = () => {
        if (selectedOption) {
            customHandler(selectedOption);
        }
    };

    const filteredOptions = options.filter(option =>
        option.toLowerCase().includes(searchText.toLowerCase())
    );

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
                                className='ml-3 rounded-lg p-3 w-[250px] h-[10px]'
                            />
                        </div>
                        <div className="relative">
                            <ul className="absolute w-[250px] overflow-y-auto max-h-[100px] bg-white border rounded-lg mt-1 shadow-lg">
                                {filteredOptions.map((option, index) => (
                                    <li
                                        key={index}
                                        onClick={() => handleOptionSelect(option)}
                                        className="px-3 py-2 cursor-pointer hover:bg-gray-100"
                                    >
                                        {option}
                                    </li>
                                ))}
                            </ul>
                        </div>
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
