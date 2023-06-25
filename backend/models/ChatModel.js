const mongoose = require("mongoose");

const chatSchema = new mongoose.Schema({
  users: {
    type: [
      {
        type: mongoose.Schema.Types.ObjectId,
        ref: "User",
      },
    ],
  },
  messages: {
    type: [
      {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Message",
      },
    ],
    default: [],
  },
});

module.exports = mongoose.model("Chat", chatSchema);
