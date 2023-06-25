const FirebaseService = require("../services/FirebaseService.js");

// create a new chat with the contact passed in the request body
const signUser = (req, res) => {
  const firebasetoken = req.body;

  
  console.log(firebasetoken + "firebase token");
  try {
    console.log(req.params.username, firebasetoken);
    FirebaseService.signUser(firebasetoken, req.params.username);
    res.status(200).send();
  } catch (error) {
    if (error.message === "User does not exist.") {
      return res.status(400).send({ message: error.message });
    }
    if (error.message === "Chat already exists.") {
      return res.status(409).send({ message: error.message });
    } else {
      return res.status(500).send({ message: error.message });
    }
  }
};

module.exports = {
  signUser,
};
