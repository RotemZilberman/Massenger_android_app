import React from "react";
import "../../styles/avatar.css";

export default function ShowAvatar({ avatar }) {
  return (
    <>
      {avatar && (
        <div className='avatar-container'>
          <img src={avatar} alt='' className='avatar-image' />
        </div>
      )
      }
    </>
  );
}
