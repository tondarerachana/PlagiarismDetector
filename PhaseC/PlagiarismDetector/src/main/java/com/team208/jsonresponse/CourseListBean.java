package com.team208.jsonresponse;

import java.io.Serializable;

import java.util.Set;

import com.team208.domain.CourseEntity;

/**
 * class defined to return list of courses along with status
 * @author rachanatondare
 *
 */
public class CourseListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<CourseEntity> courses;
	
	private StatusBean status;

	public Set<CourseEntity> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseEntity> courses) {
		this.courses = courses;
	}

	public StatusBean getStatus() {
		return status;
	}

	public void setStatus(StatusBean status) {
		this.status = status;
	}
	
	
}
