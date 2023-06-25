import React, { useContext } from "react";
import UserContext from "../../Users/User";
import { useRef } from "react";

export default function AddContactModal({ setContacts }) {
  const { addContact, currentUser } = useContext(UserContext);
  const username = useRef(null);

  const handleAddContact = async () => {
    if (username.current.value === "") {
      alert("Please enter a username");
      return;
    }
    const result = await addContact(username.current.value);
    if (result !== "success") {
      alert(result);
      username.current.value = "";
      return;
    }
    const response = await fetch("http://localhost:5000/api/Chats", {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${currentUser.token}`,
      },
    });
    const chats = await response.json();
    setContacts(chats);
  };

  return (
    <div>
      <div
        className='modal fade'
        id='contactModal'
        data-bs-backdrop='static'
        data-bs-keyboard='false'
        aria-labelledby='contactModalLabel'
        aria-hidden='true'
      >
        <div className='modal-dialog'>
          <div className='modal-content'>
            <div className='modal-header'>
              <h5 className='modal-title' id='contactModalLabel'>
                Add contact
              </h5>
              <button
                type='button'
                className='btn-close'
                data-bs-dismiss='modal'
                aria-label='Close'
              ></button>
            </div>
            <div className='modal-body'>
              Add a new contact:
              <input
                type='text'
                className='form-control'
                placeholder='Enter contact username'
                ref={username}
              />
            </div>
            <div className='modal-footer'>
              <button type='button' className='btn btn-secondary' data-bs-dismiss='modal'>
                Cancel
              </button>
              <button
                type='button'
                style={{ background: "purple", color: "white" }}
                className='btn'
                onClick={handleAddContact}
                data-bs-dismiss='modal'
              >
                Confirm
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
