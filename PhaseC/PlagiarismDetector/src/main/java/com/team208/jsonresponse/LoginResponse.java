package com.team208.jsonresponse;

import java.io.Serializable;

import com.team208.domain.UserEntity;

/**
 * response class to return login response in json 
 * @author rachanatondare
 *
 */
public class LoginResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private StatusBean status;
	private UserEntity user;
	
	public StatusBean getStatus() {
		return status;
	}
	public void setStatus(StatusBean status) {
		this.status = status;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity n) {
		this.user = n;
	}
	
	

}
