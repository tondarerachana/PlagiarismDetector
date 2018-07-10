package com.team208.controllers;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.team208.domain.UserEntity;
import com.team208.domain.UserRepository;
import com.team208.jsonresponse.StatusBean;
import com.team208.jsonresponse.UserJsonBean;
import com.team208.utilities.Constants;

/**
 * 
 * @author amanrayat
 * This is a Rest controller used for mapping the requests
 */

@CrossOrigin
@RestController
@RequestMapping(path="/team208")
public class AdminController2 {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(AdminController2.class.getName());
	@Autowired
	UserRepository userrepository;

	/**
	 * This mapping is used to add a new user
	 * @param student
	 * @return
	 */
	@PostMapping("/admin/user")
	public UserEntity createUser(@RequestBody UserEntity student) {
		return userrepository.save(student);
	}

	/**
	 * This mapping is used to find all the users 
	 * @return
	 */
	@GetMapping("/admin/user")
	public List<UserEntity> findAllUsers(){
		return (List<UserEntity>) userrepository.findAll();
	}

	/**
	 * @author rachanatondare
	 * This mapping is used to update the user. 
	 * We can pass any or all of the parameters 
	 * Only the changed parameter will be updated.
	 * @param UserId
	 * @param newUser
	 * @return
	 */
	@RequestMapping(path="/updateUser", method = RequestMethod.PUT ) // Map ONLY GET Requests
	public StatusBean updateUserById(@RequestBody UserJsonBean user ) {
		StatusBean status = new StatusBean();
		try {

			UserEntity n = userrepository.findByNEUId(user.getUserId()) ;

			if(userrepository.existsById(n.getUserDBid())) {
				n.setUserId(user.getUserId());
				n.setName(user.getName());
				n.setUserRole(user.getUserRole());
				n.setPassword(user.getPassword());
				n.setEmail(user.getEmail());

				userrepository.save(n);

				status.setStatus(Constants.SUCCESS_STATUS);
				status.setStatusCode(Constants.SUCCESS_STATUS_CODE);
			}else {
				status.setStatus(Constants.UNREGISTERED_STATUS);
				status.setStatusCode(Constants.UNREGISTERED_STATUS_CODE);
			}
		}catch (Exception e) {

			LOGGER.info(Constants.CONTEXT+e.getMessage());
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);
		}

		return status;
	}

	/**
	 * This mapping deletes the user with the given userId 
	 * @param UserId
	 */
	@DeleteMapping("/admin/user/{userId}")
	public void deleteUserById(@PathVariable ("userId") long userId) {
		int dbId = userrepository.findByNEUId(userId).getUserDBid();
		userrepository.deleteById(dbId);
	}


}

