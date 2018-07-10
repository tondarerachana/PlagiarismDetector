import React from 'react';
import ReactDOM from 'react-dom';
import { Button , Table} from 'react-bootstrap';
import * as data from '../constants';

const url = data.URL;

export default class ViewCourses extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      userID: this.props.userID,
      courses: [],
      user: [],
    }
    this.fetchCourses = this.fetchCourses.bind(this);
  }

  componentDidMount() {
    console.log("UserID from view all courses per student ", this.state.userID)
    fetch(url+'team208/getStudentCourses?userId='+this.state.userID)
      .then(response => response.json())
      .then(data => this.setState({courses: data}));
    fetch(url+'team208/findStudent?userId='+this.state.userID)
        .then(response => response.json())
        .then(data => this.setState({user: data.user}));
      console.log("Email:",this.state.user.email)
  }

  fetchCourses(){
    console.log("UserID from view all courses per student ", this.state.userID)
    console.log("Email:",this.state.user.email)
    fetch(url+'team208/getStudentCourses?userId='+this.state.userID)
      .then(response => response.json())
      .then(data => this.setState({courses: data}));
  }

  handleRowDel(course) {
    fetch(url+'team208/dropCourse?userId='+this.state.userID+'&courseId='+course.courseId)
      .then(response => response.json())
      .then(this.fetchCourses);
      let postBody = [{
  		    "email": this.state.user.email,
  		    "content": "DROP",
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
  };

  render(){
    console.log("Courses: ",this.state.courses)
    console.log("Email:",this.state.user.email)
    const courses = this.state.courses;
    let table
    if(courses){
      table = <UserTable
                onRowDel={this.handleRowDel.bind(this)}
                courses={this.state.courses} />
    }

    return(
      <div>
          {table}
      </div>
    );
  }
}


class UserTable extends React.Component {

  render() {
    var rowDel = this.props.onRowDel;
    var course = this.props.courses.map(function(course) {
      return (<CourseRow
        course={course}
        onDelEvent={rowDel.bind(this)}
        key={course.id}/>)
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
              <th> Drop Course </th>
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

  onDelEvent() {
    this.props.onDelEvent(this.props.course);
  }

  render() {

    return (
      <tr className="eachRow">
        <td> {this.props.course.courseId} </td>
        <td> {this.props.course.courseName} </td>
        <td> {this.props.course.courseAbbr} </td>
        <td> {this.props.course.courseTerm} </td>
        <td> {this.props.course.courseLoc} </td>
        <td> {this.props.course.section} </td>
        <td>
          <Button onClick={this.onDelEvent.bind(this)}>Drop Course</Button>
        </td>
      </tr>
    );
  }
}
