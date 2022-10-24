import React from 'react';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Home from './components/Home';
import Navigation from './components/Navigation';
import Sightings from './components/Sightings';
import Birds from './components/Birds';
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
          <Sightings />
        </Route>
        <Route path="/birds">
          <Birds />
        </Route>
        <Route path="*">
          <NotFound />
        </Route>
      </Switch>
      </Router>
  )
}

export default App;
