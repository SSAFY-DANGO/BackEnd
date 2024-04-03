import { FaRegTrashCan } from "react-icons/fa6";
import {useNavigate} from 'react-router-dom';

function TrashButton() {
    const navigate = useNavigate();

    const navigateTrashCan = () => {
        navigate('/fridge-trashcan');
    };


    return (
<button onClick={navigateTrashCan} className='bg-gray-300 w-[39vw] h-[4vh] flex items-center justify-center rounded-lg hover:bg-gray-200' ><FaRegTrashCan /></button>
    );
}

export default TrashButton;