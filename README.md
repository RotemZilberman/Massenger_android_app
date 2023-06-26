# Android Chat Application

This repository contains an Android client for a chat system. The application includes all the necessary screens such as Login, Registration, Chat, Add new contact, and Settings. It interacts with a predefined API for functionalities like registration, login, contacts, chats, sending and receiving messages.

## Features

- **Login Screen**: Allows users to log into the application.
- **Registration Screen**: Allows new users to register for the application.
- **Chat Screen**: Divided into two parts:
  - Contacts screen showing the list of chats.
  - A single chat screen that opens after selecting a chat from the list.
- **Add New Contact**: Allows users to add new contacts to their chat list.
- **Settings Screen**: Allows users to edit relevant settings for the application, including the server address and theme of the app.

The application saves a local copy of the chats, messages, and relevant information using Room for local SQLite DB management. It also supports push notifications using Firebase.

## Prerequisites

- Android Studio
- Node.js (for running the server)

## Installation

1. Download the project files as a zip file and extract it.
2. Open the project in Android Studio.

## Running the Server

Before running the Android application, the server needs to be up and running. Follow the steps below to run the server:

For Mac:

```
npm run test
```

For Windows:

```
npm test
```

## Running the Application
After the server is running, you can run the application on an emulator or a real device using Android Studio.

Design
The application follows a uniform design theme across all screens. Any substantial design issues or too basic a design will result in a downgrade.

###Note
that the notification must be activated on the device in order to use the application properly and must be connected to Firebase.
