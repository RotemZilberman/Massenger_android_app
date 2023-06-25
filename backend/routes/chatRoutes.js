const express = require("express");
const router = express.Router();
const isLoggedIn = require("../controllers/isLoggedInController.js");
const chatController = require("../controllers/ChatController.js");

router
  .get("/", isLoggedIn.isLoggedIn, chatController.getChats)
  .post("/", isLoggedIn.isLoggedIn, chatController.createChat)
  .get("/:chatId", isLoggedIn.isLoggedIn, chatController.getChat)
  .delete("/:chatId", isLoggedIn.isLoggedIn, chatController.deleteChat);

module.exports = router;
