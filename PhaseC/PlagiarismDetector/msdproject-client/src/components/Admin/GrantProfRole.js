import React from 'react';
import { Button } from 'react-bootstrap';
import * as data from '../constants';

const url = data.URL;

export default class GrantProfRole extends React.Component {

  constructor(props) {
    super(props);
    this.state = {};
    this.state.filterText = "";
    this.state.users = [];
    this.fetchUsers = this.fetchUsers.bind(this);
  }

  componentDidMount() {
    fetch(url+'team208/all')
      .then(response => response.json())
      .then(data => this.setState({users: data}));
  }

  fetchUsers() {
    fetch(url+'team208/all')
      .then(response => response.json())
      .then(data => this.setState({users: data}));
  }

  handleProfUpdate(user) {
    fetch(url+'team208/updateUser', {
      method: 'PUT',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json',
       },
       body: JSON.stringify({
         userId: user.userId,
	       name : user.name,
	       userRole: 'professor',
	       password: user.password,
	       email: user.email,
       })
     }).then(this.fetchUsers);
  };

  handleAdminUpdate(user){
    fetch(url+'team208/admin/user/'+user.userId, {
      method: 'PUT',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json',
       },
       body: JSON.stringify({
         userRole: 'admin'
       })
     }).then(this.fetchUsers);
  };

  render() {
    return (
      <div>
        <UserTable
        onProfUpdate={this.handleProfUpdate.bind(this)}
        onAdminUpdate={this.handleAdminUpdate.bind(this)}
        users={this.state.users}/>
      </div>
    );
  }
}

class UserTable extends React.Component {

  render() {
    var profUpdate = this.props.onProfUpdate;
    var adminUpdate = this.props.onAdminUpdate;
    var temp_profs = this.props.users.filter((prof) => prof.userRole === "professor-temp" );
    var user = temp_profs.map(function(user) {
      return (<UserRow
        user={user}
        onGrantProfRole={profUpdate.bind(this)}
        onMakeAdmin={adminUpdate.bind(this)}
        key={user.id}/>)
    });

    return (
      <div>
        <table className="table table-hover">
          <thead class="thead-dark">
            <tr>
              <th>User ID</th>
              <th>Name</th>
              <th>Role</th>
              <th>Password</th>
              <th>Email ID</th>
              <th>Grant Professor Role </th>
            </tr>
          </thead>

          <tbody>
            {user}
          </tbody>

        </table>
      </div>
    );
  }
}

class UserRow extends React.Component {
  onGrantProfRole() {
    this.props.onGrantProfRole(this.props.user);
  }

  onMakeAdmin() {
    this.props.onMakeAdmin(this.props.user);
  }

  render() {

    return (
      <tr className="eachRow">
        <td> {this.props.user.userId} </td>
        <td> {this.props.user.name} </td>
        <td> {this.props.user.userRole} </td>
        <td> {this.props.user.password} </td>
        <td> {this.props.user.email} </td>
        <td>
          <Button onClick={this.onGrantProfRole.bind(this)}>GrantProfRole</Button>
        </td>
      </tr>
    );
  }
}
