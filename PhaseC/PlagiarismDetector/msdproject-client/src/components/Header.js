import React from 'react';
import PropTypes from 'prop-types';
import ReactDOM from 'react-dom';
import HomePage from './HomePage.js'
import RegisterPage from './RegisterPage.js'
import CoursePage from './CoursePage.js'
import AdminPage from './admin/AdminPage.js'
import ProfessorCoursePage from './professor/ProfessorCoursePage.js'
import PlagiarismReportsPage from './PlagiarismReportsPage.js'
import {BrowserRouter as Router, Link, Route} from 'react-router-dom'
import ChooseCourse from "./ChooseCourse";
import ChooseCoursePage from "./ChooseCoursePage";
import ViewStudentFilesPage from "./ViewStudentFilesPage";
import ReportsPage from "./professor/ReportsPage";
import './App.css';

export default class Header extends React.Component {

  render() {
    return (
      <div>
      <Router>
        <div>
            <br />
            <h1 class="jumbotron text-center"> Plagiarism Detection System </h1>
            <HomePage />
            <Route path="/login" component={HomePage} />
            <Route path="/register" component={RegisterPage} />
            <Route path="/viewFiles" component={ViewStudentFilesPage} />
            <Route path="/home" component={HomePage} />
            <Route path="/reports" component={ReportsPage} />
        </div>
      </Router>
      </div>
    );
  }
}
