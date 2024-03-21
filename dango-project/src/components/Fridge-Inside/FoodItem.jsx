import React from 'react';

import AvocadoImage from '../../assets/imgs/groceries/아보카도.png'
import '../../styles/Landing.css';
import '../../styles/Common.css';

function Groceries({nameText}) {
  return (
    <div className='w-[10vw] h-[10vh] flex-col justify-center items-center py-[2vh]'>
        <div className="h-[3vh] flex justify-center items-center pt-[2vh]"><img src={AvocadoImage} alt="logo" className="w-[3vw] h-[5vh]"/></div>
    
    <div className="text-center h-[2vh] mt-[2vh]">{nameText}</div>
  </div>
  );
}

export default Groceries;