import React from 'react';
import ReactDOM from 'react-dom';
import ViewAllSubmissions from './ViewAllSubmissions'
import { Button , Table} from 'react-bootstrap';
import * as data from '../constants';

const url = data.URL;

export default class DeleteSubmission extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      userID: this.props.userID,
      submissionID: '',
      submissions: [],
      gitLink: '',
      isForm: false,
    }
  }

  componentDidMount() {
    console.log("UserID from view all courses per student ", this.state.userID)
    fetch(url+'team208/getStudentSubmissions?userId='+this.props.userID)
      .then(response => response.json())
      .then(data => this.setState({submissions: data}));
    console.log("Submissions",this.state.submissions)
  }

  fetchSubmissions(userID){
    console.log("User ID from fetch submissions: ",userID)

    fetch(url+'team208/getStudentSubmissions?userId='+userID)
      .then(response => response.json())
      .then(data => this.setState({submissions: data}));
  }


  handleRowDel(submission) {
    console.log("Submission ID from submissions:",submission.submissionId)
    console.log("User ID: ",this.state.userID)
    let userID = this.state.userID
    fetch(url+'team208/deletSubmission?submissionId='+submission.submissionId)
        .then(this.fetchSubmissions(userID));
  };

  handleRowUpdate(submission){
    console.log("From handleRowUpdate")
    this.setState({
      isForm: true,
      submissionID: submission.submissionId
    })

  }

  update(){
    console.log("From update form")
    this.setState({
      gitLink: this.refs.gitLink.value,
    })
  }

  handleClick(){
    console.log("Updating Submission:",this.state.submissionID)
    let userID = this.state.userID
    fetch(url+'team208/updateSubmission', {
      method: 'PUT',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json',
       },
       body: JSON.stringify({
         submissionId: this.state.submissionID,
	       gitLink : this.state.gitLink,

       })
     }).then(this.fetchSubmissions(userID));
  }

  render(){
    let gitForm

    if(this.state.isForm){
      gitForm = <div>
      <input type="text" name="gitLink" ref="gitLink" placeholder="gitlink"
            value={this.state.name}
            onChange={this.update.bind(this)} />
      <Button onClick={this.handleClick.bind(this)}> Update </Button>
            </div>
    }

    let submissions = this.state.submissions;
    console.log("Submisiions: ",submissions)
    console.log("user:",this.state.userID)
    return(
      <div>
        <UserTable onRowDel={this.handleRowDel.bind(this)}
        onRowUpdate={this.handleRowUpdate.bind(this)}
        submissions={this.state.submissions} />
        {gitForm}
      </div>
    );
  }
}

class UserTable extends React.Component {

  render() {
    var rowDel = this.props.onRowDel;
    var rowUpdate = this.props.onRowUpdate;
    var submission = this.props.submissions.map(function(submission) {
      return (<UserRow  submission={submission}
        onDelEvent={rowDel.bind(this)}
        onUpdateEvent = {rowUpdate.bind(this)}
        key={submission.id}/>)
    });
    return (
      <div>

        <Table class="table table-hover">
          <thead class="thead-dark">
            <tr>
              <th>Submission ID</th>
              <th>Assignment Name</th>
              <th>Course Name</th>
              <th>Git Link</th>
              <th>Due Date</th>
              <th> Delete </th>
              <th> Update Link </th>
            </tr>
          </thead>

          <tbody>
            {submission}
          </tbody>

        </Table>

      </div>
    );
  }
}

class UserRow extends React.Component {
  onDelEvent() {
    this.props.onDelEvent(this.props.submission);
  }

  onUpdateEvent() {
    this.props.onUpdateEvent(this.props.submission);
  }

  render() {

    return (
      <tr class="eachRow">
        <td> {this.props.submission.submissionId} </td>
        <td> {this.props.submission.assignmentName} </td>
        <td> {this.props.submission.courseAbbr} </td>
        <td> <a> {this.props.submission.gitLink} </a> </td>
        <td> {this.props.submission.submissionTime} </td>
        <td>
          <Button onClick={this.onDelEvent.bind(this)}>Delete</Button>
        </td>
        <td>
          <Button onClick={this.onUpdateEvent.bind(this)}>Update</Button>
        </td>
      </tr>
    );
  }
}
