import React from 'react';
import FoodItem from './FoodItem'


function FridgeFrame() {

    const foodItems = [];
    for (let i = 0; i < 25; i++) {
        foodItems.push(<FoodItem nameText="아보카도" />);
    }
    return (
    <div className=' w-[80vw] h-[65vh] border-slate-500 border-4 rounded-xl flex flex-wrap'>
        {foodItems}
        
    </div>
    );
}
export default FridgeFrame;