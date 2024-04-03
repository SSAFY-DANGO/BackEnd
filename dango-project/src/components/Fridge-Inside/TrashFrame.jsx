
import FoodItem from './FoodItem';
import { useRecoilState } from 'recoil';
import {userInputState, trashItemsState } from '../../recoil/atoms/userState';

function TrashFrame({ buttonText }) {
  const [userInput, setUserInput] = useRecoilState(userInputState);

  const [trashItems, setTrashItems] = useRecoilState(trashItemsState);
  console.log(trashItems);

  

  return (
    <div className=' w-[80vw] h-[65vh] border-slate-500 border-4 rounded-xl flex flex-wrap overflow-y-scroll'>
      {trashItems
        .filter((elem) => elem.name.includes(userInput))
        .map((foodItem, index) => (
          <FoodItem
            key={index}
            nameText={foodItem.name}
            buttonText={buttonText}
            itemid={foodItem.id}
            detailbool={foodItem.exist}
            inputTime={foodItem.inputTime}
            style={{ marginBottom: "5px" }}
          />
        ))}
    </div>
  );
}
export default TrashFrame;
