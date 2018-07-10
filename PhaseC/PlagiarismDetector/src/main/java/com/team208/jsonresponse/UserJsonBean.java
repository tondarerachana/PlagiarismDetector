package com.team208.jsonresponse;

import java.io.Serializable;

/**
 * response class to accept user details in json response
 * @author rachanatondare
 *
 */
public class UserJsonBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String name;
	private String userRole;

	private String password;
	private String email;


	public UserJsonBean(Long userId, String name, String userRole, String password, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.userRole = userRole;
		this.password = password;
		this.email = email;
	}
	public UserJsonBean() {
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}




}
