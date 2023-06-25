import {
  createBrowserRouter,
  RouterProvider,
  createRoutesFromChildren,
  Route,
  Outlet,
  Navigate,
} from "react-router-dom";
import Login from "./components/Registration/Login";
import SignUp from "./components/Registration/SignUp";
import ChatPage from "./components/ChatsScreen/ChatBody/ChatPage";
import { useState, useEffect } from "react";
import UserContext from "./components/Users/User";
import NotFound from "./components/NotFound/NotFound";
const imagePath = process.env.PUBLIC_URL + "/images/";
function App() {
  const [currentUser, setCurrentUser] = useState(null);

  const handleSignUp = async (username, password, displayname, profilePic) => {
    // create post request to server
    const response = await fetch("http://localhost:5000/api/Users", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: username,
        password: password,
        displayName: displayname,
        profilePic: profilePic,
      }),
    });
    if (response.status !== 200) {
      return "Username already exists";
    }
    return "success";
  };

  const handleLogin = async (username, password) => {
    const response = await fetch(`http://localhost:5000/api/Tokens`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username: username, password: password }),
    });
    if (response.status === 404) {
      return "User not found";
    }
    const token = await response.text();

    const userRes = await fetch(`http://localhost:5000/api/Users/${username}`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    });
    if (userRes.status === 401) {
      refreshToken();
    }
    if (userRes.status === 404 || userRes.status === 401) {
      return "User not found";
    }
    const user = await userRes.json();
    setCurrentUser({ ...user, token: token, password: password });
    return "success";
  };

  const handleLogout = () => {
    setCurrentUser(null);
  };

  const addContact = async (username) => {
    if (currentUser.username === username) {
      return "You cannot add yourself as a contact";
    }

    const response = await fetch("http://localhost:5000/api/Chats", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${currentUser.token}`,
      },
      body: JSON.stringify({ username: username }),
    });
    if (response.status === 409) {
      return "Contact already exists";
    }
    if (response.status === 400) {
      return "User not found";
    }
    if (response.status === 401) {
      refreshToken();
      return "try again";
    }

    return "success";
  };

  const addMessage = async (id, message) => {
    const response = await fetch(`http://localhost:5000/api/Chats/${id}/Messages`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${currentUser.token}`,
      },
      body: JSON.stringify({ msg: message }),
    });
    if (response.status === 401) {
      refreshToken();
      return;
    }
    const UpdateChat = await response.json();
    return UpdateChat;
  };

  const refreshToken = async () => {
    const response = await fetch(`http://localhost:5000/api/Tokens`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: currentUser.username,
        password: currentUser.password,
      }),
    });
    if (response.status === 404) {
      handleLogout();
    }
    const token = await response.text();
    setCurrentUser({ ...currentUser, token: token });
  };

  const userContextValue = {
    currentUser,
    handleSignUp,
    handleLogin,
    handleLogout,
    addContact,
    addMessage,
    refreshToken,
  };

  const routes = createRoutesFromChildren(
    <Route>
      <Route path='/login' element={<Login />} />
      <Route exact path='/' element={<Login />} />
      <Route exact path='/chats' element={<ChatPage />} />
      <Route path='/signup' element={<SignUp />} />
      <Route path='*' element={<NotFound />} />
    </Route>
  );

  const router = createBrowserRouter(routes);

  return (
    <UserContext.Provider value={userContextValue}>
      <RouterProvider router={router}>
        <Outlet />
      </RouterProvider>
    </UserContext.Provider>
  );
}
export default App;
