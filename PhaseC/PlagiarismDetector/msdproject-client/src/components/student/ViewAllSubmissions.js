import React from 'react';
import ReactDOM from 'react-dom';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import * as data from '../constants';

const url = data.URL;

export default class ViewAllSubmissions extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      userID: this.props.userID,
      submissions: [],
    }
  }

  componentDidMount() {
    console.log("UserID from view all courses per student ", this.state.userID)
    fetch(url+'team208/getStudentSubmissions?userId='+this.state.userID)
      .then(response => response.json())
      .then(data => this.setState({submissions: data}));
    console.log("Submissions",this.state.submissions)
  }

  render(){
    console.log("userId: ",this.state.userID)
    const submissions = this.state.submissions;
    console.log("submissions",submissions)

    return(
      <div>
      <h3> My Submisssions </h3>
      <div className={'container col-md-6 col-md-offset-3'}>
      <BootstrapTable data={submissions} striped condensed hover>
        <TableHeaderColumn isKey dataField='submissionId'>Submission ID</TableHeaderColumn>
        <TableHeaderColumn dataField='assignmentName'>Assignment Name</TableHeaderColumn>
        <TableHeaderColumn dataField='courseAbbr'>Course Name</TableHeaderColumn>
        <TableHeaderColumn dataField='gitLink'>Git Link</TableHeaderColumn>
        <TableHeaderColumn dataField='submissionTime'>Due Date</TableHeaderColumn>
      </BootstrapTable>
      </div>
      </div>
    );
  }
}
