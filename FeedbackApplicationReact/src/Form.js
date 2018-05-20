import React, { Component } from "react";
 
class Form extends Component {
  render() {
    return (
      <div>
        <h2>Feedback Form</h2>
        <p><h3>Let the truth begin !!</h3></p>
        <ol>
          <li>name     : <input type='text'/></li>
          <li>comments : <input type='text'/></li>
          <li>stars given : <slider/></li>
          
        </ol>
      </div>
    );
  }
}
 
export default Form;