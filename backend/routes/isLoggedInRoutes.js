// login router

const isLoggedInController = require("../controllers/isLoggedIn.js");

const router = require("express").Router();

// go to the controller to generate a token

router.post("/", isLoggedInController.isLoggedIn);

module.exports = router;
