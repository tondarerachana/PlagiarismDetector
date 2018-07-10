package com.team208.controllers;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import com.team208.domain.UserCourseEntity;
import com.team208.domain.UserCourseRepository;
import com.team208.domain.UserEntity;
import com.team208.domain.UserRepository;
import com.team208.jsonresponse.StatusBean;
import com.team208.jsonresponse.StudentSubmissionJsonBean;
import com.team208.jsonresponse.SubmissionResponseBean;
import com.team208.jsonresponse.UpdateSubmissionRequestBean;
import com.team208.utilities.Constants;

/**
 * this class defines the rest end point student  functionality
 * @author rachanatondare
 *
 */
@CrossOrigin
@Controller
@RequestMapping(path="/team208") 
public class StudentController {

	private static final Logger logger = 
			Logger.getLogger(StudentController.class.getName());

	@Autowired 
	private UserRepository userRepository;

	@Autowired 
	private CourseRepository courseRepository;

	@Autowired
	private UserCourseRepository userCourseRepository;

	@Autowired 
	private AssignmentRepository assignmentRepository;

	@Autowired 
	private AssignmentSubmissionRepository submissionRepository;

	/**
	 * method for students to  register for courses
	 * @param userId
	 * @param courseId
	 * @return status
	 */
	@GetMapping(path="/registerStudentCourses") // Map ONLY GET Requests
	public @ResponseBody StatusBean addStudentCourses (@RequestParam Long userId, @RequestParam List<Integer> courseId) {
		StatusBean status = null;
		try {	

			Iterable<CourseEntity> regCourses = courseRepository.findAllById(courseId);
			for(CourseEntity c1 : regCourses) {

				UserCourseEntity uc = new UserCourseEntity();
				uc.setUser(userRepository.findByNEUId(userId));
				uc.setCourse(c1);
				userCourseRepository.save(uc);	
			}
			status = new StatusBean();
			status.setStatus(Constants.SUCCESS_STATUS);
			status.setStatusCode(Constants.SUCCESS_STATUS_CODE);

		}
		catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());
			status = new StatusBean();
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);
		}

		return status;

	}

	/**
	 * get list of courses based on neu id
	 * @param userId
	 * @return
	 */
	@GetMapping(path="/getStudentCourses")
	public @ResponseBody  Set<CourseEntity> getStudentCourses(@RequestParam long userId){
		Set<CourseEntity> courses = new  HashSet<>();
		try {
			UserEntity user = userRepository.findByNEUId(userId);
			int id = user.getUserDBid();

			Iterable<UserCourseEntity> cs = userCourseRepository.findCourseById(id);
			Iterator<UserCourseEntity> itr = cs.iterator();
			while(itr.hasNext()) {
				courses.add(itr.next().getCourse());
			}
		}catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());

		}

		return courses;

	}


	/**
	 * method to submit a submission for an assignment
	 * @param submisson
	 * @return
	 */
	@RequestMapping(path="/submitSubmission", method= RequestMethod.POST)
	public @ResponseBody StatusBean assignmentSubmission (@RequestBody StudentSubmissionJsonBean submisson) {

		StatusBean status = new StatusBean();
		try {

			Long studentId = 	submisson.getStudentId();
			int assignmentId =  submisson.getAssignmentId();

			long time = System.currentTimeMillis();
			Timestamp timestamp = new Timestamp(time);

			if(assignmentRepository.existsById(assignmentId) ) {
				UserEntity student = userRepository.findByNEUId(studentId);
				AssignmentEntity assignment = assignmentRepository.findById(submisson.getAssignmentId());
				AssignmentSubmissionEntity sub = new AssignmentSubmissionEntity();

				sub.setAssignmentId(assignment);
				sub.setStudent(student);
				sub.setGitLink(submisson.getGitLink());
				sub.setTimestamp(timestamp);

				submissionRepository.save(sub);
				status.setStatusCode(Constants.SUCCESS_STATUS_CODE);
				status.setStatus(Constants.SUCCESS_STATUS);
			}else {
				status.setStatusCode(600);
				status.setStatus("Assignment doesnt exist");
			}

		}catch (Exception e) {

			logger.info(Constants.CONTEXT+e.getMessage());
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);
		}

		return status;
	}

	/**
	 * method to update an already submitted submission
	 * @param submisson
	 * @return
	 */
	@RequestMapping(path="/updateSubmission", method= RequestMethod.PUT)
	public @ResponseBody StatusBean updateSubmission ( @RequestBody UpdateSubmissionRequestBean submisson) {

		StatusBean status = new StatusBean();
		try {
			if(submissionRepository.existsById(submisson.getSubmissionId())) {

				long time = System.currentTimeMillis();
				Timestamp timestamp = new Timestamp(time);

				AssignmentSubmissionEntity sub = submissionRepository.findById(submisson.getSubmissionId());

				sub.setGitLink(submisson.getGitLink());
				sub.setTimestamp(timestamp);

				submissionRepository.save(sub);
				status.setStatusCode(Constants.SUCCESS_STATUS_CODE);
				status.setStatus(Constants.SUCCESS_STATUS);

			}else {
				status.setStatusCode(601);
				status.setStatus("Submission doesnt exist");
			}

		}catch (Exception e) {

			logger.info(Constants.CONTEXT+e.getMessage());
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);
		}

		return status;
	}

	/**
	 * method to delete and existing submission
	 * @param submissionId
	 * @return
	 */
	@RequestMapping(path="/deletSubmission", method = RequestMethod.GET  ) 
	public @ResponseBody  StatusBean deletSubmission(@RequestParam int submissionId){
		StatusBean status = new StatusBean();
		try {

			if(submissionRepository.existsById(submissionId)) {
				submissionRepository.deleteById(submissionId);

			}else {
				status.setStatusCode(601);
				status.setStatus("Submission doesnt exist");
			}

		}catch (Exception e) {

			logger.info(Constants.CONTEXT+e.getMessage());
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);

		}
		return status;

	}

	/**
	 * method to get submission for each student
	 * @param userId
	 * @return list of submissions
	 */
	@GetMapping(path="/getStudentSubmissions")
	public @ResponseBody  Set<SubmissionResponseBean> getStudentSubmissions(@RequestParam long userId){
		Set<SubmissionResponseBean> submission = new  HashSet<>();
		try {
			UserEntity user = userRepository.findByNEUId(userId);
			Set<AssignmentSubmissionEntity> id = user.getSubmissions();

			for(AssignmentSubmissionEntity i : id) {
				SubmissionResponseBean sub = new SubmissionResponseBean();
				AssignmentEntity assignment = assignmentRepository.findById(i.getAssignmentId().getAssignmentId());
				CourseEntity course = assignment.getAssignmentCourse();
				sub.setSubmissionId(i.getSubmissionId());
				sub.setCourseAbbr(course.getCourseAbbr());
				sub.setAssignmentName(assignment.getAssignmentName());
				sub.setGitLink(i.getGitLink());
				sub.setSubmissionTime(i.getTimestamp());

				submission.add(sub);
			}

		}catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());
		}

		return submission;

	}

	/**
	 * deletes the user course registration
	 * @param user
	 * @param courseId
	 * @return
	 */
	@RequestMapping(path="/dropCourse", method = RequestMethod.GET)
	public @ResponseBody  StatusBean dropCourse(@RequestParam long userId, @RequestParam int courseId)
	{
		StatusBean status = new StatusBean();
		try {

			UserEntity user = userRepository.findByNEUId(userId);
			int userdbId = user.getUserDBid();
			UserCourseEntity  userCourse = userCourseRepository.findCourseTodrop(userdbId, courseId);
			if(userCourse != null) {
				userCourseRepository.delete(userCourse);
				status.setStatusCode(Constants.SUCCESS_STATUS_CODE);
				status.setStatus(Constants.SUCCESS_STATUS);
			}else {
				status.setStatusCode(Constants.UNREGISTERED_COURSE_STATUS_CODE);
				status.setStatus(Constants.UNREGISTERED_COURSE_STATUS);

			}

		}catch (Exception e) {
			logger.info(Constants.CONTEXT+e.getMessage());
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);
		}

		return status;

	}


}
