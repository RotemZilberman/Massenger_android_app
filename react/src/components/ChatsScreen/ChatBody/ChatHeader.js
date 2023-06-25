import React from "react";
import "../../../styles/Chat.css";

export default function ChatHeader({ name, image }) {
  return (
    <div className='chat-header'>
      {/*image*/}
      {/* <img src="/images/rotemHead.png" className='profileImage' /> */}
      <img src={image} className='profileImage' alt='' />
      <span className='headerPage chat-name'>{name}</span>
      <div className='chat-header-info'>
        <span className='group-icons'>
          {/*search icon*/}
          <button className='btn-Chats'>
            <i className='bi bi-search icons' />
          </button>
          {/*video icon*/}
          <button className='btn-Chats'>
            <i className='bi bi-camera-video-fill icons' />
          </button>
          {/*phone icon*/}
          <button className='btn-Chats'>
            <i className='bi bi-telephone-fill icons' />
          </button>
          {/*more icon*/}
          <button className='btn-Chats'>
            <i className='bi bi-three-dots-vertical icons' />
          </button>
        </span>
      </div>
    </div>
  );
}
