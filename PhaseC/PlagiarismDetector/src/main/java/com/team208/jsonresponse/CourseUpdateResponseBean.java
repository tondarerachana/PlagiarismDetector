package com.team208.jsonresponse;

import java.io.Serializable;

/**
 * response class to get updated course  details in json response
 * @author rachanatondare
 *
 */
public class CourseUpdateResponseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int courseId;

	private String courseName;

	private String courseAbbr;
	
	private String courseTerm;

	private String courseLoc;
	
	private int sections;
	
	public int getSections() {
		return sections;
	}

	public void setSections(int sections) {
		this.sections = sections;
	}

	private Long createdCourseBy;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseAbbr() {
		return courseAbbr;
	}

	public void setCourseAbbr(String courseAbbr) {
		this.courseAbbr = courseAbbr;
	}

	public String getCourseTerm() {
		return courseTerm;
	}

	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}

	public String getCourseLoc() {
		return courseLoc;
	}

	public void setCourseLoc(String courseLoc) {
		this.courseLoc = courseLoc;
	}

	public Long getCreatedCourseBy() {
		return createdCourseBy;
	}

	public void setCreatedCourseBy(Long createdCourseBy) {
		this.createdCourseBy = createdCourseBy;
	}
	
	
}

