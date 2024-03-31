import React from 'react';
import FoodItem from './FoodItem'


function FridgeFrame({buttonText, foodItems}) {

    console.log(foodItems);
    
    return (
    <div className=' w-[80vw] h-[65vh] border-slate-500 border-4 rounded-xl flex flex-wrap overflow-y-scroll'>
        {foodItems.map((foodItem, index) => (
                <FoodItem key={index} nameText={foodItem.name} buttonText={buttonText} itemid={foodItem.id} detailbool={foodItem.exist} inputTime={foodItem.inputTime} />
            ))}      
    </div>
    );
}
export default FridgeFrame;