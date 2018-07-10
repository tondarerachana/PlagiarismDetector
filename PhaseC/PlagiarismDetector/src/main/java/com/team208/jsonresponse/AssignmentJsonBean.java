package com.team208.jsonresponse;

import java.io.Serializable;



/**
 * class defined to create an assignment
 * @author rachanatondare
 *
 */
public class AssignmentJsonBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int assignmentId;

	private int courseId;

	private  int assignmentNo;

	private String assignmentName;

	private String submissionDate;

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}


	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public int getAssignmentNo() {
		return assignmentNo;
	}

	public void setAssignmentNo(int assignmentNo) {
		this.assignmentNo = assignmentNo;
	}


}
