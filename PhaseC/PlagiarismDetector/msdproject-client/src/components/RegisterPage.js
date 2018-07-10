import React from 'react';
import ReactDOM from 'react-dom';
import { Button } from 'react-bootstrap';
import * as data from './constants';

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


const url = data.URL;

class RegisterPage extends React.Component{

  constructor(){
    super();

    this.state = {userID:'',
        univID:'',
        name:'',
        userRole:'',
        password:'',
        email:'',
        successMessage: '',
        formErrors: {email: '', password: '', name: '', univID: ''},
        emailValid: false,
        passwordValid: false,
        formValid: false,
        nameValid: false,
        univIDValid: false,
        };

    this.handleClick = this.handleClick.bind(this)
  }

  handleClick() {
    console.log("Success from RegisterPage!")

    fetch(url+'team208/registerUser', {
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
       }).then(
         this.setState({
           successMessage: "Created New User!"
         }),
           window.location.reload()
        );
  }

  update(e){
    const name = e.target.name;
    const value = e.target.value;
    this.setState({
      userID: 10,
      univID: this.refs.id.value,
      name: this.refs.name.value,
      password: this.refs.password.value,
      email: this.refs.email.value
    },
    () => { this.validateField(name, value) })
  }

  validateField(fieldName, value) {
    let fieldValidationErrors = this.state.formErrors;
    let emailValid = this.state.emailValid;
    let passwordValid = this.state.passwordValid;
    let univIDValid = this.state.univIDValid;
    let nameValid = this.state.nameValid;

    switch(fieldName) {
      case 'email':
        emailValid = value.match(/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/i);
        fieldValidationErrors.email = emailValid ? '' : ' is invalid.';
        break;
      case 'password':
        passwordValid = value.length >= 6;
        fieldValidationErrors.password = passwordValid ? '': ' is too short.';
        break;
      case 'univID':
        univIDValid = value.length >= 4;
        fieldValidationErrors.univID = univIDValid ? '': ' is too short.';
      case 'name':
        nameValid = value.length >= 4;
        fieldValidationErrors.name = nameValid ? '': ' is too short.';
      default:
        break;
  }
  this.setState({formErrors: fieldValidationErrors,
                  emailValid: emailValid,
                  passwordValid: passwordValid,
                  univIDValid: univIDValid,
                  nameValid: nameValid,
                }, this.validateForm);
}

validateForm() {
  this.setState({formValid: this.state.emailValid && this.state.passwordValid && this.state.nameValid && this.state.univIDValid});
}


  handleUserRole(event) {
    this.setState({
      userRole: event.target.value
    })
  }

  render(){
    return (
      <div className={'container col-md-6 col-md-offset-3'}>
        <h1 className={'text-center'}> User Registration </h1>
        <form>
          <div class="form-group">
            <label for="id">University ID:</label>
            <input class="form-control" type="text" name="univID" ref="id" placeholder="University ID"
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
            <Button disabled={!this.state.formValid} onClick={this.handleClick}>Submit</Button>
            <Button> <a href="/"> HOME </a> </Button>
          </div>
        </form>
        <div style={{color: 'red'}}>
          <FormErrors formErrors={this.state.formErrors} />
        </div>
        <h2 className={'text-center'}> {this.state.successMessage} </h2>
      </div>
    );
  }
}

export default RegisterPage
