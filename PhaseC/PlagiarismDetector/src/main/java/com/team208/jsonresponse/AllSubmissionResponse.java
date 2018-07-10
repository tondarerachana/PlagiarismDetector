package com.team208.jsonresponse;

import java.io.Serializable;
import java.util.Set;

import com.team208.domain.AssignmentSubmissionEntity;

/**
 * response class to return all submissions in json response
 * @author rachanatondare
 *
 */
public class AllSubmissionResponse implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private Set<AssignmentSubmissionEntity> submissions ; 
	public Set<AssignmentSubmissionEntity> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(Set<AssignmentSubmissionEntity> submissions) {
		this.submissions = submissions;
	}

	private StatusBean status;



	public StatusBean getStatus() {
		return status;
	}

	public void setStatus(StatusBean status) {
		this.status = status;
	}





}
