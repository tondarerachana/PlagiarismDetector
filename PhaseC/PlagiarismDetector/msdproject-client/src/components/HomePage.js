import React from 'react';
import {BrowserRouter as Router, Link, Route} from 'react-router-dom'
import AdminPage from './admin/AdminPage.js'
import RegisterPage from './RegisterPage.js'
import axios from 'axios'
import TempProfPage from './professor/TempProfPage.js'
import StudentSubmissionPage from './student/StudentSubmissionPage.js'
import ProfessorCoursePage from './professor/ProfessorCoursePage.js'
import ProfessorMainPage from './professor/ProfessorMainPage.js'
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import { GoogleLogin, GoogleLogout } from 'react-google-login';
import * as data from './constants';

const url = data.URL;

const FormErrors = ({formErrors}) =>
  <div className='formErrors'>
    {Object.keys(formErrors).map((fieldName, i) => {
      if(formErrors[fieldName].length > 0){
        return (
          <h3 key={i}>{fieldName} {formErrors[fieldName]}</h3>
        )
      } else {
        return '';
      }
    })}
  </div>


const responseGoogle = (response) => {
  window.localStorage.setItem("googletoken", response.tokenId);
  window.localStorage.setItem("googleuser_id", response.profileObj.givenName);
  window.localStorage.setItem("google_email",response.profileObj.email)
  window.location.reload();
}

const logout = (response) => {
 localStorage.clear();
 window.location.reload();
}

export default class HomePage extends React.Component{

  constructor(){
    super();
    this.state = {
                  username:'' ,
                  userID: '',
                  password: '',
                  loggedIn:false,
                  adminlogin:false,
                  role: '',
                  status: '',
                  register: false,
                  loginclick: false,
                  gmail: "",
                  formErrors: {userID: '', password: ''},
                  userIDValid: false,
                  passwordValid: false
                };
    this.handleClick = this.handleClick.bind(this);
    this.register = this.register.bind(this);
    this.loginhandle = this.loginhandle.bind(this);
    this.handlelogout = this.handlelogout.bind(this);
  }

  handlelogout(){
    localStorage.clear();
    window.location.reload();
  }

  update(e){
    const name = e.target.name;
    const value = e.target.value;
    this.setState({
      userID: this.refs.userID.value,
      password: this.refs.password.value,
      adminlogin: this.state.role === 'admin' ? true : false
    },
    () => { this.validateField(name, value) })

  }

  validateField(fieldName, value) {
    let fieldValidationErrors = this.state.formErrors;
    let passwordValid = this.state.passwordValid;
    let univIDValid = this.state.univIDValid;

    switch(fieldName) {
      case 'password':
        passwordValid = value.length >= 6;
        fieldValidationErrors.password = passwordValid ? '': ' is Invalid.';
        break;
      case 'univID':
        univIDValid = value.length >= 4;
        fieldValidationErrors.univID = univIDValid ? '': ' is Invalid.';
      default:
        break;
  }
  this.setState({ formErrors: fieldValidationErrors,
                  passwordValid: passwordValid,
                  univIDValid: univIDValid,
                }, this.validateForm);
  }

  validateForm() {
    this.setState({formValid: this.state.passwordValid  && this.state.univIDValid});
  }



  register(){
    this.setState({
      register: true
    })
  }

  loginhandle(){
    window.localStorage.setItem("token", "xxxxx");
    window.localStorage.setItem("user_id", "xxxx");
    this.setState({
      loginclick: true
    })
  }

