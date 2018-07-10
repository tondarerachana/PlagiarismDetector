package com.team208.domain;


import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * repository class for assignment entity
 * @author rachanatondare
 *
 */
@Transactional
public interface AssignmentRepository extends CrudRepository<AssignmentEntity, Integer> {

	@Query("SELECT s FROM AssignmentEntity s WHERE s.assignmentId=:assignmentId ")
	AssignmentEntity findById(@Param("assignmentId") int assignmentId);


	@Query("SELECT a FROM AssignmentEntity a inner join a.assignmentCourse c WHERE a.assignmentNo=:assignment_no and c.courseId =:course_id ")
	AssignmentEntity findByNoAndCourse(@Param("assignment_no") int assignmentNo, @Param("course_id") int courseId) ;

	@Query("SELECT s FROM AssignmentEntity s inner join s.assignmentCourse c WHERE    c.courseId=:course_id ")
	Set<AssignmentEntity> findByCourse( @Param("course_id")int courseId);

	@Query("SELECT s.assignmentId FROM AssignmentEntity s inner join s.assignmentCourse c WHERE    c.courseId IN ?1 ")
	List<Integer> findByCourses( List<Integer> courseIds);

}
