const express = require("express");
const router = express.Router();
const firebase = require("../controllers/firebaseController.js");
router.post("/:username", firebase.signUser);

module.exports = router;
