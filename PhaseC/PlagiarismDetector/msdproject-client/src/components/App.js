import React from 'react';
import PropTypes from 'prop-types';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Link, Route} from 'react-router-dom'
import './App.css';
import HomePage from "./HomePage"


class App extends React.Component {

  render(){
    return (
      <div>
        <HomePage />
      </div>
    );
  }
}
export default App
