import React from 'react';
import {BrowserRouter as Router, Link, Route} from 'react-router-dom';
import { Table } from 'reactstrap';
import * as data from '../constants';

const url = data.URL;

class UserList extends React.Component{

  constructor(props: any){
    super(props);

    this.state = {
      users: []
    };
  }

  componentDidMount() {
    fetch(url+'/team208/all')
      .then(response => response.json())
      .then(data => this.setState({users: data}));
  }

  updateProfessorRole() {
    console.log("Clicked on Update Role");
    fetch(url+'/team208/registerUser', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          userId: this.state.univID,
          name: this.state.name,
          userRole: this.state.userRole,
          password: this.state.password,
          email: this.state.email,
        })
      }).then(function(response) {
	       return response.json();
       }).then(j =>
          this.setState({
            successMessage: "Saved!"
          })
        );
  }

  render(){
    const users = this.state.users;
    return (
      <div>
      <UserTable className='table'
        users={this.state.users}/>
      </div>
    );
  }
}

class UserTable extends React.Component {

  render() {
    var user = this.props.users.map(function(user) {
      return (<UserRow
        user={user}
        key={user.id}/>)
    });

    return (
      <div>
        <Table className="table table-hover">
          <thead class="thead-dark">
            <tr>
              <th>User ID</th>
              <th>Name</th>
              <th>Role</th>
              <th>Password</th>
              <th>Email ID</th>
            </tr>
          </thead>

          <tbody /*style={{backgroundColor: '#f2f2f2'}}*/>
            {user}
          </tbody>

        </Table>
      </div>
    );
  }
}


class UserRow extends React.Component {

  render() {

    return (
      <tr className="eachRow">
        <td> {this.props.user.userId} </td>
        <td> {this.props.user.name} </td>
        <td> {this.props.user.userRole} </td>
        <td> {this.props.user.password} </td>
        <td> {this.props.user.email} </td>
      </tr>
    );
  }
}


export default UserList
