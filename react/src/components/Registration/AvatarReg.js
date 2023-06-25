import React, { useRef, useState } from "react";

export default function AvatarReg({ images, onAvatarChange }) {
  const avatarRef = useRef(null);

  const handleAvatarChange = (e) => {
    var reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);
    reader.onload = () => {
      onAvatarChange(reader.result);
    };
    reader.onerror = (erorr) => {
      console.log(erorr);
    };
  };

  return (
    <div className='row justify-content-center'>
      <div className='line'>
        <img
          src={images("./icons8-picture-64.png")}
          alt='Avatar'
          className='col-2 type'
        />
        <input
          onChange={handleAvatarChange}
          type='file'
          accept='image/*'
          required
          id='actual-btn'
          style={{ display: "none" }}
          ref={avatarRef}
        />
        <label htmlFor='actual-btn' className='col-10 info'>
          Choose file
        </label>
      </div>
    </div>
  );
}
