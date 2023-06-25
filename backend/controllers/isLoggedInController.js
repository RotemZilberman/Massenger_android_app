const jwt = require("jsonwebtoken");

// Ensure that the user sent a valid token
const isLoggedIn = (req, res, next) => {
  // If the request has an authorization header
  if (req.headers.authorization) {
    // Extract the token from that header
    const token = req.headers.authorization.split(" ")[1];

    try {
      // Verify the token is valid
      const data = jwt.verify(token, process.env.JWT_KEY);
      // Token validation was successful. Continue to the actual function (Chats)
      req.currentUsername = data.username;

      return next();
    } catch (err) {
      return res.status(401).send("Invalid Token");
    }
    // } else return res.status(403).send("Token required");
  } else return res.status(401).send("Invalid Token");
};

// Export the controller
module.exports = {
  isLoggedIn,
};
