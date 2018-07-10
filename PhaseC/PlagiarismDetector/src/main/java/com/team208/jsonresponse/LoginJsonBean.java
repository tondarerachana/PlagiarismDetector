package com.team208.jsonresponse;

import java.io.Serializable;

/**
 * response class to get login details in json response
 * @author rachanatondare
 *
 */
public class LoginJsonBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String password;

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



}
