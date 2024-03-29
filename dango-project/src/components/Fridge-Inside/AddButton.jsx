
import { MdOutlineAddShoppingCart } from "react-icons/md";
import CustomModal from '../CustomModal'
import {useState} from 'react'
import {addGrocery} from '../../api/Api'
function AddButton() {

const [isModalOpen, setIsModalOpen] = useState(false);

 
  const openModal = () => {
    setIsModalOpen(true);
  }
  const closeModal = () => {
    setIsModalOpen(false);
  }

  const addHandler = async () => {
    try {
      const response = await addGrocery({});
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
 <CustomModal bool= {isModalOpen} onClose={closeModal} mainText="식재료 추가" subText="식재료명" buttonText="추가" placeText="파프리카" customHandler={addHandler}/>
 </div>
    );
}

export default AddButton;