const express = require("express");
const router = express.Router();
const userController = require("../controllers/userController.js");
const isLoggedIn = require("../controllers/isLoggedInController.js");

router
  .get("/Users/:id", isLoggedIn.isLoggedIn, userController.getUser)
  .post("/Users/", userController.createUser);

module.exports = router;
