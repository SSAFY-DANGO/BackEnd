import React from 'react';
import { useRecoilState } from 'recoil';
import { foodOldItemsState } from '../../recoil/atoms/userState';
export default function AlertModalSVG({ selectedImages }) {
  const [foodOldItems, setFoodOldItems] = useRecoilState(foodOldItemsState);

  return (
    <>
      <svg
        width='auto'
        height='580'
        viewBox='0 -52 146 217'
        fill='none'
        xmlns='http://www.w3.org/2000/svg'
      >
        <rect
          width='145'
          height='215'
          transform='translate(0.479858 0.502869)'
          fill='url(#paint0_linear_188_447)'
        />
        <text x='13' y='25' fill='white' fontFamily='SBAggroM' fontSize={20}>
          오래된 식재료
        </text>

        <rect
          x='11'
          y='35'
          width='123'
          height='100'
          fill='url(#paint1_linear_188_447)'
          fill-opacity='0.13'
        />
        {selectedImages?.map((selectedImage, index) => (
          <image
            href={selectedImage}
            key={index}
            width='30'
            height='40'
            x={15 + index * 40}
            y='40'
          />
        ))}
        <text>
          {foodOldItems.map((text, i) => (
            <tspan
              key={i}
              x={18 + i * 37}
              y='75'
              dy='1.2em'
              fill='white'
              fontSize={10}
            >
              {text}
            </tspan>
          ))}
        </text>
        <defs>
          <linearGradient
            id='paint0_linear_188_447'
            x1='72'
            y1='0'
            x2='72'
            y2='215'
            gradientUnits='userSpaceOnUse'
          >
            <stop stop-color='#6CC8C6' />
            <stop offset='0.5' stop-color='#6CC8C6' />
            <stop offset='1' stop-color='#6840C0' />
          </linearGradient>
          <linearGradient
            id='paint1_linear_188_447'
            x1='73'
            y1='50'
            x2='73'
            y2='206'
            gradientUnits='userSpaceOnUse'
          >
            <stop stop-color='#FFFBFB' />
            <stop offset='0.46' stop-color='#6CC8C6' />
            <stop offset='1' stop-color='#453F3F' />
          </linearGradient>
        </defs>
      </svg>
    </>
  );
}
