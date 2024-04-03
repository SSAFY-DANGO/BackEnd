
import FoodItem from './FoodItem';
import { useRecoilState } from 'recoil';
import { foodItemsState, userInputState} from '../../recoil/atoms/userState';

function FridgeFrame({ buttonText, selectedCategory }) {
  const [userInput, setUserInput] = useRecoilState(userInputState);
  const [foodItems, setFoodItems] = useRecoilState(foodItemsState);
  console.log(foodItems);
  

  return (
    <div className=' w-[80vw] h-[65vh] border-slate-500 border-4 rounded-xl flex flex-wrap overflow-y-scroll'>
      {foodItems
        ?.filter(
          (elem) =>
            selectedCategory === '전체' || elem.category === selectedCategory 
        )
        ?.filter((elem) => elem.name.includes(userInput))
        ?.map((foodItem, index) => (
          <FoodItem
            key={index}
            nameText={foodItem.name}
            buttonText={buttonText}
            itemid={foodItem.id}
            detailbool={foodItem.exist}
            inputTime={foodItem.inputTime}
          />
        ))}
    </div>
  );
}
export default FridgeFrame;
