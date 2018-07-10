import React from 'react';
import ReactDOM from 'react-dom';
import { Button , Table} from 'react-bootstrap';
import CourseList from './CourseList'
import Popup from "reactjs-popup";
import * as data from '../constants';

const url = data.URL;

export default class AssignmentsPage extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      courseId: '',
      courseTerm: '',
      assignements: [],
      assignmentNo: '',
      assignmentName: '',
      submissionDate: '',
      courses: [],
      selectedCourse: '',
      isForm: false,
      courseAbbr: '',
      assignmentId: '',
      threshold: '',
      lang: '',
      submissions: [],
      reports: [],
      gotSubmissions: false,
      resultReports: [],
      userID: this.props.userID,
      user: [],
      terms: [],
      checkByTerms: false,
    }

    this.fetchCourses = this.fetchCourses.bind(this);
    this.fetchAssignments = this.fetchAssignments.bind(this);
    this.fetchEmailFromID = this.fetchEmailFromID.bind(this);
    this.fetchTermsByCourse = this.fetchTermsByCourse.bind(this);
  }

  fetchTermsByCourse(){
    let terms
    fetch(url+'team208/CourseByAbbr?courseAbbr='+this.state.courseAbbr)
      .then(response => response.json())
      .then(data => this.setState({terms: data, checkByTerms: true}))
  }

  getSubmissionsPerTerm(){
    let terms = this.state.terms;
    let postBody = {
      "courseAbbr": this.state.courseAbbr,
      "assignmentNo": this.state.assignmentNo,
      "term": terms
    }
    fetch(url+'team208/allSubmissionsByCourseMultipleTerms', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(postBody)
       }).then(response => response.json())
       .then(data => this.setState({submissions: data,
                                   gotSubmissions: true}))
       .catch(function() {
         alert("Failed to fetch submissions")});
  }

  fetchCourses(){
    fetch(url+'team208/allCourses')
      .then(response => response.json())
      .then(data => this.setState({courses: data}));
  }

  fetchEmailFromID(id){
    fetch(url+'team208/findStudent?userId='+id)
      .then(response => response.json())
      .then(data => this.setState({user: data.user}));
    console.log("Email:",this.state.user.email)
    return this.state.user.email
  }

  fetchAssignments(course){
    this.setState({
      courseId: course.courseId,
      courseAbbr: course.courseAbbr,
      courseTerm: course.courseTerm,
    })
    fetch(url+'team208/assignmentsByCourse?courseId='+course.courseId)
      .then(response => response.json())
      .then(data => this.setState({assignments: Object.values(data)[0],
                                    selectedCourse: course }));
  }

  componentDidMount() {
    fetch(url+'team208/allCourses')
      .then(response => response.json())
      .then(data => this.setState({courses: data}));
  }

  onUpdateAssignment(assignment){
    this.setState({
      isForm:true,
      assignmentNo: assignment.assignmentNo,
      assignmentId: assignment.assignmentId,
    })
  }

  handleFormSubmit(){
    fetch(url+'team208/updateAssignment', {
        method: 'PUT',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          courseId: this.state.selectedCourse.courseId,
          assignmentNo: this.state.assignmentNo,
          assignmentName: this.state.assignmentName,
          submissionDate: this.state.submissionDate,
        })
      }).then(function(response) {
         return response.json();
       }).then(j =>
          this.setState({
            assignmentNo: '',
            assignmentName: '',
            submissionDate: '',
            isForm: false,
          })
        ).catch(function() {
          alert("Error updating a new assignment. Please try again.")
        });
  }

  update(){
    this.setState({
      threshold: this.refs.threshold.value,
      lang: this.refs.lang.value,
    });
  }

  fetchBySections(){
    fetch(url+'team208'+
    '/allSubmissionsByCourse?courseAbbr='+this.state.courseAbbr+'&assignmentNo='+this.state.assignmentNo+'&term='+this.state.courseTerm+'&sections=1,2')
      .then(response => response.json())
      .then(data => this.setState({submissions: data,
                                  gotSubmissions: true}))
      .catch(function() {
        alert("Failed to get submissions")
      });
  }

  getSubmissions(){
    fetch(url+'team208'+
    '/allSubmissionsByCourse?courseAbbr='+this.state.courseAbbr+'&assignmentNo='+this.state.assignmentNo+'&term='+this.state.courseTerm+'&sections=1')
      .then(response => response.json())
      .then(data => this.setState({submissions: data,
                                  gotSubmissions: true}))
      .catch(function() {
        alert("Failed to get submissions")
      });
  }


  checkPlaigarism(){
    //Fetch Submissions
    this.getSubmissions()
  }

  test(input) {
        var data = input.Data.split(",");
        var result = [];
        for(var i = 0 ; i < data.length;){
          var j = 0;

        var temp = {
            "id1" : data[i].replace("{",""),
            "id2" : data[i + 1],
            "s3Link" : data[i + 4].replace("}",""),
            "percentage" : data[i + 3].split("=")[1]
        }
        i = i + 5;
        result.push(temp);

    }
    return result

}

  generateReport(){
    console.log("From generate Report")
    console.log("submissions",JSON.stringify(this.state.submissions))

    fetch(url+'team208/generateReport?courseId='+this.state.courseId+
    '&assignId='+this.state.assignmentId+'&threshold='+this.state.threshold+
    '&lang='+this.state.lang+'&courseName='+this.state.courseAbbr, {
      method: 'POST',
      mode: 'cors',
       headers: {
         'Content-Type': 'application/json',
         'Access-Control-Allow-Origin':'*'
       },
       body: JSON.stringify(this.state.submissions)
     }).then(response => response.json())
     .then(data => this.setState({reports: data}))
     .catch(function() {
       alert("Failed to Generate Reports")
     });
     console.log("Reports1:",this.state.reports)
  }

  generateReport2(){
    console.log("From generate Report2")
    console.log("submissions",JSON.stringify(this.state.submissions))

    fetch(url+'team208/generateReport?courseId='+this.state.courseId+'&assignId='+this.state.assignmentId+'&threshold='+this.state.threshold+'&lang='+this.state.lang, {
      method: 'POST',
      mode: 'cors',
       headers: {
         'Content-Type': 'application/json',
         'Access-Control-Allow-Origin':'*'
       },
       body: JSON.stringify(this.state.submissions)
     }).then(response => response.json())
     .then(data => this.setState({reports: data}))
     .catch(function() {
       alert("Failed to Generate Reports")
     });
     console.log("Reports1:",this.state.reports)
  }

  onEmailStudents(report){
    this.fetchEmailFromID(report.id1)
    this.fetchEmailFromID(report.id1)
    let student1Email = this.fetchEmailFromID(report.id1)
    let student2Email = this.fetchEmailFromID(report.id2)
    let postBody = [{
		    "email": student1Email,
		    "content": "CAUGHT",
		    "link":""
	   },
	   {
		     "email": student2Email,
		     "content": "CAUGHT",
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

  onEmailReports(report){
    console.log("s3Link:",report.s3Link)
    console.log("Prof ID:",this.state.userID)
    console.log("FROM EMAIL REPORTS:",this.fetchEmailFromID(this.state.userID))
    let profEmail = this.fetchEmailFromID(this.state.userID)
    let postBody = [{
		    "email": profEmail,
		    "content": "REPORT",
		    "link": report.s3Link
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
    let courses = this.state.courses
    let assignments = this.state.assignments
    let assignmentsComp
    let form
    let generateReport
    let generateReport2
    let generatingReports
    let viewReports
    let generateReportByTerms


    if(this.state.checkByTerms){
      generateReportByTerms = <div><Button onClick={this.getSubmissionsPerTerm.bind(this)}> Fetch Submissions per Terms </Button></div>
    }

    if(this.state.gotSubmissions){
      generateReport = <div class={'container text-center'}>
          <Button  onClick={this.generateReport.bind(this)}> Generate Reports </Button>
          </div>
      generateReport2 = <div class={'container text-center'}>
      <Button class={'container text-center'} onClick={this.generateReport2.bind(this)}> Generate Reports for Zip Uploads</Button>
      </div>
      generatingReports = <div class={'container text-center'}>
      <h1> Click on Generate Reports and please wait while reports are being generated. </h1>
      </div>
    }

    if(this.state.reports.length!=0){
      let reportLinks = this.test(this.state.reports)
      generatingReports = <div> <h1> Reports Generated! </h1>
      <ReportTable reports={reportLinks}
                    onEmailStudents={this.onEmailStudents.bind(this)}
                    onEmailReports={this.onEmailReports.bind(this)}/>
       </div>
    }

    if(this.state.isForm){
      form =
      <div class={'container text-center'}>
      <input type="text" ref="threshold"
            placeholder="Threshold"
            onChange={this.update.bind(this)} />
      <select ref="lang" value={this.state.lang} onChange={this.update.bind(this)}>
        <option value="java11">Java1.1</option>
        <option value="java12">Java1.2</option>
        <option value="java15">Java1.5</option>
        <option value="java17">Java1.7</option>
        <option value="python3">Python3</option>
      </select>
      <br />
      <br />
      <h4>Choose any one</h4>
      <br />
      <Button onClick={this.fetchTermsByCourse.bind(this)}> Fetch by Terms</Button>
      <br />
      <br />
      <Button onClick={this.fetchBySections.bind(this)}> Fetch by Sections</Button>
      <br />
      <br />
      <Button onClick={this.checkPlaigarism.bind(this)}> Fetch Submissions </Button>
      <br />
      <br />
      </div>
    }

    if(this.state.assignments) {
      assignmentsComp = <AssignmentTable
                        assignments={this.state.assignments}
                        onAssignmentUpdate={this.onUpdateAssignment.bind(this)} />
    }

    return(
      <div>
        <CourseTable
          onViewAssignments={this.fetchAssignments.bind(this)}
          courses={this.state.courses}/>
        {assignmentsComp}
        {form}
        {generateReportByTerms}
        {generateReport}
        <br />
        <br />
        {generatingReports}
      </div>
    );
  }
}

class CourseTable extends React.Component {

  render() {
    var viewAssignments = this.props.onViewAssignments;
    var course = this.props.courses.map(function(course) {
      return (<CourseRow
        course={course}
        onRowViewAssignments={viewAssignments.bind(this)}
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
              <th>View Assignments</th>
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

  onClickEvent(){
    this.props.onRowViewAssignments(this.props.course);
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
          <Button onClick={this.onClickEvent.bind(this)}>View Assignments</Button>
        </td>
      </tr>
    );
  }
}

class AssignmentTable extends React.Component {

  render() {
    var rowUpdate = this.props.onAssignmentUpdate;
    var assignment = this.props.assignments.map(function(assignment) {
      return (<AssignmentRow
        onUpdateAssignmentEvent={rowUpdate.bind(this)}
        assignment={assignment}
        key={assignment.id}/>)
    });

    return (
      <div>
        <Table class="table table-hover">
          <thead class="thead-dark">
            <tr>
              <th>Assignment ID</th>
              <th>Assignment Number</th>
              <th>Assignment Name</th>
              <th>Submission Date</th>
              <th> Udpate </th>
            </tr>
          </thead>

          <tbody>
            {assignment}
          </tbody>

        </Table>
      </div>
    );
  }
}


class AssignmentRow extends React.Component {

  onUpdateAssignmentEvent(){
    this.props.onUpdateAssignmentEvent(this.props.assignment)
  }

  render() {
    return (
      <tr class="eachRow">
        <td> {this.props.assignment.assignmentId} </td>
        <td> {this.props.assignment.assignmentNo} </td>
        <td> {this.props.assignment.assignmentName} </td>
        <td> {this.props.assignment.submissionDate} </td>
        <td>
          <Button onClick={this.onUpdateAssignmentEvent.bind(this)}>Check Plaigarism</Button>
        </td>
      </tr>
    );
  }
}


class ReportTable extends React.Component {

  render() {
    var onEmailStudents = this.props.onEmailStudents;
    var onEmailReports = this.props.onEmailReports;
    var report = this.props.reports.map(function(report) {
      return (<ReportRow
        report={report}
        onEmailStudents={onEmailStudents.bind(this)}
        onEmailReports={onEmailReports.bind(this)}
        key={report.id}/>)
    });

    return (
      <div>
        <Table class="table table-hover">
          <thead class="thead-dark">
            <tr>
              <th>Student ID1</th>
              <th>Student ID2</th>
              <th>s3Link</th>
              <th>Percentage match</th>
              <th> Email Students </th>
              <th> Email Reports </th>
            </tr>
          </thead>

          <tbody /*style={{backgroundColor: '#f2f2f2'}}*/>
            {report}
          </tbody>

        </Table>
      </div>
    );
  }
}


class ReportRow extends React.Component {

  onEmailStudents() {
    this.props.onEmailStudents(this.props.report)
  }

  onEmailReports(){
    this.props.onEmailReports(this.props.report)
  }

  render() {
    return (
      <tr class="eachRow">
        <td> {this.props.report.id1} </td>
        <td> {this.props.report.id2} </td>
        <td> <a href={this.props.report.s3Link}> {this.props.report.s3Link} </a> </td>
        <td> {this.props.report.percentage} </td>
        <td>
        <Popup trigger={<Button onClick={this.onEmailStudents.bind(this)}>Send</Button>} position="right center">
        <div>Email Sent !!</div>
        </Popup>
        </td>
        <td>
        <Popup trigger={<Button onClick={this.onEmailReports.bind(this)}>Send</Button>} position="right center">
          <div>Email Sent !!</div>
        </Popup>

        </td>
      </tr>
    );
  }
}
