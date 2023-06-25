import React, { useState, useContext, useEffect, useRef } from "react";
import "../../../styles/Sidebar.css";
import AddContactModal from "./AddContactModel";
import Chat from "./Chat";
import UserContext from "../../Users/User";
import SidebarContact from "../Sidebar/SidebarContact";
import SideBarHeader from "../Sidebar/SideBarHeader";
import SideBarSearch from "../Sidebar/SideBarSearch";
import Archive from "../Sidebar/Archive";
import NotificationToast from "./NotificationToast";
export default function Sidebar({ socket }) {
  const { currentUser, logout } = useContext(UserContext);
  const [CurrentContact, setCurrentContact] = useState(null);
  const [messages, setMessages] = useState([]);
  const [contactList, setContactList] = useState([]);
  const [contacts, setContacts] = useState([]);
  const [toastContent, setToastContent] = useState({ senderName: "", message: "" });
  const currentContactRef = useRef(null);
  const currentMessagesRef = useRef(null);

  useEffect(() => {
    currentContactRef.current = CurrentContact;
    currentMessagesRef.current = messages;
  }, [CurrentContact, messages]);

  const fetchContactList = async () => {
    const res = await fetch("http://localhost:5000/api/Chats", {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${currentUser.token}`,
      },
    });
    const list = await res.json();
    setContacts(list);
    setContactList(list);
  };

  const search = async (searchText) => {
    const chats = contacts;
    setContactList(
      chats.filter((contact) => contact.user.displayName.includes(searchText))
    );
  };

  useEffect(() => {
    fetchContactList();
  }, []);

  useEffect(() => {
    socket.on("newMessage", (message) => {
      const currentContact = currentContactRef.current;
      const messages = currentMessagesRef.current;

      if (currentContact && message.id === currentContact.id) {
        setMessages([message.message, ...messages]);
      } else {
        setToastContent({
          senderName: message.message.sender.username,
          message: message.message.content,
        });
      }
      var oldUser = false;
      const updatedContacts = contacts.map((contact) => {
        if (contact.username === message.message.sender.username) {
          oldUser = true;
          return { ...contact, lastMessage: message.message };
        } else {
          return contact;
        }
      });
      if (!oldUser) {
        fetchContactList();
        return;
      }
      const sortedContacts = updatedContacts.sort((a, b) => {
        if (a.lastMessage && b.lastMessage) {
          return new Date(b.lastMessage.created) - new Date(a.lastMessage.created);
        }
      });
      setContacts(sortedContacts);
      setContactList(sortedContacts);
    });

    socket.on("forceDisconnect", () => {
      logout();
    });
  }, [CurrentContact, messages]);
  return (
    <>
      <div className='col-md-4 sidebar d-flex flex-column '>
        <SideBarHeader
          name={currentUser.displayName}
          profilePic={currentUser.profilePic}
        />
        <SideBarSearch
          doSearch={search}
          contacts={contacts}
          setContactList={setContactList}
        />
        <Archive />
        <SidebarContact contactList={contactList} setCurrentContact={setCurrentContact} />
      </div>
      <Chat
        contact={CurrentContact}
        contacts={contacts}
        setContacts={setContacts}
        messages={messages}
        setMessages={setMessages}
      />
      <AddContactModal setContacts={setContacts} />
      <NotificationToast toastContent={toastContent} />
    </>
  );
}
