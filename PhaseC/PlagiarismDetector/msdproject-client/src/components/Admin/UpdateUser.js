import React from 'react';
import { Button } from 'react-bootstrap';
import { Table } from 'reactstrap';
import * as data from '../constants';

const url = data.URL;

export default class UpdateUser extends React.Component{

  constructor(){
    super();

    this.state = {users: [], userID:'', univID:'', name:'', userRole:'', password:'', email:'', successMessage: false, isUpdate: false};

    this.fetchUsers = this.fetchUsers.bind(this);
  }

  componentDidMount() {
    fetch(url+'/team208/all')
      .then(response => response.json())
      .then(data => this.setState({users: data}));
  }

  fetchUsers() {
    fetch(url+'/team208/all')
      .then(response => response.json())
      .then(data => this.setState({users: data}));
  }

  handleUpdate(user) {
    console.log("User ID from update",user.userId)
     this.setState({
       userID: user.userId,
       name: user.name,
       userRole: user.userRole,
       password: user.password,
       email: user.email,
       isUpdate: true,
     })
       console.log("User ID from update",this.state.userID)
  };

  handleClick() {
    console.log("User ID",this.state.userID)
    fetch(url+'/team208/updateUser', {
      method: 'PUT',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json',
       },
       body: JSON.stringify({
         userId: this.state.userID,
	       name : this.state.name,
	       userRole: this.state.userRole,
	       password: this.state.password,
	       email: this.state.email,
       })
     }).then(this.fetchUsers);
     this.setState({
       userID: '',
       name: '',
       userRole: '',
       password: '',
       email: '',
       isUpdate: false,
     })
  };

  update(){
    this.setState({
      name: this.refs.name.value,
      password: this.refs.password.value,
      email: this.refs.email.value,
    })
  }

  handleUserRole(event) {
    this.setState({
      userRole: event.target.value,
    })
  }

  render(){

    let form

    if(this.state.isUpdate){
      form =  <div style={{ padding: '25px'}} className={'container col-md-6 col-md-offset-3'}>
      <form>
        <div class="form-group">
          <label for="name">Name:</label>
          <input name="name" class="form-control" ref="name" type="text"
          value={this.state.name}
          onChange={this.update.bind(this)}></input>
        </div>
        <div class="form-group">
          <label for="email">Email address:</label>
          <input type="email" class="form-control" ref="email"
          value={this.state.email}
          onChange={this.update.bind(this)} />
        </div>
        <div class="form-group">
          <label for="pwd">Password:</label>
          <input type="password" class="form-control" ref="password"
          value={this.state.password}
          onChange={this.update.bind(this)} />
        </div>
        <div class="radio">
          <label><input type="radio" ref="studentRole"
                  checked={this.state.userRole === 'student'} value="student"
                  onChange={this.handleUserRole.bind(this)} />Student</label>
        </div>
        <div class="radio">
          <label><input type="radio" ref="profRole" value="professor"
                  checked={this.state.userRole === 'professor'}
                  onChange={this.handleUserRole.bind(this)} />Professor</label>
        </div>
        <div class={'container text-center'}>
          <Button class={'text-center'} onClick={this.handleClick.bind(this)}> Update </Button>
        </div>
      </form>
      </div>
    }

    return (
      <div>
        <div>
        <UserTable
          onUpdateUser={this.handleUpdate.bind(this)}
          users={this.state.users}/>
        </div>
        {form}
      </div>
    );
  }
}

class UserTable extends React.Component {

  render() {
    var updateUser = this.props.onUpdateUser;
    var user = this.props.users.map(function(user) {
      return (<UserRow
        user={user}
        onUpdateUser={updateUser.bind(this)}
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
              <th>Edit </th>
            </tr>
          </thead>

          <tbody>
            {user}
          </tbody>

        </Table>
      </div>
    );
  }
}


class UserRow extends React.Component {

  onUpdateUser() {
    this.props.onUpdateUser(this.props.user);
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
          <Button onClick={this.onUpdateUser.bind(this)}>Edit</Button>
        </td>
      </tr>
    );
  }
}
