import { useContext, useEffect, useRef } from "react";
import UserContext from "../Users/User";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import "../../styles/connection.css";
import "../../styles/signup.css";
import UsernameReg from "./UsernameReg";
import PasswordReg from "./PasswordReg";
import MatchPassword from "./MatchPassword";
import DisplayNameReg from "./DisplayNameReg";
import AvatarReg from "./AvatarReg";
import ShowAvatar from "./ShowAvatar";
import { Alert, Button } from "react-bootstrap";

const images = require.context("../../../public/images/", true);

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

function SignUp() {
  const { handleSignUp } = useContext(UserContext);
  const navigate = useNavigate();
  // to put focos on the user input when the page is loaded
  const userRef = useRef();
  // to put focos when the error message is displayed
  const errRef = useRef();

  // user name
  const [user, setUser] = useState("");
  // name validation
  const [validName, setValidName] = useState(false);
  // foucos on the name input
  const [userFocus, setUserFocus] = useState(false);

  // password
  const [pwd, setPwd] = useState("");
  const [validPwd, setValidPwd] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);

  // match password
  const [matchPwd, setMatchPwd] = useState("");
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);
  // display name
  const [displayName, setDisplayName] = useState("");
  const [displayNameFocus, setDisplayNameFocus] = useState(false);

  const [avatar, setAvatar] = useState(null);
  const handleAvatarChange = (avatar) => {
    setAvatar(avatar);
  };

  const [errMsg, setErrMsg] = useState("");
  useEffect(() => {
    userRef.current.focus();
  }, []);

  // cheeck if the user name is valid
  useEffect(() => {
    setValidName(USER_REGEX.test(user));
  }, [user]);

  // check if the password is valid
  useEffect(() => {
    setValidPwd(PWD_REGEX.test(pwd));
    setValidMatch(pwd === matchPwd);
  }, [pwd, matchPwd]);

  // if user and password change, clear the error message
  useEffect(() => {
    setErrMsg("");
  }, [user, pwd, matchPwd]);

  const handleClose = () => {
    setErrMsg("");
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    const result = await handleSignUp(user, pwd, displayName, avatar);
    if (result === "success") {
      navigate("/login");
    } else if (result === "Username already exists") {
      setErrMsg("Username already exists");
      return;
    }
  };

  return (
    <div>
      <div
        className='d-flex align-items-center justify-content-center container'
        id='container-signup'
      >
        <div className='d-flex flex-column card align-items-center card'>
          <form className='form' onSubmit={handleSubmit}>
            <div className='row headerPage'>Sign up</div>
            <div className='row justify-content-center text-center '>
              <ShowAvatar avatar={avatar} />
              {errMsg && (
                <div className='row justify-content-center text-center'>
                  <Alert variant='danger' onClose={handleClose} dismissible>
                    <span aria-live='assertive'>{errMsg}</span>
                  </Alert>
                </div>
              )}
            </div>

            <UsernameReg
              userRef={userRef}
              setUser={setUser}
              validName={validName}
              user={user}
              setUserFocus={setUserFocus}
              images={images}
              userFocus={userFocus}
            />
            {/*Password*/}
            <PasswordReg
              pwd={pwd}
              setPwd={setPwd}
              validPwd={validPwd}
              pwdFocus={pwdFocus}
              setPwdFocus={setPwdFocus}
              images={images}
              matchPwd={matchPwd}
              setMatchPwd={setMatchPwd}
              validMatch={validMatch}
              matchFocus={matchFocus}
              setMatchFocus={setMatchFocus}
            />
            {/*Confirm password*/}
            <MatchPassword
              matchPwd={matchPwd}
              setMatchPwd={setMatchPwd}
              validMatch={validMatch}
              matchFocus={matchFocus}
              setMatchFocus={setMatchFocus}
              images={images}
            />

            {/*Display name*/}
            <DisplayNameReg
              displayName={displayName}
              setDisplayName={setDisplayName}
              displayNameFocus={displayNameFocus}
              setDisplayNameFocus={setDisplayNameFocus}
              images={images}
            />

            {/*Picture*/}
            <AvatarReg images={images} onAvatarChange={handleAvatarChange} />
            <div className='row justify-content-center'>
              <button
                disabled={
                  validName && validPwd && validMatch && displayName && avatar
                    ? false
                    : true
                }
                className='btn col-3 log'
              >
                Register
              </button>
            </div>
            <div className='row justify-content-center'>
              <div className='col-9'>
                <div className='footnotes'>Already registered?</div>
                <Link to='/login' className='link'>
                  &nbsp;&nbsp;Sign in
                </Link>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default SignUp;
