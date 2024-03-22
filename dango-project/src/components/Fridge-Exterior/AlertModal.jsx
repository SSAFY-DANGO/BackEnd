import React from 'react';
import AlertModalSVG from './AlertModalSVG';
import { IoClose } from 'react-icons/io5';
export default function AlertModal({ isOpen, closeModal }) {
  const handleBackgroundClick = (e) => {
    // Check if the click happened outside the modal content
    if (e.target.classList.contains('modal-background')) {
      closeModal();
    }
  };

  return (
    <>
      {isOpen && (
        <div
          className='fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-35 modal-background'
          onClick={handleBackgroundClick}
        >
          <div
            className='bg-[rgba(0,0,0,0.35)] p-8 rounded-lg w-full h-full'
            onClick={closeModal}
          >
            <button onClick={closeModal}>
              <IoClose
                fill='white'
                className='absolute top-0 right-0 mt-4 mr-4'
              />
            </button>
            <AlertModalSVG />
          </div>
        </div>
      )}
    </>
  );
}
