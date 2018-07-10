import React from 'react';
import NewSubmission from './NewSubmission.js';
import ViewAllSubmissions from './ViewAllSubmissions'
import ViewCourses from './ViewCourses'
import RegisterForCourse from './RegisterForCourse'
import DeleteSubmission from './DeleteSubmission'
import { Button , Table} from 'react-bootstrap';
import * as data from '../constants';

const url = data.URL;

export default class StudentSubmissionPage extends React.Component{

  constructor(props: any){
    super(props);

    this.state = {
      registerCourse: false,
      viewCourses: false,
      newSubmission: false,
      deleteSubmission: false,
      courses: [],
      courseNames: [],
    };

    this.registerCourse = this.registerCourse.bind(this);
    this.viewCourses = this.viewCourses.bind(this);
    this.newSubmission = this.newSubmission.bind(this);
    this.deleteSubmission = this.deleteSubmission.bind(this);
    this.logout = this.logout.bind(this);
  }

  logout(){
    console.log("From inside logout!")
    localStorage.clear();
    window.location.reload();
  }

  componentDidMount(){
    console.log("User ID from fetch users",this.props.userID)
    fetch(url+'team208/getStudentCourses?userId='+this.props.userID)
      .then(response => response.json())
      .then(data => this.setState({courses: data}));
    console.log("Student Courses",this.state.courses)
  }

  deleteSubmission(){
    this.setState({
      registerCourse: false,
      viewCourses: false,
      newSubmission: false,
      deleteSubmission: true,
    })
  }

  registerCourse(){
    this.setState({
      registerCourse: true,
      viewCourses: false,
      newSubmission: false,
      deleteSubmission: false,
    })
  }

  viewCourses(){
    this.setState({
      registerCourse: false,
      viewCourses: true,
      newSubmission: false,
      deleteSubmission: false,
    })
  }

  newSubmission(){
    this.setState({
      registerCourse: false,
      viewCourses: false,
      newSubmission: true,
      deleteSubmission: false,
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
          <a class="navbar-brand" style={{ color: 'white'}}>Student Page</a>
        </div>
        <div>
          <Button onClick={this.newSubmission}> Submit Assignment </Button>
        </div>
        <div>
          <Button onClick={this.registerCourse}> Register Course </Button>
        </div>
        <div>
          <Button onClick={this.viewCourses}> Drop Courses </Button>
        </div>
        <div>
          <Button onClick={this.deleteSubmission}> Submissions </Button>
        </div>
        <div>
          <Button onClick={this.logout}> Logout </Button>
        </div>
      </div>
    </nav>


    if(this.state.registerCourse){
      return(
        <div>
          {NavBar}
          <br />
          <RegisterForCourse userID={this.props.userID}/>
        </div>
      )
    }
    else if(this.state.newSubmission){
      return(
        <div>
          {NavBar}
          <br />
          <NewSubmission userID={this.props.userID} courses={this.state.courses}/>
        </div>
      )
    }
    else if(this.state.viewCourses){
      return(
        <div>
          {NavBar}
          <br />
          <ViewCourses userID={this.props.userID}/>
        </div>
      )
    }
    else if(this.state.deleteSubmission){
      return(
        <div>
          {NavBar}
          <br />
          <DeleteSubmission userID={this.props.userID}/>
        </div>
      )
    }
    else
    return(
      <div>
        {NavBar}
        <br />
        <NewSubmission userID={this.props.userID} />
      </div>
    )
  }
}
