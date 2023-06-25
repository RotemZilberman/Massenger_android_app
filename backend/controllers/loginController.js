const loginService = require("../services/LoginService.js");
const jwt = require("jsonwebtoken");
// Handle login form submission
const processLogin = async (req, res) => {
  // Check credentials
  const token = await loginService.loginValidation(req.body.username, req.body.password);
  if (token) {
    // return the token as text/plain
    res.set("Content-Type", "text/plain");
    res.status(200).send(token);
  }
  // Incorrect username/password. The user should try again.
  else res.status(404).send("Invalid username and/or password");
};
// Export the controller
module.exports = {
  processLogin,
};
