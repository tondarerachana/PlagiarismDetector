package com.team208.domain;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 *  repository class for user course entity
 * @author rachanatondare
 *
 */
@Transactional
public interface UserCourseRepository extends CrudRepository<UserCourseEntity, Integer> {
	
	/**
	 * method to get the list of courses based on id.
	 * @param userId
	 * @return list course
	 */
	@Query("SELECT uc FROM UserCourseEntity uc where userdbid = ?1 ")
	Iterable<UserCourseEntity> findCourseById( int userId);
	
	/**
	 * method to return the courses based on user db id and course id
	 * @param userDBid
	 * @param courseId
	 * @return course
	 */
	@Query("SELECT uc FROM UserCourseEntity uc  inner join uc.user u  inner join uc.course c where u.userDBid = ?1  and c.courseId = ?2 ")
	UserCourseEntity findCourseTodrop( int userDBid,  int courseId);
}

