const userService = require("../services/UserService.js");

const createUser = async (req, res) => {
  try {
    const newUser = await userService.createUser(req);
    res.send(newUser);
  } catch (error) {
    if (error.code === 409) {
      res.status(409).send({ message: "Username already exists" });
    } else if (error.code === 400) {
      res.status(400).send({ message: error.message });
    } else {
      res.status(500).send({ message: error.message });
    }
  }
};

const getUser = async (req, res) => {
  try {
    // find user b username
    const user = await userService.getUser(req.params.id);
    // if user not found
    if (!user) {
      return res.status(401).send("User not found");
    }
    // if user found
    res.send(user);
  } catch (error) {
    res.send({ message: error.message }, "error");
  }
};
module.exports = {
  createUser,
  getUser,
};
