const User = require("../models/UserModel");
const mongoose = require("mongoose");
const bcrypt = require("bcrypt");

const createUser = async (req) => {
  try {
    const saltRounds = 10;
    const hashedPassword = await bcrypt.hash(req.body.password, saltRounds);
    const user = new User({
      username: req.body.username,
      password: hashedPassword,
      displayName: req.body.displayName,
      profilePic: req.body.profilePic,
    });
    await user.save();
    const userToReturn = {
      username: user.username,
      displayName: user.displayName,
      profilePic: user.profilePic,
    };
    return userToReturn;
  } catch (e) {
    if (e.name === "ValidationError") {
      e.code = 400;
      throw e;
    } else if (e.code === 11000) {
      e.code = 409;
      throw e;
    } else {
      e.code = 500;
      throw e;
    }
  }
};
const getUser = async (username) => {
  // find user b username
  const user = await User.findOne({ username: username });
  // if user not found
  if (!user) {
    return null;
  }
  const userToReturn = {
    username: user.username,
    displayName: user.displayName,
    profilePic: user.profilePic,
  };
  return userToReturn;
};

// export the service
module.exports = {
  createUser,
  getUser,
};
