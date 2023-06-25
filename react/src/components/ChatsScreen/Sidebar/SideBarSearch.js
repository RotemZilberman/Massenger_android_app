import React, { useRef, useEffect } from "react";
import "../../../styles/index.css";
import "../../../styles/Sidebar.css";
import { useContext } from "react";
import UserContext from "../../Users/User";

export default function SideBarSearch({ doSearch, contacts, setContactList }) {
  const { currentUser } = useContext(UserContext);
  const searchBox = useRef();

  const handleChange = () => {
    doSearch(searchBox.current.value);
  };

  useEffect(() => {
    const chats = contacts;
    setContactList(
      chats.filter((contact) =>
        contact.user.displayName.includes(searchBox.current?.value)
      )
    );
  }, [searchBox.current?.value, contacts]);

  const deleteSearch = () => {
    searchBox.current.value = "";
    doSearch("");
  };

  return (
    <div className='sidebar-search'>
      <div className='input-group' id='search_input'>
        <button
          className='btn-Chats'
          type='button'
          data-bs-toggle='modal'
          data-bs-target='#contactModal'
        >
          <i className='bi bi-person-fill-add icons' />
        </button>
        <div className='d-flex flex-grow-1' id='searchGroup'>
          <input
            type='text'
            placeholder='Search...'
            ref={searchBox}
            id='searchBox'
            onChange={handleChange}
          />
          <button onClick={deleteSearch} id='cancelSearch'>
            <i className='bi bi-x-lg' />
          </button>
        </div>
      </div>
    </div>
  );
}
