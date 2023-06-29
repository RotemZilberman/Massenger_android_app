# Android Chat Application

This repository contains an Android client for a chat system. The application includes all the necessary screens such as Login, Registration, Chat, Add new contact, and Settings. It interacts with a predefined API for functionalities like registration, login, contacts, chats, sending and receiving messages. Different technologies are used in the project such as firebase sqlite to use a local database

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

## Design
The application is built upon the MVVM (Model-View-ViewModel) architecture, which provides a structured and scalable approach to development. This architectural pattern enables separation of concerns and promotes maintainability.

![image](https://github.com/RotemZilberman/ass3-programming/assets/112009232/bf33c439-e1dd-458f-8e99-6de7e652edeb)

To ensure a cohesive and consistent user experience, the application follows a uniform design theme across all screens. This design theme has been carefully crafted to strike a balance between visual aesthetics and intuitive user interactions. The goal is to deliver a polished and engaging interface that aligns with modern design principles.

Furthermore, the application utilizes Firebase Cloud Messaging to incorporate real-time push notifications. This feature enables seamless communication with users by delivering timely updates and notifications directly to their devices. To efficiently manage local data persistence, the application leverages Room, an SQLite-based persistence library. This integration allows for efficient data storage and retrieval, enhancing the overall performance and responsiveness of the app.

In summary, the application adopts the MVVM architecture to ensure a well-structured codebase, while employing Firebase Cloud Messaging and Room in SQLite to enable real-time notifications and efficient data management respectively. The consistent design theme throughout the app guarantees a visually appealing and user-friendly experience.
### Note
that the notification must be activated on the device in order to use the application properly and must be connected to Firebase.

#Please add the next line too :

![image](https://github.com/RotemZilberman/ass3-programming/assets/94087682/639aeb1b-34ce-4e09-be9d-2bd70efa3e38)

