import React, { Component } from "react";
 
class Form extends Component {
  render() {
    return (
      <div>
        <h2>Feedback Form</h2>
        <p>Mauris sem velit, vehicula eget sodales vitae,
        rhoncus eget sapien:</p>
        <ol>
          <li>name : <input type='text'/></li>
          <li>comments : <input type='text'/></li>
          <li>Vestibulum vulputate</li>
          <li>Eget erat</li>
          <li>Id porttitor</li>
        </ol>
      </div>
    );
  }
}
 
export default Form;