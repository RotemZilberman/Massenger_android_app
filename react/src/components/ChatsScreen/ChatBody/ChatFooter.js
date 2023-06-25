import React, { useContext, useEffect, useRef } from "react";
import "../../../styles/Chat.css";
import UserContext from "../../Users/User";

export default function ChatFooter({ id, messages, setMessages, contacts, setContacts }) {
  const { addMessage, currentUser } = useContext(UserContext);

  const inputValue = useRef("");

  useEffect(() => {
    inputValue.current.focus();
  }, []);
  
  async function onClick() {
    if (inputValue.current.value === "") {
      return;
    }
    const message = inputValue.current.value;
    const newMessage = await addMessage(id, message);
    const newMessages = [newMessage, ...messages];
    setMessages(newMessages);
    const updatedContacts = contacts.map((contact) => {
      if (contact.id === id) {
        return {...contact, lastMessage: newMessage};
      }
      else {
        return contact;
      }
    });
    const sortedContacts = updatedContacts.sort((a, b) => {
      if (a.lastMessage && b.lastMessage) {
        return new Date(b.lastMessage.created) - new Date(a.lastMessage.created);
      }
    });
    setContacts(sortedContacts);
    inputValue.current.value = "";
  }
  
  const handleKeypress = e => {      //it triggers by pressing the enter key
    if (e.keyCode === 13) {
      onClick(); // does it need to wait?
    } 
  };  

  return (
    <div className='chat-footer'>
      <button id='smile'>
        <i className='bi bi-emoji-smile icons'> </i>
      </button>
      <div className='input-group' id='chat-footer' />
      <div className='input-group'>
        <input
          autoFocus
          ref={inputValue}
          id='texter'
          type='text'
          className='form-control'
          placeholder='Type a message...'
          autoComplete='off'
          onKeyUp={handleKeypress}
        />
        <button className='btn btn-primary' id='send' onClick={onClick}>
          <i className='bi bi-send-fill' />
        </button>
      </div>
    </div>
  );
}