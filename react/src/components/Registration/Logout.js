import React, { Fragment, useContext } from "react";
import { useNavigate } from "react-router-dom";
import UserContext from "../Users/User";
import "../../styles/logout.css";
import { Link } from "react-router-dom";
export default function Logout() {
  const navigate = useNavigate();
  const { handleLogout } = useContext(UserContext);

  const handleLogoutClick = () => {
    handleLogout();
    navigate("/");
  };

  return (
    <>
      <div className='logout'>
        <Link to='/login' className='btn log' onClick={handleLogoutClick}>
          Logout
        </Link>
      </div>
    </>
  );
}
