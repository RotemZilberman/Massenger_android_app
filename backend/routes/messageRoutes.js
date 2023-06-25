const express = require("express");
const router = express.Router();
const isLoggedIn = require("../controllers/isLoggedInController.js");
const messageController = require("../controllers/messageController.js");

router
  .get("/:ChatId/Messages", isLoggedIn.isLoggedIn, messageController.getMessages)
  .post("/:ChatId/Messages", isLoggedIn.isLoggedIn, messageController.createMessage);

module.exports = router;
