package com.team208.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * domain class for  course registration of each user  entity
 * @author rachanatondare
 *
 */
@Entity
@Table(name = "user_course", uniqueConstraints=
@UniqueConstraint(columnNames={"course_id", "userDBid"}))
public class UserCourseEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private int ucid;

	private CourseEntity course;

	private UserEntity user;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "course_id") 
	@JsonBackReference
	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	@ManyToOne
	@JoinColumn(name = "userDBid")  
	@JsonBackReference
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Id
	@GeneratedValue
	@Column(name = "user_course_id")
	public int getUcid() {
		return ucid;
	}

	public void setUcid(int ucid) {
		this.ucid = ucid;
	}





}
