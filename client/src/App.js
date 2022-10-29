import React from 'react';
import { BrowserRouter as Router, Switch, Route, Redirect } from "react-router-dom";
import { useEffect, useState } from "react";
import jwtDecode from "jwt-decode";

import Home from './components/Home';
import Navigation from './components/Navigation';
import SightingList from './components/SightingList';
import SightingForm from './components/SightingForm';
import BirdGrid from './components/BirdGrid';
import BirdForm from './components/BirdForm';
import Login from './components/Login';
import NotFound from './components/NotFound';
import Error from './components/Error';
import AuthContext from "./contexts/AuthContext";
import { refresh } from "./services/auth"

const LOCAL_STORAGE_TOKEN_KEY = "wingspanToken";

function App() {
  const [user, setUser] = useState(null);
  // NEW: Define a state variable to track if 
  // the restore login attempt has completed
  const [restoreLoginAttemptCompleted, setRestoreLoginAttemptCompleted] = useState(false);

  // NEW: Define a useEffect hook callback function to attempt
  // to restore the user's token from localStorage
  useEffect(() => {
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
    if (token) {
      login(token);
    }
    refresh().then(setUser).catch(logout);
    setRestoreLoginAttemptCompleted(true);
  }, []);

  const login = (token) => {
    // NEW: set the token in localStorage
    localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, token);

    // Decode the token
    const { sub: username, app_user_id: id, authorities: authoritiesString } = jwtDecode(token);

    // Split the authorities string into an array of roles
    const roles = authoritiesString.split(',');

    // Create the "user" object
    const user = {
      username,
      id,
      roles,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      }
    };

    // Log the user for debugging purposes
    console.log(user);

    // Update the user state
    setUser(user);

    // Return the user to the caller
    return user;
  };

  const logout = () => {
    setUser(null);
    // NEW: remove the token from localStorage
    localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY);
  };

  const auth = {
    user: user ? { ...user } : null,
    login,
    logout
  };

  // NEW: If we haven't attempted to restore the login yet...
  // then don't render the App component
  if (!restoreLoginAttemptCompleted) {
    return null;
  }

  return (
    <AuthContext.Provider value={auth}>
      <Router>
        <h1>WingSpan 🦉</h1>
        <Navigation />
        <Switch>
          <Route path="/" exact>
            <Home />
          </Route>
          <Route path="/sightings" exact>
            <SightingList />
          </Route>
          <Route path={["/sightings/add", "/sightings/edit/:sightingId"]}>
            {auth.user ? (
              <SightingForm />
            ) : (
              <Redirect to='/login' />
            )}
          </Route>
          <Route path="/birds" exact>
            <BirdGrid />
          </Route>
          <Route path={["/birds/add", "/birds/edit/:birdId"]}>
            {auth.user ? (
              <BirdForm />
            ) : (
              <Redirect to='/login' />
            )}
          </Route>
          <Route path="/login">
            {!auth.user ? <Login /> : <Redirect to="/" />}
          </Route>
          <Route path="/error">
            <Error />
          </Route>
          <Route path="*">
            <NotFound />
          </Route>
        </Switch>
      </Router>
    </AuthContext.Provider>
  )
}

export default App;
