package com.team208.jsonresponse;

import java.io.Serializable;


/**
 * response class to get student assignment submission details in json 
 * @author rachanatondare
 *
 */
public class StudentSubmissionJsonBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String gitLink;

	private int assignmentId;

	private Long studentId;


	public String getGitLink() {
		return gitLink;
	}

	public void setGitLink(String gitLink) {
		this.gitLink = gitLink;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}




}
