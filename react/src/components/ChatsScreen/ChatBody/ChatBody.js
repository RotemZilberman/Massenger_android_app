import React, { useContext, useEffect, useRef } from "react";
import "../../../styles/Chat.css";
import UserContext from "../../Users/User";

export default function ChatBody({ messages }) {
  const { currentUser } = useContext(UserContext);

  function getTime(date) {
    const hours = new Date(date).getHours();
    const minutes = new Date(date).getMinutes();

    if (new Date(date).toDateString() === new Date().toDateString()) {
      return hours + ":" + minutes;
    }

    const formattedDate = new Date(date).toLocaleDateString("en-US", {
      day: "numeric",
      month: "numeric",
      year: "numeric",
    });
    return hours + ":" + minutes + ", " + formattedDate;
  }

  function setType(message, key) {
    if (message.sender.username === currentUser.username) {
      return (
        <div key={key} className='chat-message sender'>
          <span style={{ marginRight: "5px" }}>{message.content}</span>
          <span className='chat-time'>{getTime(message.created)}</span>
          <i className='bi bi-check-all' />
        </div>
      );
    }
    return (
      <div key={key} className='chat-message receiver'>
        <span style={{ marginRight: "5px" }}>{message.content}</span>
        <span className='chat-time'>{getTime(message.created)}</span>
      </div>
    );
  }

  const containerRef = useRef(null);

  useEffect(() => {
    const container = containerRef.current;
    container.scrollTop = container.scrollHeight;

    // Observe mutations in the container to automatically scroll to the bottom
    const observer = new MutationObserver(() => {
      container.scrollTop = container.scrollHeight;
    });

    observer.observe(container, { childList: true });

    // Clean up the observer when the component unmounts
    return () => {
      observer.disconnect();
    };
  }, [messages]);

  // Generate the list of chat message components with unique key props
  var messagesList =
    messages &&
    messages
      .slice()
      .reverse()
      .map((message) => setType(message, message.id));

  return (
    <div id='chatBody' className='chat-body' ref={containerRef}>
      {messagesList}
    </div>
  );
}
