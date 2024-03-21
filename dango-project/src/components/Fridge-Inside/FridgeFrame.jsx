import React from 'react';
import FoodItem from './FoodItem'

function FridgeFrame() {
    return (
    <div className=' w-[80vw] h-[65vh] border-slate-500 border-4 rounded-xl'>
        <FoodItem nameText="아보카도"/>
    </div>
    );
}
export default FridgeFrame;