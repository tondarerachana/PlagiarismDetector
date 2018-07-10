import React from 'react';
import { Button } from 'react-bootstrap';
import * as data from '../constants';

const url = data.URL;


class NewUser extends React.Component{

  constructor(){
    super();

    this.state = {userID:'', univID:'', name:'', userRole:'', password:'', email:'', successMessage: false};

    this.handleClick = this.handleClick.bind(this)
  }

  handleClick() {
    console.log("Success from RegisterPage!")

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
	        // console.log(Object.values(j)[1].name);
          this.setState({
            userID:'',
            univID:'',
            name:'',
            userRole:'',
            password:'',
            email:'',
            successMessage: "New User Created!"
          })
        );
  }

  update(){
    this.setState({
      userID: 10,
      univID: this.refs.id.value,
      name: this.refs.name.value,
      password: this.refs.password.value,
      email: this.refs.email.value
    })
  }

  handleUserRole(event) {
    this.setState({
      userRole: event.target.value
    })
  }

  render(){
    return (
      <div style={{ padding: '25px'}} className={'container col-md-6 col-md-offset-3'}>
        <h1> User Registration </h1>
        <form>
          <div class="form-group">
            <label for="id">University ID:</label>
            <input class="form-control" type="text" ref="id" placeholder="University ID"
                    onChange={this.update.bind(this)}/>
          </div>
          <div class="form-group">
            <label for="name">Name:</label>
            <input class="form-control" type="text" name="name" ref="name" placeholder="Name"
                  onChange={this.update.bind(this)}/>
          </div>
          <div class="form-group">
            <label for="id">Email ID:</label>
            <input class="form-control" type="email" name="email" ref="email" placeholder="Email"
                  onChange={this.update.bind(this)}/>
          </div>
          <div class="form-group">
            <label for="id">Password:</label>
            <input class="form-control" type="password" name="password" ref="password" placeholder="Password"
                  onChange={this.update.bind(this)}/>
          </div>
          <div class="radio">
            <label>
            <input type="radio" name="role" ref="studentRole" value="student"
                    checked={this.state.userRole === 'student'}
                    onChange={this.handleUserRole.bind(this)} /> Student</label>
          </div>
          <div class="radio">
            <label>
            <input type="radio" name="role" ref="profRole" value="professor-temp"
                  checked={this.state.userRole === 'professor-temp'}
                  onChange={this.handleUserRole.bind(this)} /> Professor</label>
          </div>
          <div class={'container text-center'}>
            <Button onClick={this.handleClick}>Submit</Button>
          </div>
        </form>
      </div>
    );
  }
}

export default NewUser
