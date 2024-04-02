
import { MdOutlineAddShoppingCart } from "react-icons/md";
import AddModal from './AddModal'
import {useState, useEffect} from 'react'
import {logAPI} from '../../api/logAPI'
import { useRecoilValue } from 'recoil';
import { loginUserState } from '../../recoil/atoms/userState';
function AddButton() {
    useEffect(() => {

  }, []);

const [isModalOpen, setIsModalOpen] = useState(false);

const loginUser = useRecoilValue(loginUserState);
 
  const openModal = () => {
    setIsModalOpen(true);
  }
  const closeModal = () => {
    setIsModalOpen(false);
  }
  

  const addHandler = async (addObj) => {
    const inputData = {
      refrigeratorId: 3,
      name: addObj.name,
      category: addObj.type,
      type: 1}
    try {
      const response = await logAPI.register(inputData, loginUser.accessToken);
      console.log('식재료 추가 성공', response)
      closeModal();
    } catch (error) {
      alert("추가에 실패했습니다.")
      console.log('식재료 추가 실패', error);

    }
  }  

    return (
        <div>
<button className='bg-green-300 w-[39vw] h-[4vh] flex items-center justify-center rounded-lg hover:bg-green-200' onClick={openModal} ><MdOutlineAddShoppingCart /></button>
 <AddModal bool= {isModalOpen} onClose={closeModal} mainText="식재료 추가" subText="식재료명" buttonText="추가" placeText="파프리카" customHandler={addHandler}/>
 </div>
    );
}

export default AddButton;