  handleClick() {

    window.localStorage.setItem("token", "xxxxx");
    window.localStorage.setItem("user_id", "xxxx");
    console.log("Local storage",window.localStorage.getItem("token"));
    console.log("URL:",url)
    fetch(url+'team208/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          userId: this.state.userID,
          password: this.state.password,
        })
      }).then(function(response) {
	       return response.json();
       }).then(j =>
          this.setState({
            username: Object.values(j)[1].name,
            adminlogin: Object.values(j)[1].userRole === 'admin' ? true : false,
            role: Object.values(j)[1].userRole,
            isLoggedIn: true,
            loginclick: false})
          ).catch(function() {
            alert("Login Error! Wrong User ID and password entered. Please try again.")
          });
  }

  getUserByEmail(gmail){
    fetch(url+'team208/studentByEmail?email='+gmail)
      .then(response => response.json())
      .then(data =>
        this.setState({
          userID: data.user.userId,
          username: data.user.name,
          adminlogin: data.user.userRole === 'admin' ? true : false,
          role: data.user.userRole,
          isLoggedIn: true,
          loginclick: false}))
          .catch(function() {
            localStorage.clear();
            window.location.reload();
          });
  }

  render(){

    let LoginForm
    let welcomeInfo
    var tok1 = window.localStorage.getItem("googletoken");
    var tok = window.localStorage.getItem("token");
    var gmail = window.localStorage.getItem("google_email");
    var gusername
    if(gmail){
      gusername = this.getUserByEmail(gmail)
    }

    if(this.state.loginclick){
      LoginForm =
      <div class={'container col-md-6 col-md-offset-3'}>
        <h1 class={'text-center'}> User Login </h1>
        <form>
          <div class="form-group">
            <label>User ID: </label>
            <input
                class="form-control"
                type="number"
                ref="userID"
                name="univID"
                placeholder="User ID"
                onChange={this.update.bind(this)} />
          </div>
          <div class="form-group">
            <label>Password: </label>
            <input
                class="form-control"
                type="password"
                ref="password"
                name="password"
               placeholder="Password"
               onChange={this.update.bind(this)} />
          </div>
          <div class={'text-center'} >
            <Button class={'btn text-center'} disabled={!this.state.formValid} onClick={this.handleClick}> Login </Button>
          </div>
          <div style={{color: 'red'}}>
            <FormErrors formErrors={this.state.formErrors} />
          </div>
          <div class={'text-center'} >
          <br />
          <div class='google-signin'>
            <GoogleLogin
            clientId="750553707251-d298k3pijiijj4gok3efuuq27049unmp.apps.googleusercontent.com"
            buttonText=""
            onSuccess={responseGoogle}
            onFailure={responseGoogle}/>
            </div>
          </div>
      </form>
      </div>
    }
    else{
      welcomeInfo = <div>
      <br />
      <br />
      <h1 class={'text-center'}> Welcome to Plagiarism Detection System!</h1>
      <h2 class={'text-center'}> Click on Register if using for the first time or Login.</h2>
        </div>
    }

    let NavBar =
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                  <div class="navbar-header">
                    <a class="navbar-brand" style={{ color: 'white'}} href="/">Plagiarism Detection System</a>
                  </div>
                <div>
                <button class="btn btn-default navbar-btn" onClick={this.register}> Register </button>
                <button class="btn btn-danger navbar-btn" onClick={this.loginhandle}> Login </button>
                </div>
              </div>
            </nav>

    let isLoggedIn = this.state.isLoggedIn;
    let username = this.state.username;
    let isAdmin = this.state.adminlogin;
    let role = this.state.role;
    let register = this.state.register;

    let contactAdmin =
    <div class={'text-center'}>
      <p> Mail the admin for any issues: team208@gmail.com </p>
    </div>

    if(register){
      return (
        <div>
          {NavBar}
          <RegisterPage />
        </div>
      );
    }
    else if (isLoggedIn && !isAdmin && role === 'professor-temp' && tok) {
      return <TempProfPage userID={this.state.userID}/>
    }
    else if (isLoggedIn && !isAdmin && role === 'student' && tok) {
      return <StudentSubmissionPage userID={this.state.userID}/>
    }
    else if (isLoggedIn && !isAdmin && role === 'professor' && tok) {
      return <ProfessorMainPage userID={this.state.userID}/>
    }
    else if (isLoggedIn && isAdmin && tok){
      return <AdminPage />
    }
    else {
      return (
        <div>
          {NavBar}
          {LoginForm}
          {welcomeInfo}
          {contactAdmin}
        </div>
      );
    }
  }
}
