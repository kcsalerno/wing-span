import React from 'react';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Home from './components/Home';
import Navigation from './components/Navigation';
import SightingList from './components/SightingList';
import BirdGrid from './components/BirdGrid';
import NotFound from './components/NotFound';
import Error from './components/Error';

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
        <Route path="/birds">
          <BirdGrid />
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
