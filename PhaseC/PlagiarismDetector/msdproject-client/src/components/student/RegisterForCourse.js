import React from 'react';
import ReactDOM from 'react-dom';
import { Button , Table} from 'react-bootstrap';
import * as data from '../constants';

const url = data.URL;

export default class RegisterForCourse extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      userID: this.props.userID,
      courses: [],
      studentCourses: [],
      user: [],
    };
    this.fetchUsers = this.fetchUsers.bind(this);
  }

  componentDidMount() {
    fetch(url+'team208/allCourses')
      .then(response => response.json())
      .then(data => this.setState({courses: data}));

      fetch(url+'team208/findStudent?userId='+this.state.userID)
          .then(response => response.json())
          .then(data => this.setState({user: data.user}));
        console.log("Email:",this.state.user.email)
  }

  fetchUsers() {
    console.log("User ID from fetch users",this.state.userID)
    fetch(url+'team208/getStudentCourses?userId='+this.state.userID)
      .then(response => response.json())
      .then(data => this.setState({studentCourses: data}));
    console.log("Student Courses",this.state.studentCourses)
  }

  handleRowDel(course) {
    fetch(url+'team208/registerStudentCourses?userId='
      +this.state.userID+"&courseId="+course.courseId)
    .catch(function() {
      alert("Registration completed.")
    });

    let postBody = [{
        "email": this.state.user.email,
        "content": "ADD",
        "link":""
     }]
    fetch(url+'team208/Email', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin':'*'
      },
      body: JSON.stringify(postBody)
     }).then(response => response.json())
     .catch(function() {
       alert("Failed to send Email")});

  }

  render(){
    const courses = this.state.courses;
    console.log("Student Courses"+this.state.studentCourses)
    return (
      <div>
        <UserTable onRowDel={this.handleRowDel.bind(this)} courses={this.state.courses} />
      </div>
    );
  }
}

class UserTable extends React.Component {

  render() {
    var rowDel = this.props.onRowDel;
    var course = this.props.courses.map(function(course) {
      return (<CourseRow  course={course} onRegisterEvent={rowDel.bind(this)} key={course.id}/>)
    });
    return (
      <div>

        <Table class="table table-hover">
          <thead class="thead-dark">
            <tr>
              <th>Course ID</th>
              <th>Course Title</th>
              <th>Course Name</th>
              <th>Term</th>
              <th>Location</th>
              <th>Section</th>
              <th>Register </th>
            </tr>
          </thead>

          <tbody>
            {course}
          </tbody>

        </Table>
      </div>
    );
  }
}

class CourseRow extends React.Component {
  onRegisterEvent() {
    this.props.onRegisterEvent(this.props.course);
  }

  render() {

    return (
      <tr class="eachRow">
        <td> {this.props.course.courseId} </td>
        <td> {this.props.course.courseName} </td>
        <td> {this.props.course.courseAbbr} </td>
        <td> {this.props.course.courseTerm} </td>
        <td> {this.props.course.courseLoc} </td>
        <td> {this.props.course.section} </td>
        <td>
          <Button onClick={this.onRegisterEvent.bind(this)}>Register</Button>
        </td>
      </tr>
    );
  }
}
