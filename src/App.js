import React, { Component } from 'react';
import Home from './Home'
import Form from './Form'
import Contact from './Contact'
import logo from './logo.svg';


class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
         
          <h1 className="App-title">Welcome to Feedback Application</h1>
        </header>
        <div className="whole-template">
        <ul>
            <li><a href='/'>Home</a></li>
            <li><a href='/form'>Feedback Form</a></li>
            <li><a href='/contact'>Contact us</a></li>
        
        
        </ul>
        <div className="content">
        <Home/>
        <Form/>
        <Contact/>
        </div>
        
        
        
        
        
        
        
        
        </div>
      </div>
    );
  }
}

export default App;
