import {deleteRefrigerator} from '../api/Api'




function CheckModal({ bool, mainText, subText, onClose}) {
   
    const handleModalClose = () => {
        onClose();
    }

    const handleDelete = async() => {
        try {
            const response = await deleteRefrigerator({});
            console.log('냉장고 삭제 성공', response)
            handleModalClose();
          } catch (error) {
            alert("삭제에 실패했습니다.")
            console.log('냉장고 삭제 실패', error);
      
          }        
    }

    return (
        <>
            {bool && (
                <div className='fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50'>
                    <div className='w-[380px] h-[160px] border-slate-500 border-4 rounded-xl bg-white pt-[4vh]'>
                        
                        <p className="flex justify-center items-center pb-[2vh]">{mainText}</p>
                        <div className="h-[3vh] pt-[2vh] flex justify-center items-center">
                            {subText}
                           
                        </div>
                        
                        
                        <div className="flex justify-center items-center mt-[2vh] ml-60">
                        <button onClick={handleDelete} className="hover:bg-slate-200 rounded-xl border-solid border-2 w-12 border-slate-200">삭제</button>
                        <button onClick={handleModalClose} className="rounded-xl border-solid border-2 w-12 border-slate-200 ml-3 hover:bg-slate-200">취소</button>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}

export default CheckModal;