import React from 'react';
import AvocadoImage from '../../assets/imgs/groceries/아보카도.png'
import { useNavigate } from 'react-router-dom';

function Modal({ bool, onClose, nameText }) {
    const navigate = useNavigate();

    const handleModalClose = () => {
        onClose();
    }

    return (
        <>
            {bool && (
                <div className='fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50'>
                    <div className='w-[20vw] h-[30vh] border-slate-500 border-4 rounded-xl bg-white pt-[4vh]'>
                        
                        <p className="flex justify-center items-center pb-[2vh]">{nameText}</p>
                        <div className="h-[3vh] pt-[4vh] flex justify-center items-center"><img src={AvocadoImage} alt="logo" className="max-w-12"/></div>
                        <div className="mt-[7vh]">
                        <div className="flex justify-center">
                            3일 경과
                        </div>
                        <div className="flex justify-center">
                            들어온 시간 2024-03-07 14:38
                        </div>
                        </div>
                        <div className="flex justify-center items-center mt-[1vh]">
                        <button onClick={handleModalClose} className="rounded-xl border-solid border-2 border-black ml-72">닫기</button>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}

export default Modal;