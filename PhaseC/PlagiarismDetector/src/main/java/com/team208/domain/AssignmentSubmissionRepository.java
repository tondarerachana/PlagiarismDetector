package com.team208.domain;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * repository class for submission entity
 * @author rachanatondare
 *
 */
@Transactional
public interface AssignmentSubmissionRepository extends CrudRepository<AssignmentSubmissionEntity, Integer> {

	/**
	 * method to get submission on submission id
	 * @param submissionId
	 * @return
	 */
	@Query("SELECT s FROM AssignmentSubmissionEntity s WHERE s.submissionId=:submission_id ")
	AssignmentSubmissionEntity findById(@Param("submission_id") int submissionId);

	/**
	 * method to get list of submissions for each assignment id
	 * @param assignmentId
	 * @return
	 */
	@Query("SELECT sub, s  FROM AssignmentSubmissionEntity sub  inner join sub.student s "
			+ "WHERE (s.userDBid,sub.timestamp) IN "
			+ " (select s1.userDBid as uid, MAX(timestamp) as mts  FROM AssignmentSubmissionEntity sub1 "
			+ "inner join sub1.assignmentId a inner join sub1.student s1 WHERE a.assignmentId=:assignment_id "
			+ "GROUP BY s1.userDBid) ")
	Set<AssignmentSubmissionEntity> findSubmissionByAssignmentId(@Param("assignment_id") int assignmentId);

	/**
	 * method to get list of submissions for each course
	 * @param assignmentIds
	 * @return
	 */
	@Query("SELECT sub, s  FROM AssignmentSubmissionEntity sub  inner join sub.student s "
			+ "WHERE (s.userDBid,sub.timestamp) IN "
			+ " (select s1.userDBid as uid, MAX(timestamp) as mts  FROM AssignmentSubmissionEntity sub1 "
			+ "inner join sub1.assignmentId a inner join sub1.student s1 WHERE a.assignmentId IN (:assignment_ids) "
			+ "GROUP BY s1.userDBid) ")
	Set<AssignmentSubmissionEntity> findSubmissionByMultpileAssignmentIds(@Param("assignment_ids") List<Integer> assignmentIds);

}
