
import {useNavigate} from 'react-router-dom';
import { RiArrowGoBackFill } from "react-icons/ri";

function FridgeButton() {
    const navigate = useNavigate();

    const navigateInside = () => {
        navigate('/fridge-inside');
    };


    return (
<button onClick={navigateInside} className='bg-gray-300 w-[80vw] h-[4vh] flex items-center justify-center rounded-lg hover:bg-gray-200' ><RiArrowGoBackFill /></button>
    );
}

export default FridgeButton;