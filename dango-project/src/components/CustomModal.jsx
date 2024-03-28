
function CustomModal({ bool, onClose, mainText, subText, buttonText, placeText, customHandler}) {
   
    const handleModalClose = () => {
        onClose();
    }

    const handle = () => {
        customHandler();
    }

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
                                className='ml-3 rounded-lg p-3 w-[250px] h-[10px]'
                                />
                        </div>
                        
                        
                        <div className="flex justify-center items-center mt-[2vh] ml-60">
                        <button onClick={handle} className="hover:bg-slate-200 rounded-xl border-solid border-2 w-12 border-slate-200">{buttonText}</button>
                        <button onClick={handleModalClose} className="rounded-xl border-solid border-2 w-12 border-slate-200 ml-3 hover:bg-slate-200">닫기</button>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}

export default CustomModal;