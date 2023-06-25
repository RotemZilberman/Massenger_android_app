import React from "react";

export default function DisplaynameReg({
  images,
  setDisplayName,
  setDisplayNameFocus,
  displayName,
}) {
  return (
    <div className='row justify-content-center'>
      <div className='line'>
        <img
          src={images("./icons8-registration-50.png")}
          alt='Avatar'
          className='col-2 type'
          style={{ width: 38, marginTop: 1, marginBottom: 1 }}
        />
        <input
          placeholder='Display Name'
          className='col-10 info'
          type='text'
          id='displayname'
          autoComplete='off'
          onChange={(e) => setDisplayName(e.target.value)}
          required
          onFocus={() => setDisplayNameFocus(true)}
          onBlur={() => setDisplayNameFocus(false)}
        />
        <span className={!displayName ? "hide" : "valid"}>
          <i class='bi bi-check-circle-fill'></i>
        </span>
      </div>
    </div>
  );
}
