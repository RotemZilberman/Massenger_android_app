// create a user schema
const mongoose = require("mongoose");
// add a collection to the data base
const UserPass = new mongoose.Schema({
  username: {
    type: String,
    required: true,
  },
  password: {
    type: String,
    required: true,
  },
});

// export the user schema
module.exports = mongoose.model("UserPass", UserPass);
