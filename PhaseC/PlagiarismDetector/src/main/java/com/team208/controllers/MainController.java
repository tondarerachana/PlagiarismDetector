package com.team208.controllers;



import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Main controller with generic functionalities accross the application
 * @author rachanatondare
 *
 */


import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team208.domain.AssignmentEntity;
import com.team208.domain.AssignmentRepository;
import com.team208.domain.AssignmentSubmissionEntity;
import com.team208.domain.AssignmentSubmissionRepository;
import com.team208.domain.CourseEntity;
import com.team208.domain.CourseRepository;
import com.team208.jsonresponse.AllSubmissionResponse;
import com.team208.jsonresponse.AssignmentListBean;
import com.team208.jsonresponse.CourseJsonBean;
import com.team208.jsonresponse.LoginJsonBean;
import com.team208.jsonresponse.LoginResponse;
import com.team208.jsonresponse.MultipleTermSubmissionJson;
import com.team208.jsonresponse.StatusBean;
import com.team208.jsonresponse.UserJsonBean;
import com.team208.userservice.UserService;
import com.team208.utilities.Constants;
import com.team208.jsonresponse.CourseListBean;

/**
 * this class defines the rest end point common user functionality
 * @author rachanatondare
 *
 */
@CrossOrigin
@Controller
@RequestMapping(path="/team208") 
public class MainController {

	/**
	 * Logger
	 */
	private static final Logger logger = 
			Logger.getLogger(MainController.class.getName());

	@Autowired
	private UserService studService;

	@Autowired 
	private CourseRepository courseRepository;

	@Autowired 
	private AssignmentSubmissionRepository submissionRepository;

