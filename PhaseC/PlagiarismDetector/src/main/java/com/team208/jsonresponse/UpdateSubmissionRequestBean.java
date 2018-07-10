package com.team208.jsonresponse;

import java.io.Serializable;

/**
 * response class to return updated submission request in json response
 * @author rachanatondare
 *
 */
public class UpdateSubmissionRequestBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int submissionId;
	private String gitLink;

	public String getGitLink() {
		return gitLink;
	}

	public void setGitLink(String gitLink) {
		this.gitLink = gitLink;
	}

	public int getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}


}
