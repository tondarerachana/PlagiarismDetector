package com.team208.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * domain class for  user entity
 * @author rachanatondare
 *
 */
@Entity
@Table(name = "user" , uniqueConstraints=
@UniqueConstraint(columnNames={"userneu_id", "email"}))

public class UserEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static UserEntity instance = null;

	public static UserEntity getInstance() {
		if (instance == null)
			return new UserEntity();
		else
			return instance;
	}


	public static void setInstance(UserEntity user) {
		instance = user;
	}


	private int userDBid; 

	private Long userId;


	private String name;


	private String userRole;


	private String password;


	private String email;


	private Set<UserCourseEntity> usercourse = new HashSet<>();


	private  Set<AssignmentSubmissionEntity> submissions = new HashSet<>();

	private Set<CourseEntity> createdCourse = new HashSet<>();

	public UserEntity(Long userId, String name, String userRole, String password, String email
			) {
		super();
		this.userId = userId;
		this.name = name;
		this.userRole = userRole;
		this.password = password;
		this.email = email;

	}


	public UserEntity(Set<UserCourseEntity> usercourse) {
		super();
		this.usercourse = usercourse;
	}

	public UserEntity() {
		super();
	}



	@OneToMany( mappedBy = "createdCourseBy",  cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval=true)
	@JsonManagedReference
	public Set<CourseEntity> getCreatedCourse() {
		return createdCourse;
	}


	public void setCreatedCourse(Set<CourseEntity> createdCourse) {
		this.createdCourse = createdCourse;
	}

	@OneToMany( mappedBy = "user",  fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE  }, orphanRemoval = true)
	@JsonManagedReference
	public Set<UserCourseEntity> getUsercourse() {
		return usercourse;
	}

	public void setUsercourse(Set<UserCourseEntity> usercourse) {
		this.usercourse = usercourse;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userDBid", nullable = false)  
	public int getUserDBid() {
		return userDBid;
	}


	public void setUserDBid(int userDBid) {
		this.userDBid = userDBid;
	}

	@Column(name = "userneu_id", nullable = false, unique=true)
	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "userRole", nullable = false)
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@OneToMany( mappedBy = "student",  cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval=true)
	@JsonManagedReference
	public Set<AssignmentSubmissionEntity> getSubmissions() {
		return submissions;
	}


	public void setSubmissions(Set<AssignmentSubmissionEntity> submissions) {
		this.submissions = submissions;
	}


	/**
	 * This function is used to update the user whenever they are changed 
	 * Only the value that is changed will be updated 
	 * @author amanrayat
	 * @param newUser
	 */
	public void set(UserEntity newUser) {
		this.email=newUser.email!=null? newUser.email:this.email;
		this.name=newUser.name!=null? newUser.name:this.name;
		this.password=newUser.password!=null? newUser.password:this.password;
		this.userId=newUser.userId!=null? newUser.userId:this.userId;
		this.userRole=newUser.userRole!=null? newUser.userRole:this.userRole;

	}




}