	@Autowired
	private AssignmentRepository assignmentRepository;



	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "Hello World!!!  Team208 Homepage";
	}


	/**
	 * Login controller 
	 * @param userDetails
	 * @return user object with status on successful  credentials
	 */
	@RequestMapping(path="/login")
	public @ResponseBody LoginResponse login(@RequestBody LoginJsonBean userDetails)  {


		return  studService.login(userDetails);  

	}

	/**
	 * Register new user functionality for registering user details
	 * @param user
	 * @return status bean with creation status
	 */
	@RequestMapping(path="/registerUser", method = RequestMethod.POST  ) // Map ONLY GET Requests
	public @ResponseBody StatusBean addNewUser (@RequestBody UserJsonBean user) {
		return studService.register(user);

	}


	/**
	 * find the student based on northeastern id
	 * @param userId
	 * @return student details on the id
	 */
	@GetMapping(path="/findStudent")
	public @ResponseBody LoginResponse findStudent(@RequestParam Long userId ) {
		return studService.findStudent(userId)  ;

	}


	/**
	 * find the student based by email
	 * @param email 
	 * @return student details on the email id
	 */
	@GetMapping(path="/studentByEmail")
	public @ResponseBody LoginResponse findStudentByEmail(@RequestParam String email ) {
		return studService.findByEmail(email)  ;

	}

	/**
	 * Find all submissions with course abbreviation and assignment number
	 * @param courseAbbr
	 * @param assignmentNo
	 * @return list of the submissions by students for the requirement 
	 */
	@GetMapping(path="/allSubmissionsByCourse")
	public @ResponseBody AllSubmissionResponse allSubmissionsByCourse(@RequestParam String courseAbbr,@RequestParam int assignmentNo,
			@RequestParam List<String> term ,@RequestParam List<Integer> sections) {
		AllSubmissionResponse subs = new AllSubmissionResponse();
		StatusBean status = new StatusBean();
		try {

			String currentTerm = term.get(0);
			List<Integer> ids = courseRepository.findByAbbrTermSections(courseAbbr, currentTerm,  sections);

			List<Integer> assignmentIds = assignmentRepository.findByCourses(ids);

			Set<AssignmentSubmissionEntity> submissions = submissionRepository.findSubmissionByMultpileAssignmentIds(assignmentIds);

			subs.setSubmissions(submissions);
			status.setStatus(Constants.SUCCESS_STATUS);
			status.setStatusCode(Constants.SUCCESS_STATUS_CODE);
			subs.setStatus(status);
		}catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);
			subs.setStatus(status);
		}

		return subs;

	}

	/**
	 * method to get the courses by each term
	 * @param term
	 * @return
	 */
	@RequestMapping(path="/coursesByTerm", method=RequestMethod.GET)
	public @ResponseBody CourseListBean getcoursesByTerm(@RequestParam String term){
		CourseListBean courses = null;
		StatusBean status = new StatusBean();
		Set<CourseEntity> course = null;
		try{
			course = courseRepository.findByTerm(term);
			if(course != null) {
				courses = new CourseListBean();
				courses.setCourses(course);
				status.setStatus(Constants.SUCCESS_STATUS);
				status.setStatusCode(Constants.SUCCESS_STATUS_CODE);
				courses.setStatus(status);	
			}
		}catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());
			courses = new CourseListBean();
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);
			courses.setCourses(course);
			courses.setStatus(status);
		}

		return courses;

	}

	/**
	 * method to get the assignments by each course id
	 * @param courseId
	 * @return
	 */
	@RequestMapping(path="/assignmentsByCourse", method=RequestMethod.GET)
	public @ResponseBody AssignmentListBean getassignmentsByCourse(@RequestParam int courseId){
		AssignmentListBean assignments = null;
		StatusBean status = new StatusBean();
		Set<AssignmentEntity> assignment = null;
		try{
			assignment = assignmentRepository.findByCourse(courseId);
			if(assignment != null) {
				assignments = new AssignmentListBean();
				assignments.setAssignments(assignment);
				status.setStatus(Constants.SUCCESS_STATUS);
				status.setStatusCode(Constants.SUCCESS_STATUS_CODE);
				assignments.setStatus(status);	
			}
		}catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());
			assignments = new AssignmentListBean();
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);
			assignments.setAssignments(assignment);
			assignments.setStatus(status);
		}

		return assignments;

	}

	/**
	 * method to get the course by id
	 * @param courseId
	 * @return
	 */
	@RequestMapping(path="/CourseById", method=RequestMethod.GET)
	public @ResponseBody CourseJsonBean getCourseById(@RequestParam int courseId){
		CourseJsonBean courseResponse = null;
		CourseEntity course = null;

		try{
			course = courseRepository.findByCourseId(courseId);
			if(course != null) {
				courseResponse = new CourseJsonBean();
				courseResponse.setCourseAbbr(course.getCourseAbbr());
				courseResponse.setCourseLoc(course.getCourseLoc());
				courseResponse.setCourseName(course.getCourseName());
				courseResponse.setCourseTerm(course.getCourseTerm());
				courseResponse.setCreatedCourseBy(course.getCreatedCourseBy().getUserId());


			}else {
				courseResponse = new CourseJsonBean();
			}
		}catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());


		}

		return courseResponse;

	}



	/**
	 * Find all submissions with course abbreviation and assignment number with different terms
	 * @param courseAbbr
	 * @param assignmentNo
	 * @return list of the submissions by students for the requirement 
	 */
	@RequestMapping(path="/allSubmissionsByCourseMultipleTerms", method=RequestMethod.POST)
	public @ResponseBody AllSubmissionResponse allSubmissionsByCourseMultipleTerms(@RequestBody MultipleTermSubmissionJson data ) {
		AllSubmissionResponse subs = new AllSubmissionResponse();
		StatusBean status = new StatusBean();
		try {
			List<Integer> courseIds = courseRepository.findByTermAndAbbr(data.getTerm(), data.getCourseAbbr());

			List<Integer> assignmentIds = assignmentRepository.findByCourses(courseIds);

			Set<AssignmentSubmissionEntity> submissions = submissionRepository.findSubmissionByMultpileAssignmentIds(assignmentIds);

			subs.setSubmissions(submissions);
			status.setStatus(Constants.SUCCESS_STATUS);
			status.setStatusCode(Constants.SUCCESS_STATUS_CODE);
			subs.setStatus(status);

		}catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);
			subs.setStatus(status);
		}

		return subs;

	}

	/**
	 * method to get the course by abbreviation
	 * @param courseAbbr
	 * @return
	 */
	@RequestMapping(path="/CourseByAbbr", method=RequestMethod.GET)
	public @ResponseBody List<String> courseByAbbr(@RequestParam String courseAbbr){
		Set<CourseEntity> courses = null;
		List<String> terms = new ArrayList<>();

		try{
			courses = courseRepository.findByMultipleCourseAbbr(courseAbbr);
			if(courses != null) {

				for(CourseEntity c :  courses) {
					terms.add(c.getCourseTerm());
				}
				Collections.sort(terms);
			}
		}catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());


		}

		return terms;

	}

}
