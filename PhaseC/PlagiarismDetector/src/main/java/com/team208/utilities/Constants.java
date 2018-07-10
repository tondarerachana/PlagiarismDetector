package com.team208.utilities;

/**
 * class with all constants defined.
 * @author rachanatondare
 *
 */
public final class Constants {

	private Constants() {
		super();
	}

	public static final String CONTEXT = "Context : ";

	public static final String FAILURE_EXCEPTION_STATUS = "Fail";
	public static final Integer FAILURE_EXCEPTION_STATUS_CODE= 500;

	public static final String SUCCESS_STATUS = "success";
	public static final Integer SUCCESS_STATUS_CODE = 0;

	public static final String UNREGISTERED_STATUS = " Unregistered user";
	public static final Integer UNREGISTERED_STATUS_CODE = 201;


	public static final String 	INCORRECT_CREDENTIALS = "Incorrect Passowrd";
	public static final Integer INCORRECT_CREDENTIALS_CODE = 202;

	public static final String 	UNAVAILABLE = "Doesn't exist";
	public static final Integer UNAVAILABLE_CODE = 203;

	public static final String UNAVAILABLE_COURSE= "Course Unavailable";
	public static final Integer UNAVAILABLE_COURSE_CODE = 204;

	public static final String UNAVAILABLE_ASSIGNMENT = "Assignment Unavailable";
	public static final Integer UNAVAILABLE_ASSIGNMENT_CODE = 205;

	public static final String UNREGISTERED_COURSE_STATUS = " Unregistered course";
	public static final Integer UNREGISTERED_COURSE_STATUS_CODE = 206;
    public static final String EXECCOMMAND="java -jar jplag-2.11.9-SNAPSHOT-jar-with-dependencies.jar -l ";

}
