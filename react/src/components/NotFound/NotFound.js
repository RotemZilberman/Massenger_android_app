import React from "react";
import "../../styles/notfound.css";
import "../../styles/layout.css";
import { Link } from "react-router-dom";
import "../../styles/signup.css";
import "../../styles/connection.css";
const NotFound = () => {
  return (
    <div className='container d-flex align-items-center justify-content-center'>
      <div className='d-flex flex-column card align-items-center card'>
        <div className='row align-items-center justify-content-center text-center'>
          <h1 className='headerPage'>404 Not Found</h1>
          <h2 className='headerPage'>The page you requested could not be found.</h2>

          <Link to='/login' className='btn log'>
            Go to login
          </Link>
          <Link to='/signup' className='btn log'>
            Sign Up
          </Link>
        </div>
      </div>
    </div>
  );
};

export default NotFound;
