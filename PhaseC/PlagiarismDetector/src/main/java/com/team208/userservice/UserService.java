package com.team208.userservice;




import com.team208.jsonresponse.LoginJsonBean;
import com.team208.jsonresponse.LoginResponse;
import com.team208.jsonresponse.StatusBean;
import com.team208.jsonresponse.UserJsonBean;

/**
 * service layer with user services
 * @author rachanatondare
 *
 */
public interface UserService {
	
	/**
	 * method to verify user and grant access to user
	 * @param userDetails
	 * @return user
	 */
	public LoginResponse login( LoginJsonBean userDetails); 
	
	/**
	 * method to register a new user
	 * @param user
	 * @return status
	 */
	public StatusBean register(UserJsonBean user);
	
	/**
	 * method to find a user based on northeastern id
	 * @param userId
	 * @return user
	 */
	public LoginResponse findStudent( Long userId ) ;
	
	/**
	 * method to find user from email id
	 * @param email
	 * @return user
	 */
	public LoginResponse findByEmail(String email);

}
