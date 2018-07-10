package com.team208.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * domain class for assignment entity
 * @author rachanatondare
 *
 */
@Entity
@Table(name = "assignment", uniqueConstraints=
@UniqueConstraint(columnNames={"course_id", "assignmentNo"}))
public class AssignmentEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int assignmentId;

	private int assignmentNo;

	private String assignmentName;

	private CourseEntity assignmentCourse;

	private Date submissionDate;

	private  Set<AssignmentSubmissionEntity> submissions = new HashSet<>();

	@JsonIgnore
	@OneToMany( mappedBy = "assignmentId",  cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval=true)
	@JsonManagedReference
	public Set<AssignmentSubmissionEntity> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(Set<AssignmentSubmissionEntity> submissions) {
		this.submissions = submissions;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="assignmentId")
	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	@Column(name = "assignmentNo", nullable = false)
	public int getAssignmentNo() {
		return assignmentNo;
	}

	public void setAssignmentNo(int assignmentNo) {
		this.assignmentNo = assignmentNo;
	}

	@Column(name = "assignmentName", nullable = false)
	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	@ManyToOne
	@JoinColumn(name = "course_id")
	@JsonBackReference
	public CourseEntity getAssignmentCourse() {
		return assignmentCourse;
	}

	public void setAssignmentCourse(CourseEntity assignmentCourse) {
		this.assignmentCourse = assignmentCourse;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "submissionDate", nullable = false)
	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

}
