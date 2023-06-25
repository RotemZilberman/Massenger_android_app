// create a user schema
const mongoose = require("mongoose");
// add a collection to the data base
const UserSchema = new mongoose.Schema({
  username: {
    type: String,
    required: true,
    unique: true,
    index: true,
  },
  password: {
    type: String,
    required: true,
  },
  displayName: {
    type: String,
    required: true,
  },
  profilePic: {
    type: String,
    required: false,
  },
  room: {
    type: String,
    required: false,
  }
});

// export the user schema
module.exports = mongoose.model("User", UserSchema);
