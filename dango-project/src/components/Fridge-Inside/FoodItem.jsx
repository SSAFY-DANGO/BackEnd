import React from 'react';

import AvocadoImage from '../../assets/imgs/groceries/아보카도.png'
import '../../styles/Landing.css';
import '../../styles/Common.css';

function Groceries({nameText}) {
  return (
    <div className='w-[12vw] h-[10vh] flex-col justify-center items-center py-[2vh] px-[1vw]'>
        <div className="h-[3vh] flex justify-center items-center pt-[2vh]"><img src={AvocadoImage} alt="logo" className="max-w-12"/></div>
    
    <div className="text-center h-[2vh] mt-[3vh] text-sm">{nameText}</div>
  </div>
  );
}

export default Groceries;