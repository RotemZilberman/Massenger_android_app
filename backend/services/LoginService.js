const User = require("../models/UserModel");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");

const comparePasswords = async (plainPassword, hashedPassword) => {
  const match = await bcrypt.compare(plainPassword, hashedPassword);
  return match;
};

const loginValidation = async (username, password) => {
  // Check credentials
  // get user from db BY username
  const user = await User.findOne({ username: username });
  if (user) {
    const match = await comparePasswords(password, user.password);
    if (match) {
      const data = { username: username };
      // Generate the token.
      const token = jwt.sign(data, process.env.JWT_KEY, { expiresIn: "1h" });
      return token;
    } else {
      // Passwords don't match
      // ...
      return false;
    }
  } else {
    // No user found
    // ...
    return false;
  }
};

module.exports = { loginValidation };
