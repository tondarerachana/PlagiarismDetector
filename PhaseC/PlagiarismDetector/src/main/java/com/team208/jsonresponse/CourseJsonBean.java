package com.team208.jsonresponse;

import java.io.Serializable;
import java.util.List;


/**
 * response class to return all courses in json response
 *
 * @author rachanatondare
 *
 */
public class CourseJsonBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String courseName;

	private List<Integer> sections;

	private String courseAbbr;

	private String courseTerm;

	private String courseLoc;

	private Long createdCourseBy;

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

	public List<Integer> getSections() {
		return sections;
	}

	public void setSections(List<Integer> sections) {
		this.sections = sections;
	}





}
