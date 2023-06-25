const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
const cors = require("cors");
const customEnv = require("custom-env");
const app = express();
customEnv.env(process.env.NODE_ENV, "./config");
require("custom-env").env(process.env.NODE_ENV, "./config");

app.use(cors());

app.use(express.static("public"));
app.use(bodyParser.json({ limit: "50mb" }));
app.use(bodyParser.urlencoded({ limit: "50mb", extended: true }));
app.use(bodyParser.text());

mongoose
  .connect(process.env.MONGODB_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => console.log("Connected to MongoDB..."));

console.log(process.env.MONGODB_URL);
console.log(process.env.PORT);

// use /api/ to access the routes

const loginRouter = require("./routes/loginRoutes");
app.use("/api/Tokens", loginRouter);
// const signupRouter = require("./routes/signup");
// app.use("/api/signup", signupRouter);
const userRouter = require("./routes/userRoutes");
app.use("/api", userRouter);
const chatRouter = require("./routes/chatRoutes");
app.use("/api/Chats", chatRouter);
// massages
const messageRouter = require("./routes/messageRoutes");
app.use("/api/Chats/", messageRouter);
const firebaseRouter = require("./routes/firebaseRoutes");
app.use("/api/FirebaseTokens/", firebaseRouter);

app.listen(process.env.PORT, () => {
  console.log(`Server running on port ${process.env.PORT}`);
});
