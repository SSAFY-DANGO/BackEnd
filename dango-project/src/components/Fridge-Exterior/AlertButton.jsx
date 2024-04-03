import React, { useState , useEffect} from 'react';
import { refrigeratorAPI } from '../../api/refrigeratorAPI';

export default function AlertButton() {
    
    useEffect(() => { 
        const interval = setInterval(() => { 
            getRefrigeratorOpen();
        }, 1000); 
        return () => clearInterval(interval); 
    }, []);

    const [isChecked, setIsChecked] = useState(false); // 기본값으로 false를 설정합니다.

    const getRefrigeratorOpen = async() => {
        try {
            const response = await refrigeratorAPI.isDoorOpen();
            console.log(response)
            const isOpen = response.data.data;
            console.log(isOpen)
            setIsChecked(isOpen); // 상태를 업데이트합니다.
        } catch(error) {
            console.log('냉장고 조회 실패', error);
        }
    }

    return (
        <>
            <label className='themeSwitcherTwo relative inline-flex cursor-pointer select-none items-center mb-5'>
                <input
                    type='checkbox'
                    checked={isChecked}
                    disabled={true} // 문자열이 아닌 boolean 값을 전달합니다.
                    className='sr-only'
                />
                <span className='label flex items-center text-sm font-medium text-black'>
                    닫힘
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
                    열림
                </span>
            </label>
        </>
    );
}
