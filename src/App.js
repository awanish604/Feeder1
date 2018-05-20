import React, { Component } from 'react';
import Home from './Home'
import Form from './Form'
import Contact from './Contact'
import logo from './logo.svg';

import {
  Route,
  NavLink,
  HashRouter
} from "react-router-dom";


class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
         
          <h1 className="App-title">Welcome to Feedback Application</h1>
        </header>
        <div className="whole-template">
        <HashRouter>
            <div>
        <ul>
            <li><NavLink  to="/">Home</NavLink></li>
            <li><NavLink to="/form">Feedback Form</NavLink></li>
            <li><NavLink to="/contact">Contact us</NavLink></li>
           
      
        </ul>
        <div className="content">
            <Route exact path="/" component={Home}/>
            <Route path="/form" component={Form}/>
            <Route path="/contact"component={Contact}/>
      
        </div>
        </div>
        </HashRouter> 
      
        </div>
      </div>
    );
  }
}

export default App;
