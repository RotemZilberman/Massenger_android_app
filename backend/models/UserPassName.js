// create a user schema
const mongoose = require("mongoose");
// add a collection to the data base
const UserPassName = new mongoose.Schema({
  username: {
    type: String,
    required: true,
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
    type : String,
    contentType: String, // Content type of the image
  },
});

// export the user schema
module.exports = mongoose.model("UserPassName", UserPassName);
