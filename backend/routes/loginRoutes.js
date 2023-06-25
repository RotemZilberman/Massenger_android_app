// login router

const loginController = require("../controllers/loginController.js");
const isLoggedInController = require("../controllers/isLoggedInController.js");
const router = require("express").Router();

// go to the controller to generate a token

router.post(
  "/",
  loginController.processLogin, // this is the controller that generates the token
  isLoggedInController.isLoggedIn // this is the controller that checks if the token is valid
);

module.exports = router;
