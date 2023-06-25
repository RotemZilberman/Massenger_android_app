const User = require("../models/UserModel");
const Message = require("../models/MessageModel");
const Chat = require("../models/ChatModel");
const FirebaseService = require("./FirebaseService.js");
const createMessage = async (currentUsername, msgToSend, chatId) => {
  try {
    const curentUser = await User.findOne({ username: currentUsername });
    const msg = new Message({
      created: Date.now(),
      sender: {
        _id: curentUser._id,
        username: curentUser.username,
        displayName: curentUser.displayName,
        profilePic: curentUser.profilePic,
      },
      content: msgToSend,
    });
    // add the message to the chat
    const chat = await Chat.findById(chatId);

    // if chat does not exist
    if (!chat) {
      throw new Error("Chat not found.");
    }

    chat.messages.push(msg);
    chat.lastMessage = msg;

    await chat.save();
    await msg.save();

    msgToSend = {
      id: msg._id.toString(),
      created: msg.created,
      sender: {
        username: curentUser.username,
        displayName: curentUser.displayName,
        profilePic: curentUser.profilePic,
      },
      content: msg.content,
    };
    // GET all the  users in the chat
    const users = await User.find({ _id: { $in: chat.users } });
    // get the user of the receiver
    const receiver = users.find((user) => user.username !== curentUser.username);
    console.log(receiver.username, "receiver.username");
    console.log(curentUser.username, "curentUser.username");
    // get the the display name of the receiver
    if (FirebaseService.isSignedIn(receiver.username)) {
      console.log("sending message to user");
      FirebaseService.sendNotification(
        receiver.username,
        msgToSend.content,
        curentUser.displayName,
        chatId
      );
    }
    return msgToSend;
  } catch (error) {
    if (error.message === "Chat not found.") {
      throw new Error(error.message);
    } else {
      console.log(error.message + " in createMessage");
      throw new Error("Something went wrong.");
    }
  }
};

const getMessages = async (currentUsername, chatId) => {
  try {
    // find the chat
    const chat = await Chat.findById(chatId);
    if (!chat) {
      throw new Error("Chat not found.");
    }
    // find the messages in the chat
    const messages = await Message.find({ _id: { $in: chat.messages } });
    // get the user IDs of all senders
    const senderIds = messages.map((msg) => msg.sender);

    // find the users of the senders
    const users = await User.find({ _id: { $in: senderIds } });

    // map the messages and include the sender's username
    const formattedMessages = messages.map((msg) => {
      // find the user of the sender
      const sender = users.find((user) => user._id.toString() === msg.sender.toString());
      return {
        id: msg._id.toString(),
        created: msg.created,
        sender: {
          username: sender.username,
        },
        content: msg.content,
      };
    });
    // sort the messages by date
    return formattedMessages.sort((a, b) => b.created - a.created);
  } catch (error) {
    if (error.message === "Chat not found.") {
      throw new Error(error.message);
    } else {
      throw new Error("Something went wrong.");
    }
  }
};

module.exports = {
  createMessage,
  getMessages,
};
