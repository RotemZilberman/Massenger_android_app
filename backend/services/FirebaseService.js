const admin = require("firebase-admin");
const serviceAccount = require("../config/service_acc.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

var dict = {};
const signUser = (firebasetoken, username) => {
  console.log("signing user");
  dict[username] = firebasetoken;
};

const sendNotification = (username, content, senderDisplayName, chatID) => {
  console.log("sending notification");
  console.log(dict);
  const message = {
    token: dict[username], // Replace with the FCM token of the target device
    notification: {
      title: senderDisplayName,
      body: content,
    },
    data: {
      chatID: chatID,
    },
  };
  console.log(message);
  // push to firebase cloud messaging
  admin
    .messaging()
    .send(message)
    .then((response) => {
      console.log("Successfully sent notification:", response);
    })
    .catch((error) => {
      console.error("Error sending notification:", error);
    });
};

const isSignedIn = (username) => {
  return dict[username] !== undefined;
};

module.exports = {
  signUser,
  sendNotification,
  isSignedIn,
};
