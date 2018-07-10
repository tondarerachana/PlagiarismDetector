package com.team208.jsonresponse;

import java.io.Serializable;

import java.util.Set;

import com.team208.domain.AssignmentEntity;

/**
 * class defined to return status and list of assignments
 * @author rachanatondare
 *
 */
public class AssignmentListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<AssignmentEntity> assignments;

	private StatusBean status;


	public Set<AssignmentEntity> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<AssignmentEntity> assignments) {
		this.assignments = assignments;
	}

	public StatusBean getStatus() {
		return status;
	}

	public void setStatus(StatusBean status) {
		this.status = status;
	}




}
