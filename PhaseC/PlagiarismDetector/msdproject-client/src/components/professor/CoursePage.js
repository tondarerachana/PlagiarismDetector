import React from 'react';
import ReactDOM from 'react-dom';
import { Button , Table} from 'react-bootstrap';
import * as data from '../constants';

const url = data.URL;

export default class CoursePage extends React.Component {

  constructor(props){
    super(props);

    this.state = {
      userID: this.props.userID,
      courses: [],
      createdCourseBy:this.props.userID,
      courseId: '',
      courseAbbr: '',
      courseLoc: '',
      courseName: '',
      courseTerm: '',
      section: '',
      isForm: false,
    };

    this.fetchCourses = this.fetchCourses.bind(this);

  }

  update(){
    this.setState({
      courseAbbr: this.refs.courseAbbr.value,
      courseLoc: this.refs.courseLoc.value,
      courseName: this.refs.courseName.value,
      courseTerm: this.refs.courseTerm.value,
    });
  }

  componentDidMount() {
    fetch(url+'team208/allCourses')
      .then(response => response.json())
      .then(data => this.setState({courses: data}));
  }

  fetchCourses(){
    fetch(url+'team208/allCourses')
      .then(response => response.json())
      .then(data => this.setState({courses: data}));
  }

  handleRowDel(course) {
    fetch(url+'team208/deletCourse?courseId='+course.courseId)
    .then(this.fetchCourses);
  }

  handleEditSubmit() {
    fetch(url+'team208/updateCourse', {
      method: 'PUT',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json',
       },
       body: JSON.stringify({
         courseId: this.state.courseId,
         courseAbbr: this.state.courseAbbr,
         courseLoc: this.state.courseLoc,
         courseName: this.state.courseName,
         courseTerm: this.state.courseTerm,
         sections: this.state.section,
       })
     }).then(this.fetchCourses);
     this.setState({
       courseAbbr: '',
       courseLoc: '',
       courseName: '',
       courseTerm: '',
       isForm: false,
     })
  }

  handleRowUpdate(course) {
    this.setState({
      courseId: course.courseId,
      courseAbbr: course.courseAbbr,
      courseLoc: course.courseLoc,
      courseName: course.courseName,
      courseTerm: course.courseTerm,
      section: course.section,
      isForm: true,
    });
  }

  render(){
    let form

    if(this.state.isForm){
      form =
      <div class={'container col-md-6 col-md-offset-3'}>
        <form>
          <div class="form-group">
            <label> Course Abbreviation:</label>
            <input type="text" class="form-control" ref="courseAbbr"
                    placeholder="Course Name"
                    value={this.state.courseAbbr}
                    onChange={this.update.bind(this)}/>
          </div>
          <div class="form-group">
            <label> Course Location:</label>
            <input type="text" class="form-control" name="courseLoc" ref="courseLoc"
                  placeholder="Location"
                  value={this.state.courseLoc}
                  onChange={this.update.bind(this)}/>
          </div>
          <div class="form-group">
            <label> Course Name:</label>
            <input type="text" class="form-control" name="courseName"
                  ref="courseName" placeholder="Title"
                  value={this.state.courseName}
                  onChange={this.update.bind(this)}/>
          </div>
          <div class="form-group">
            <label> Course Term: </label>
            <input type="text" class="form-control" name="courseTerm" ref="courseTerm"
                  placeholder="Course Term"
                  value={this.state.courseTerm}
                  onChange={this.update.bind(this)}/>
          </div>
          <div class={'container text-center'}>
            <Button onClick={this.handleEditSubmit.bind(this)}> Update </Button>
          </div>
        </form>
      </div>

    }

    const courses = this.state.courses;
    return (
      <div>
      <UserTable onRowDel={this.handleRowDel.bind(this)}
                 courses={this.state.courses}
                 onRowUpdate={this.handleRowUpdate.bind(this)} />
        {form}
      </div>
    );
  }
}

class UserTable extends React.Component {

  render() {
    var rowDel = this.props.onRowDel;
    var rowUpdate = this.props.onRowUpdate;
    var course = this.props.courses.map(function(course) {
      return (<CourseRow
        course={course}
        onRegisterEvent={rowUpdate.bind(this)}
        onDeleteEvent={rowDel.bind(this)}
        key={course.id}/>)
    });
    return (
      <div>

        <Table className="table table-hover">
          <thead class="thead-dark">
            <tr>
              <th>Course ID</th>
              <th>Course Title</th>
              <th>Course Name</th>
              <th>Term</th>
              <th>Location</th>
              <th>Section </th>
              <th>Udpate </th>
              <th>Delete </th>
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

  onDeleteEvent() {
    this.props.onDeleteEvent(this.props.course);
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
          <Button onClick={this.onRegisterEvent.bind(this)}>Edit</Button>
        </td>
        <td>
          <Button onClick={this.onDeleteEvent.bind(this)}>Delete</Button>
        </td>
      </tr>
    );
  }
}
