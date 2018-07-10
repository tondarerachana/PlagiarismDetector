package com.team208.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team208.domain.CourseEntity;
import com.team208.domain.CourseRepository;
import com.team208.domain.UserEntity;
import com.team208.domain.UserRepository;

/**
 * class is defines admin functionality controllers
 * @author rachanatondare
 *
 */
@CrossOrigin
@Controller
@RequestMapping(path="/team208") 
public class AdminController {

	private static final Logger logger = 
			Logger.getLogger(AdminController.class.getName());

	@Autowired 
	private CourseRepository courseRepository;

	@Autowired 
	private UserRepository userRepository;

	/**
	 * method to get all courses
	 * @return list of courses
	 */
	@GetMapping(path="/allCourses")
	public @ResponseBody Iterable<CourseEntity> getAllCourses() {
		// This returns a JSON or XML with the users
		return courseRepository.findAll();
	}

	/**
	 * method to get all users
	 * @return list of users
	 */
	@GetMapping(path="/all")
	public @ResponseBody Iterable<UserEntity> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}
