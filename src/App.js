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
constructor(props){
super(props)
this.state={
    testData:[],
    isLoading:false
}

}
componentDidMount(){
    this.setState({
        isLoading:true
    })
    fetch('http://echo.jsontest.com/key/value/one/two')
    .then(response =>response.json() )
    .then(data => this.setState({testData : data,isLoading:false}))
    
}




  render() {
      
      const test=this.state.testData
      const isLoading= this.state.isLoading
      
      if(isLoading){
          return(<div> Loading ...............</div>)
      }
      
      
      
      console.log(test+' the test value is')
      console.log(isLoading+ 'is the value of isloading')
      
    return (
        
      <div className="App">
        <header className="App-header">
         
          <h1 className="App-title">Welcome to Feedback Application <div>{test.one} {test.key}</div> </h1>
        
            
            
            
        </header>
        <div className="whole-template">
        <HashRouter>
            <div>
        <ul className="header">
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
