package com.team208.jsonresponse;

import java.io.Serializable;
import java.util.List;

/**
 * response class to get details required to fetch all submissions details in json response
 * @author rachanatondare
 *
 */
public class MultipleTermSubmissionJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private String courseAbbr;

	private  int assignmentNo ;

	private List<String> term;

	public String getCourseAbbr() {
		return courseAbbr;
	}

	public void setCourseAbbr(String courseAbbr) {
		this.courseAbbr = courseAbbr;
	}

	public int getAssignmentNo() {
		return assignmentNo;
	}

	public void setAssignmentNo(int assignmentNo) {
		this.assignmentNo = assignmentNo;
	}

	public List<String> getTerm() {
		return term;
	}

	public void setTerm(List<String> term) {
		this.term = term;
	}




}
