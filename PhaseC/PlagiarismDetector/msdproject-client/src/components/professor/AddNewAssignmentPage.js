import React from 'react';
import { Button , Table} from 'react-bootstrap';
import * as data from '../constants';

const url = data.URL;

export default class AddNewAssignmentsPage extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      courseId: '',
      assignmentNo: '',
      assignmentName: '',
      submissionDate: '',
      courses: [],
      isForm: false,
    }
  }

update(date){
  this.setState({
    courseId: this.refs.courseId.value,
    assignmentNo: this.refs.assignmentNo.value,
    assignmentName: this.refs.assignmentName.value,
    submissionDate: this.refs.yyyy.value+"-"+this.refs.mm.value+"-"+this.refs.dd.value,
  })
}

componentDidMount() {
  fetch(url+'team208/allCourses')
    .then(response => response.json())
    .then(data => this.setState({courses: data}));
}

handleRowUpdate(course) {
  this.setState({
    courseId: course.courseId,
    isForm: true,
  });
}

handleClick(){

  fetch(url+'team208/addAssignment', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        courseId: this.state.courseId,
        assignmentNo: this.state.assignmentNo,
        assignmentName: this.state.assignmentName,
        submissionDate: this.state.submissionDate,
      })
    }).then(function(response) {
       return response.json();
     }).then(j =>
        this.setState({
          courseAbbr: '',
          courseLoc:'',
          courseName:'',
          courseTerm:'',
        })
      ).catch(function() {
        alert("Error adding a new assignment. Please try again.")
      });

      this.setState({
        courseId: '',
        assignmentNo: '',
        assignmentName: '',
        submissionDate: '',
        isForm: false,
      })
}


  render(){
    let form
    if(this.state.isForm){
      form =
      <div class={'container col-md-6 col-md-offset-3'}>
        <form>
          <div class="form-group">
            <label for="courseID">Course ID:</label>
            <input
                class="form-control"
                type="text"
                name="courseId"
                ref="courseId"
                value={this.state.courseId}
                onChange={this.update.bind(this)}/>
          </div>
          <div class="form-group">
            <label for="assignmentNo">Assignment Number:</label>
            <input class="form-control" type="number" name="assignmentNo" ref="assignmentNo" placeholder="Assignment Number"
                  onChange={this.update.bind(this)}/>
          </div>
          <div class="form-group">
            <label for="assignmentName">Assignment Name:</label>
            <input class="form-control" type="text" name="assignmentName" ref="assignmentName" placeholder="Assignment Name"
                  onChange={this.update.bind(this)}/>
          </div>
          <div class="form-group">
            <label for="submissionDate"> Submission Date </label>
            <input class="form-control" type="text" name="yyyy" ref="yyyy" placeholder="yyyy"
                  onChange={this.update.bind(this)}/>
            <input class="form-control" type="text" name="mm" ref="mm" placeholder="mm"
                  onChange={this.update.bind(this)}/>
            <input class="form-control" type="text" name="dd" ref="dd" placeholder="dd"
                  onChange={this.update.bind(this)}/>
          </div>
          <div class={'container text-center'}>
            <Button onClick={this.handleClick.bind(this)}> Submit </Button>
          </div>
        </form>
        </div>
    }

    return(
      <div>
        <UserTable
                   courses={this.state.courses}
                   onRowUpdate={this.handleRowUpdate.bind(this)} />
        {form}
      </div>
    );
  }
}

class UserTable extends React.Component {

  render() {
    var rowUpdate = this.props.onRowUpdate;
    var course = this.props.courses.map(function(course) {
      return (<CourseRow
        course={course}
        onRegisterEvent={rowUpdate.bind(this)}
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
              <th> Section </th>
              <th>Add assignment </th>
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
      <tr className="eachRow">
        <td> {this.props.course.courseId} </td>
        <td> {this.props.course.courseName} </td>
        <td> {this.props.course.courseAbbr} </td>
        <td> {this.props.course.courseTerm} </td>
        <td> {this.props.course.courseLoc} </td>
        <td> {this.props.course.section} </td>
        <td>
          <Button onClick={this.onRegisterEvent.bind(this)}>Add</Button>
        </td>
      </tr>
    );
  }
}
