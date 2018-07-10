import React from 'react';
import ReactDOM from 'react-dom';
import * as data from '../constants';
import { Button } from 'react-bootstrap';

// import the components
import Dropzone from 'react-dropzone';
import request from 'superagent';

const url = data.URL;

//'application/java-archive'
export default class Uploader extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      courseAbbr: this.props.courseAbbr,
      assignmentName: this.props.assignmentName,
      fileName: '',
      studentId: this.props.studentId,
      submitAssignmentId: this.props.submitAssignmentId,
    }

    this.dropHandler = this.dropHandler.bind(this);
  }

  handleClick(){
    console.log("Assignment ID: ",this.state.submitAssignmentId)
    console.log("Student ID: ",this.state.studentId)
    console.log("FileName: ",this.state.fileName)
    console.log("gitlink: "+"DownloadReports2/"+this.state.courseAbbr+"/"+this.state.assignmentName+"/"+this.state.fileName)
    console.log("from uploader:",url+'team208/submitSubmission')
    fetch(url+'team208/submitSubmission', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          assignmentId: this.state.submitAssignmentId,
          studentId: this.state.studentId,
          gitLink: "DownloadedReports2/"+this.state.courseAbbr+"/"+this.state.assignmentName+"/"+this.state.fileName,
        })
      }).then(function(response) {
         return response.json();
       })
  }

  dropHandler(file) {
    console.log("handle file drop")
    console.log("Course: "+this.state.courseAbbr)
    console.log("Assignment: "+this.state.assignmentName)
    var jsonFile = new FormData();
    jsonFile.append('file', file[0]);
    jsonFile.append('name', file[0].name);
    this.setState({
      fileName: file[0].name
    })
    request.post(url+'upload?course='+this.state.courseAbbr+"&hw="+this.state.assignmentName)
    .send(jsonFile)
    .end(function(err, resp) {
      if (err) {
        console.error(err);
      }
      return resp;
    });
  }


  render() {
    return (
      <div>
        <Dropzone disableClick={false} multiple={false} accept={'application/zip'} onDrop={this.dropHandler}>
          <div> Drop a Zip file, or click to add. < /div >
        </Dropzone>
        <Button onClick={this.handleClick.bind(this)}> Submit </Button>
      </div>
    );
  }
}
