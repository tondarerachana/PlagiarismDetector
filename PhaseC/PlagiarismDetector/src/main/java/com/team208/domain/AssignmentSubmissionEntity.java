package com.team208.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * domain class for  submission entity
 * @author rachanatondare
 *
 */
@Entity
@Table(name="submission")
public class AssignmentSubmissionEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int submissionId;

	private String gitLink;

	private AssignmentEntity assignmentId;

	private UserEntity student;

	private Timestamp timestamp;



	@Column(name="timestamp")
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Id
	@GeneratedValue
	@Column(name = "submissionId")
	public int getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}

	@Column(name="gitLink")
	public String getGitLink() {
		return gitLink;
	}

	public void setGitLink(String gitLink) {
		this.gitLink = gitLink;
	}


	@ManyToOne
	@JoinColumn(name = "assignmentId")
	@JsonBackReference
	public AssignmentEntity getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(AssignmentEntity assignmentId) {
		this.assignmentId = assignmentId;
	}

	@ManyToOne
	@JoinColumn(name = "userDBid")
	@JsonBackReference
	public UserEntity getStudent() {
		return student;
	}


	public void setStudent(UserEntity student) {
		this.student = student;
	} 



}
