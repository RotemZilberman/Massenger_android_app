import React, { useState, useEffect, useContext } from "react";
import "../../../styles/Chat.css";
import ChatHeader from "./ChatHeader";
import ChatBody from "./ChatBody";
import ChatFooter from "./ChatFooter";
import UserContext from "../../Users/User";

export default function Chat({ contact, messages, setMessages, contacts, setContacts }) {
  const { currentUser } = useContext(UserContext);

  useEffect(() => {
    if (contact) {
      startMessages();
    }
  }, [contact]);

  useEffect(() => {}, [messages]);

  async function startMessages() {
    try {
      const response = await fetch(
        `http://localhost:5000/api/Chats/${contact.id}/Messages`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${currentUser.token}`,
          },
        }
      );
      const messages = await response.json();
      setMessages(messages);
    } catch (error) {
      console.error("Error fetching messages:", error);
    }
  }

  return (
    <div className='col-md-8 chat-container d-flex flex-column'>
      <div className='chat-container d-flex flex-column'>
        <ChatHeader
          name={contact ? contact.user.displayName : "Chat"}
          image={contact ? contact.user.profilePic : "/images/githubpurplecat.png"}
        />
        <ChatBody messages={messages} />
        {contact && (
          <ChatFooter
            id={contact.id}
            messages={messages}
            setMessages={setMessages}
            contacts={contacts}
            setContacts={setContacts}
          />
        )}
      </div>
    </div>
  );
}
