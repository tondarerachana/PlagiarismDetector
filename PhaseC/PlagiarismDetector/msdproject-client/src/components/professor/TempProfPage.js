import React from 'react';
import { Button, Pager} from 'react-bootstrap';

export default class TempProfPage extends React.Component{

  handlelogout(){
    localStorage.clear();
    window.location.reload();
  }

  render(){
    var userID = this.props.userID;
    return(
    <div class={'container text-center'}>
      <Button class={'btn'} onClick={this.handlelogout.bind(this)}> Logout </Button>
      <h1> Waiting for Admin Confirmation for User ID {userID}</h1>
    </div>);
  }
}
