import React from 'react';
import {BootstrapTable, TableHeaderColumn, Button, Nav, NavItem} from 'react-bootstrap';
import UserList from './UserList.js'
import NewUser from './NewUser.js'
import DeleteUser from './DeleteUser.js'
import GrantProfRole from './GrantProfRole.js'
import UpdateUser from './UpdateUser.js'


class AdminPage extends React.Component{

  constructor(props: any){
    super(props);

    this.state = {
      usersList: false,
      newUser: false,
      grantProfRole: false,
      deleteUser: false,
      updateUser: false
    };

    this.handleClick = this.handleClick.bind(this);
    this.registerUser = this.registerUser.bind(this);
    this.updateUser = this.updateUser.bind(this);
    this.deleteUser = this.deleteUser.bind(this);
    this.grantProfRole = this.grantProfRole.bind(this);
    this.handlelogout = this.handlelogout.bind(this);
  }

  handlelogout(){
    localStorage.clear();
    window.location.reload();
  }

  handleClick(){
    this.setState({
      usersList: true,
      newUser: false,
      grantProfRole: false,
      deleteUser: false,
      updateUser: false
    })
  }

  registerUser(){
    this.setState({
      usersList: false,
      newUser: true,
      grantProfRole: false,
      deleteUser: false,
      updateUser: false
    })
  }

  updateUser(){
    this.setState({
      usersList: false,
      newUser: false,
      grantProfRole: false,
      deleteUser: false,
      updateUser: true
    })
  }

  deleteUser(){
    this.setState({
      usersList: false,
      newUser: false,
      grantProfRole: false,
      deleteUser: true,
      updateUser: false
    })
  }

  grantProfRole(){
    this.setState({
      usersList: false,
      newUser: false,
      grantProfRole: true,
      deleteUser: false,
      updateUser: false
    })
  }

  render(){

    let NavBar =
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div>
          <a class="navbar-brand" style={{ color: 'white'}}>Plagiarism Detection System</a>
        </div>
        <div>
          <a class="navbar-brand" style={{ color: 'white'}}>Admin Page</a>
        </div>
        <div>
          <Button onClick={this.handleClick}> Users List </Button>
        </div>
        <div>
          <Button className={'bsStyle="success"'} onClick={this.registerUser}> New User </Button>
        </div>
        <div>
          <Button onClick={this.updateUser}> Update User </Button>
        </div>
        <div>
          <Button onClick={this.deleteUser}> Delete User </Button>
        </div>
        <div>
          <Button onClick={this.grantProfRole}> Grant Professor Role </Button>
        </div>
        <div>
          <Button className={'bsStyle="danger"'} onClick={this.handlelogout} href="/"> Logout </Button>
        </div>
      </div>
    </nav>

    if(this.state.usersList){
      return (
        <div>
          {NavBar}
          <br />
          <UserList />
        </div>
      )
    }
    else if(this.state.newUser){
      return (
        <div>
          {NavBar}
          <br />
          <NewUser />
        </div>
      )
    }
    else if(this.state.deleteUser){
      return (
        <div>
          {NavBar}
          <br />
          <DeleteUser />
        </div>
      )
    }
    else if(this.state.updateUser){
      return (
        <div>
          {NavBar}
          <br />
          <UpdateUser />
        </div>
      )
    }
    else if(this.state.grantProfRole){
      return (
        <div>
          {NavBar}
          <br />
          <GrantProfRole />
        </div>
      )
    }
    else{
      return(
        <div>
          {NavBar}
          <br />
          <UserList />
        </div>
      )
    }
  }
}

export default AdminPage
