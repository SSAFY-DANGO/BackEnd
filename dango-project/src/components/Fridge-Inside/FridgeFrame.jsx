import React from 'react';
import FoodItem from './FoodItem'


function FridgeFrame({buttonText}) {

    const foodItems = [];

    for (let i = 0; i < 15; i++) {
        foodItems.push(<FoodItem nameText="아보카도" buttonText={buttonText} />);

    }
    return (
    <div className=' w-[80vw] h-[65vh] border-slate-500 border-4 rounded-xl flex flex-wrap overflow-y-scroll'>
        {foodItems}        
    </div>
    );
}
export default FridgeFrame;