import React from 'react';
import { BrowserRouter as Router, Switch, Route, Redirect } from "react-router-dom";
import { useEffect, useState } from "react";
import jwtDecode from "jwt-decode";

import Home from './components/Home';
import Navigation from './components/Navigation';
import SightingList from './components/SightingList';
import SightingForm from './components/SightingForm';
import BirdGrid from './components/BirdGrid';
import Login from './components/Login';
import NotFound from './components/NotFound';
import Error from './components/Error';
import AuthContext from "./contexts/AuthContext";
import { refresh } from "./services/auth"

const LOCAL_STORAGE_TOKEN_KEY = "wingspanToken";

function App() {

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
        <Route path={["/sightings/add", "/sightings/edit/:sightingId"]}>
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
