import React, { useContext, useEffect } from "react";
import ContactItem from "./ContactItem";

export default function SidebarContact({ contactList, setCurrentContact }) {
  const ContactListDisplay = contactList.map((contact, key) => {
    return (
      <ContactItem contact={contact} key={key} setCurrentContact={setCurrentContact} />
    );
  });

  return <div className='sidebar-chats'>{ContactListDisplay}</div>;
}
