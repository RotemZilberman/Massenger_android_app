import React from "react";
import { Link, Navigate } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useContext, useRef, useState } from "react";
import UserContext from "../Users/User";
import "../../styles/connection.css";
import "../../styles/layout.css";
import "../../styles/signup.css";
import { Alert, Button } from "react-bootstrap";

const images = require.context("../../../public/images/", true);

export default function Login() {
  const { currentUser, handleLogin } = useContext(UserContext);
  const navigate = useNavigate();
  const usernameRef = useRef(null);
  const passwordRef = useRef(null);
  const errRef = useRef(null);
  const [errMsg, setErrMsg] = useState("");
  const [showPwd, setShowPwd] = useState(false);

  const handleClose = () => {
    setErrMsg("");
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    const result = await handleLogin(usernameRef.current.value, passwordRef.current.value);
    if (result === "User not found") {
      setErrMsg("User not found");
      usernameRef.current.focus();
      return;
    }
    navigate("/chats");
    // if (currentUser) {
    // return <Navigate to='/chats' />;
    // }
  };

  return (
    <div>
      <div
        className='d-flex align-items-center justify-content-center container'
        id='container-signup'
      >
        <div className='d-flex flex-column align-items-center card'>
          <form className='form ' onSubmit={handleSubmit}>
            <div className='row headerPage'>Login</div>
            <div className='row justify-content-center text-center'>
              {errMsg && (
                <Alert variant='danger' onClose={handleClose} dismissible>
                  <span aria-live='assertive'>{errMsg}</span>
                </Alert>
              )}
            </div>
            <div className='row justify-content-center'>
              <div className='line'>
                <img src={images("./username.png")} alt='Avatar' className='col-2 type' />
                <input
                  className='col-10 info'
                  placeholder='Username '
                  ref={usernameRef}
                  required
                />
              </div>
            </div>
            <div className='row justify-content-center'>
              <div className='line'>
                <img
                  src={images("./icons8-lock-67.png")}
                  alt='Avatar'
                  className='col-2 type'
                />
                <input
                  className='col-10 info'
                  placeholder='Password'
                  type={showPwd ? "text" : "password"}
                  ref={passwordRef}
                  required
                />
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button
                  type='button'
                  className='toggle-password-visibility-btn'
                  onClick={() => setShowPwd(!showPwd)}
                >
                  {/* if showPwd is true, show slash icon */}
                  <i className={`bi bi-eye${showPwd ? "-slash" : ""}`} id='eye'></i>
                </button>{" "}
              </div>
            </div>
            <div className='row justify-content-center'>
              <button className='btn col-3 log'>Login</button>
            </div>
            <div className='row justify-content-center'>
              <div className='col-9'>
                <div className='footnotes'>Not registered?</div>
                <Link to='/signup' className='link'>
                  &nbsp;&nbsp;Click here
                </Link>
                <div className='footnotes'>&nbsp; to Register</div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
