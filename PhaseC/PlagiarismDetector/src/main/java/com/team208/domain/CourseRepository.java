package com.team208.domain;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * repository class for course entity
 * @author rachanatondare
 *
 */
@Transactional
public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {

	/**
	 * method to find course based on course id
	 * @param courseId
	 * @return course
	 */
	@Query("SELECT s FROM CourseEntity s WHERE s.courseId=:course_id ")
	CourseEntity findByCourseId(@Param("course_id") int courseId);

	/**
	 * method to find course based on course abbreviation
	 * @param courseAbbr
	 * @return course
	 */
	@Query("SELECT  max(s.courseId) FROM CourseEntity s WHERE s.courseAbbr=:courseAbbr  ")
	Integer findByAbbr(@Param("courseAbbr") String courseAbbr);

	/**
	 *  method to find list of course based on course term
	 * @param courseTerm
	 * @return list of courses
	 */
	@Query("SELECT s FROM CourseEntity s WHERE s.courseTerm=:course_term")
	Set<CourseEntity> findByTerm(@Param("course_term") String courseTerm);

	/**
	 *  method to find course based on course abbreviation and in list of course terms
	 * @param courseTerm
	 * @param courseAbbr
	 * @return list of course ids
	 */
	@Query("SELECT s.courseId FROM CourseEntity s WHERE   s.courseAbbr=:courseAbbr and s.courseTerm IN (:course_terms)")
	List<Integer> findByTermAndAbbr(@Param("course_terms") List<String> courseTerm, @Param("courseAbbr") String courseAbbr);

	/**
	 *  method to find list of course based on same course abbreviation
	 * @param courseAbbr
	 * @return list of courses
	 */
	@Query("SELECT s FROM CourseEntity s WHERE s.courseAbbr=:courseAbbr")
	Set<CourseEntity> findByMultipleCourseAbbr(@Param("courseAbbr") String courseAbbr);

	/**
	 *  method to find course based on same course abbreviation and term and in sections list
	 * @param courseAbbr
	 * @param currentTerm
	 * @param sections
	 * @return list of course ids
	 */
	@Query("SELECT s.courseId FROM CourseEntity s WHERE   s.courseAbbr=:courseAbbr and s.courseTerm=:course_term and s.section IN (:course_sections)") 
	List<Integer> findByAbbrTermSections(@Param("courseAbbr")String courseAbbr, @Param("course_term")String currentTerm, @Param("course_sections")List<Integer> sections);

}
