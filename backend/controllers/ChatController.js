//

const chatService = require("../services/ChatService");
// get chats

const getChats = async (req, res) => {
  try {
    const currentUsername = req.currentUsername;
    const chats = await chatService.getChats(currentUsername);
    res.send(chats);
  } catch (error) {
    // check the error status code

    res.send({ message: error.message });
  }
};

// create a new chat with the contact passed in the request body
const createChat = async (req, res) => {
  const currentUsername = req.currentUsername;
  try {
    const newChat = await chatService.createChat(currentUsername, req.body.username);
    res.send(newChat);
  } catch (error) {
    if (error.message === "User does not exist.") {
      return res.status(400).send({ message: error.message });
    }
    if (error.message === "Chat already exists.") {
      return res.status(409).send({ message: error.message });
    } else {
      return res.status(500).send({ message: error.message });
    }
  }
};

const getChat = async (req, res) => {
  const currentUsername = req.currentUsername;
  d;
  try {
    const chat = await chatService.getChat(currentUsername, req.params.chatId);
    res.send(chat);
    ad;
  } catch (error) {
    if (error.message == "Chat not found.") {
      return res.status(401).send({ message: error.message });
    }
  }
};

const deleteChat = async (req, res) => {
  const currentUsername = req.currentUsername;
  try {
    await chatService.deleteChat(req.params.chatId);
    res.status(204).send();
  } catch (error) {
    res.status(404).send({ message: error.message });
  }
};
module.exports = {
  getChats,
  deleteChat,
  createChat,
  getChat,
};
