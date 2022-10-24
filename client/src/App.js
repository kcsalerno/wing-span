import React from 'react';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Home from './components/Home';
import Navigation from './components/Navigation';
import Sighting from './components/Sighting';
import Bird from './components/Bird';
import NotFound from './components/NotFound';

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
          <Sighting />
        </Route>
        <Route path="/bird">
          <Bird />
        </Route>
        <Route path="*">
          <NotFound />
        </Route>
      </Switch>
    </Router>
  )
}

export default App;
