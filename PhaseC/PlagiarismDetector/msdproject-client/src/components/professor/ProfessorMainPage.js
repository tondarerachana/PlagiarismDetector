import React from 'react';
import {BootstrapTable, TableHeaderColumn, Button} from 'react-bootstrap';
import ProfessorCoursePage from './ProfessorCoursePage'
import AddNewCoursePage from './AddNewCoursePage'
import AddNewAssignmentPage from './AddNewAssignmentPage'
import CoursePage from './CoursePage'
import AssignmentsPage from './AssignmentsPage'

export default class ProfessorMainPage extends React.Component{

  constructor(props: any){
    super(props);

    this.state = {
      generateReports: false,
      assignmentsPage: false,
      addNewAssignment: false,
      coursePage: false,
      addNewCourse: false,
      userID: this.props.userID,
    };

    this.generateReports = this.generateReports.bind(this);
    this.assignmentsPage = this.assignmentsPage.bind(this);
    this.addNewAssignment = this.addNewAssignment.bind(this);
    this.coursePage = this.coursePage.bind(this);
    this.addNewCourse = this.addNewCourse.bind(this);
    this.logout = this.logout.bind(this);
  }

  logout(){
    localStorage.clear();
    window.location.reload();
  }

  generateReports(){
    this.setState({
      generateReports: true,
      assignmentsPage: false,
      addNewAssignment: false,
      coursePage: false,
      addNewCourse: false,
    })
  }

  assignmentsPage(){
    this.setState({
      generateReports: false,
      assignmentsPage: true,
      addNewAssignment: false,
      coursePage: false,
      addNewCourse: false,
    })
  }

  addNewAssignment(){
    this.setState({
      generateReports: false,
      assignmentsPage: false,
      addNewAssignment: true,
      coursePage: false,
      addNewCourse: false,
    })
  }

  coursePage(){
    this.setState({
      generateReports: false,
      assignmentsPage: false,
      addNewAssignment: false,
      coursePage: true,
      addNewCourse: false,
    })
  }

  addNewCourse(){
    this.setState({
      generateReports: false,
      assignmentsPage: false,
      addNewAssignment: false,
      coursePage: false,
      addNewCourse: true,
    })
  }

  render(){

    let NavBar =
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div>
          <a class="navbar-brand" style={{ color: 'white'}}>Plagiarism Detection System</a>
        </div>
        <div>
          <a class="navbar-brand" style={{ color: 'white'}}>Faculty Page</a>
        </div>
        <div>
          <Button onClick={this.generateReports}>  Generate Reports </Button>
        </div>
        <div>
          <Button onClick={this.assignmentsPage}> Assignments Page </Button>
        </div>
        <div>
          <Button onClick={this.addNewAssignment}> Add New Assignment </Button>
        </div>
        <div>
          <Button onClick={this.coursePage}> Courses Page </Button>
        </div>
        <div>
          <Button onClick={this.addNewCourse}> Add new Course </Button>
        </div>
        <div>
          <Button onClick={this.logout} > Logout </Button>
        </div>
      </div>
    </nav>

    if(this.state.generateReports){
      return(
        <div>
          {NavBar}
          <br />
          <ProfessorCoursePage userID={this.state.userID}/>
        </div>
      )
    }
    else if(this.state.assignmentsPage){
      return(
        <div>
          {NavBar}
          <br />
          <AssignmentsPage />
        </div>
      )
    }
    else if(this.state.addNewAssignment){
      return(
        <div>
          {NavBar}
          <br />
          <AddNewAssignmentPage />
        </div>
      )
    } else if(this.state.addNewCourse){
      return(
        <div>
          {NavBar}
          <br />
          <AddNewCoursePage userID={this.state.userID}/>
        </div>
      )
    }
    else if(this.state.coursePage){
      return(
        <div>
          {NavBar}
          <br />
          <CoursePage />
        </div>
      )
    }
    else {
      return(
        <div>
          {NavBar}
          <br />
          <ProfessorCoursePage userID={this.state.userID}/>
        </div>
      )
    }
  }
}
