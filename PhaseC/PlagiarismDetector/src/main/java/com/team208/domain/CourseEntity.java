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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * domain class for  course entity
 * @author rachanatondare
 *
 */
@Entity
@Table(name = "course", uniqueConstraints=
@UniqueConstraint(columnNames={"courseName", "courseTerm", "courseLoc", "courseSection"  }))
public class CourseEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static CourseEntity instance = null  ;
	private int courseId;

	private String courseName;

	private int section; 

	private String courseAbbr;

	private String courseTerm;

	private String courseLoc;

	private UserEntity createdCourseBy;

	public static void setInstance(CourseEntity dao) {
		instance = dao;
	}
	
	public static CourseEntity getInstance() {
		if (instance == null)
			return new CourseEntity();
		else
			return instance;
	}
	
	@ManyToOne
	@JoinColumn(name = "userDBid")
	@JsonBackReference
	public UserEntity getCreatedCourseBy() {
		return createdCourseBy;
	}


	public void setCreatedCourseBy(UserEntity createdCourseBy) {
		this.createdCourseBy = createdCourseBy;
	}


	private Set<UserCourseEntity> usercourse = new HashSet<>();

	private Set<AssignmentEntity> assignment = new HashSet<>();



	@OneToMany( mappedBy = "assignmentCourse",  cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval=true)
	@JsonManagedReference
	public Set<AssignmentEntity> getAssignment() {
		return assignment;
	}


	public void setAssignment(Set<AssignmentEntity> assignment) {
		this.assignment = assignment;
	}


	@OneToMany( mappedBy = "course",  cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval=true)
	@JsonManagedReference
	public Set<UserCourseEntity> getUsercourse() {
		return usercourse;
	}

	public void setUsercourse(Set<UserCourseEntity> usercourse) {
		this.usercourse = usercourse;
	}



	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCourseId() {
		return courseId;
	}

	@Column(name = "courseName", nullable = false)
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "courseAbbr", nullable = false)
	public String getCourseAbbr() {
		return courseAbbr;
	}

	public void setCourseAbbr(String courseAbbr) {
		this.courseAbbr = courseAbbr;
	}

	@Column(name = "courseSection", nullable = false)
	public int getSection() {
		return section;
	}


	public void setSection(int section) {
		this.section = section;
	}


	@Column(name = "courseTerm", nullable = false)
	public String getCourseTerm() {
		return courseTerm;
	}

	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}

	@Column(name = "courseLoc", nullable = false)
	public String getCourseLoc() {
		return courseLoc;
	}


	public void setCourseLoc(String courseLoc) {
		this.courseLoc = courseLoc;
	}



}
