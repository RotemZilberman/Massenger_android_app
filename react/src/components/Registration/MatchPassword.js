import React from "react";
import { useState } from "react";
export default function MatchPassword({
  images,
  validMatch,
  matchPwd,
  setMatchPwd,
  setMatchFocus,
  matchFocus,
}) {
  const [showPwd, setShowPwd] = useState(false);

  return (
    <div className='row justify-content-center'>
      <div className='line'>
        <img src={images("./icons8-lock-67.png")} alt='Avatar' className='col-2 type' />
        <input
          className='col-10 info'
          type={showPwd ? "text" : "password"}a
          placeholder='Confirm password'
          id='confirm_pwd'
          onChange={(e) => setMatchPwd(e.target.value)}
          value={matchPwd}
          required
          aria-invalid={validMatch ? "false" : "true"}
          aria-describedby='confirmnote'
          onFocus={() => setMatchFocus(true)}
          onBlur={() => setMatchFocus(false)}
        />
        <span className={validMatch && matchPwd ? "valid" : "hide"}>
          <i className='bi bi-check-circle-fill'></i>
        </span>
        <span className={validMatch || !matchPwd ? "hide" : "invalid"}>
          <i className='bi bi-x-circle-fill'></i>
        </span>
        &nbsp;&nbsp;
        <button
          type='button'
          className='toggle-password-visibility-btn'
          onClick={() => setShowPwd(!showPwd)}
        >
          {/* if showPwd is true, show slash icon */}
          <i className={`bi bi-eye${showPwd ? "-slash" : ""}`} id='eye'></i>
        </button>{" "}
        <p
          id='confirmnote'
          className={matchFocus && !validMatch ? "instructions" : "offscreen"}
        >
          <i className='bi bi-info-circle-fill'></i> &nbsp; Password Must match the first
          password input field.
        </p>
      </div>
    </div>
  );
}
