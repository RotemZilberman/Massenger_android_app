import React from "react";

export default function UsernameReg({ userRef, setUser, validName, user, setUserFocus , images , userFocus}) {
  return (
    <div className='row justify-content-center'>
      <div className='line'>
        <img src={images("./username.png")} alt='Avatar' className='col-2 type' />
        <label htmlFor='username'></label>
        <input
          className='col-10 info'
          type='text'
          id='username'
          autoComplete='off'
          onChange={(e) => setUser(e.target.value)}
          aria-invalid={validName ? "false" : "true"}
          placeholder='Username'
          required
          ref={userRef}
          aria-describedby='uidnote'
          onFocus={() => setUserFocus(true)}
          onBlur={() => setUserFocus(false)}
        />
        <span className={validName ? "valid" : "hide"}>
          <i class='bi bi-check-circle-fill'></i>
        </span>
        {/* if empty or valid*/}
        <span className={validName || !user ? "hide" : "invalid"}>
          <i class='bi bi-x-circle-fill'></i>
        </span>
        <p
          id='uidnote'
          className={userFocus && user && !validName ? "instructions" : "offscreen"}
        >
          <i class='bi bi-info-circle-fill'></i>
          &nbsp; Username must be 4 to 24 characters.
          <br />
          Must begin with a letter.
          <br />
          Letters, numbers, underscores, hyphens allowed.
        </p>
      </div>
    </div>
  );
}
