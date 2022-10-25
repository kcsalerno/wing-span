import React from 'react';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { useEffect, useState } from "react";

import Home from './components/Home';
import Navigation from './components/Navigation';
import SightingList from './components/SightingList';
import SightingForm from './components/SightingForm';
import BirdGrid from './components/BirdGrid';
import Login from './components/Login';
import NotFound from './components/NotFound';
import Error from './components/Error';
import { refresh } from "./services/auth"

function App() {

  const [user, setUser] = useState();

  useEffect(() => {
    refresh().then(setUser).catch(logout);
  }, []);

  const login = setUser;
  const logout = () => {
    setUser();
    localStorage.removeItem("jwt");
  }

  const auth = {
    user,
    login,
    logout
  }

  return (
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
        <Route path={["/add", "/edit/:sightingId"]}>
          <SightingForm />
        </Route>
        <Route path="/birds">
          <BirdGrid />
        </Route>
        <Route path="/login">
          <Login />
        </Route>
        <Route path="/error">
          <Error />
        </Route>
        <Route path="*">
          <NotFound />
        </Route>
      </Switch>
    </Router>
  )
}

export default App;
