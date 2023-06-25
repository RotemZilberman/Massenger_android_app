import { useEffect } from "react";
import { useState } from "react";

export default function ContactItem({ contact, setCurrentContact }) {
  const [lastMassage, setLastMassage] = useState("");
  const [lastCommunicationTime, setLastCommunicationTime] = useState("");

  const handleClick = () => {
    setCurrentContact(contact);
  };

  useEffect(() => {
    if (!contact.lastMessage) {
      return;
    }
    var Massage = contact.lastMessage.content;
    setLastMassage(Massage);
  }, [contact.lastMessage]);

  useEffect(() => {
    if (!contact.lastMessage) {
      return;
    }
    var lastMessage = contact.lastMessage;
    var date = lastMessage.created;

    if (new Date(date).toDateString() == new Date().toDateString()) {
      const hours = new Date(date).getHours();
      const minutes = new Date(date).getMinutes();
      setLastCommunicationTime(hours + ":" + minutes);
      return;
    }

    const formattedDate = new Date(date).toLocaleDateString("en-US", {
      day: "numeric",
      month: "numeric",
      year: "numeric",
    });
    const hours = new Date(date).getHours();
    const minutes = new Date(date).getMinutes();
    setLastCommunicationTime(formattedDate + " " + hours + ":" + minutes);
  }, [contact.lastMessage]);

  return (
    <div className='chat' onClick={handleClick}>
      <img src={contact.user.profilePic} className='profileImage' />
      <div className='chat-info'>
        <h5 className='chatText'>{contact.user.displayName}</h5>
        <p className='chatText' id='lastMassage'>
          {lastMassage}
        </p>
      </div>
      <div className='chat-time'>{lastCommunicationTime}</div>
    </div>
  );
}
