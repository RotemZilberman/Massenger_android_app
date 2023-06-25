import React, { useState } from "react";

export default function PasswordReg({
  pwd,
  setPwd,
  validPwd,
  setPwdFocus,
  pwdFocus,
  images,
}) {
  const [showPwd, setShowPwd] = useState(false);

  const togglePwdVisibility = () => {
    setShowPwd(!showPwd);
  };

  return (
    <>
      <div className='row justify-content-center'>
        <div className='line'>
          <img src={images("./icons8-lock-67.png")} alt='Avatar' className='col-2 type' />
          <input
            className='col-10 info'
            type={showPwd ? "text" : "password"}
            id='password'
            placeholder='Password'
            onChange={(e) => setPwd(e.target.value)}
            value={pwd}
            required
            aria-invalid={validPwd ? "false" : "true"}
            aria-describedby='pwdnote'
            onFocus={() => setPwdFocus(true)}
            onBlur={() => setPwdFocus(false)}
          />
          <label htmlFor='password'>
            <span className={validPwd ? "valid" : "hide"}>
              <i className='bi bi-check-circle-fill'></i>
            </span>
            {/* if empty or valid*/}
            <span className={validPwd || !pwd ? "hide" : "invalid"}>
              <i className='bi bi-x-circle-fill'></i>
            </span>
          </label>
          &nbsp;&nbsp;
          <button
            type='button'
            className='toggle-password-visibility-btn'
            onClick={togglePwdVisibility}
          >
            {/* if showPwd is true, show slash icon */}
            <i className={`bi bi-eye${showPwd ? "-slash" : ""}`} id='eye'></i>
          </button>{" "}
          <p
            id='pwdnote'
            className={pwdFocus && !validPwd ? "instructions" : "offscreen"}
          >
            <i className='bi bi-info-circle-fill'></i> &nbsp; Password must be 8 to 24
            characters.
            <br />
            Must include uppercase and lowercase letters, a number and a special
            character.
            <br />
            Allowed special characters: <span aria-label='exclamation mark'>!</span>
            <span aria-label='at symbol'>@</span> <span aria-label='hashtag'>#</span>
            <span aria-label='dollar sign'>$</span> <span aria-label='percent'>%</span>
          </p>
        </div>
      </div>
    </>
  );
}
