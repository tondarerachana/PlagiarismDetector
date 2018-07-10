package neu.msd.team208.controller;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import neu.msd.team208.JsonResponse.LoginInput;
import neu.msd.team208.JsonResponse.LoginResponse;
import neu.msd.team208.JsonResponse.RegisterUserBean;
import neu.msd.team208.service.HomeService;

/**
 *  controller for user user home page APIs
 * @author rachanatondare
 *
 */
@Controller
public class HomePageController {

	
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LogFactory.getLog(HomePageController.class);
	
	
	@Autowired
	HomeService homeService;
	
	/**
	 * Login functionality
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse authenticateUser(
			@RequestBody final LoginInput input)
			throws Exception {

		LoginResponse responseBean = null;
		try {

			responseBean = homeService.loginUser(input);

		} catch (Exception e) {
		
			logger.info("Exception", e);
		}

		return responseBean;

	}
	
	/**
	 * regiter api
	 * @param registerInput
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse registerUser(
			@RequestBody final RegisterUserBean registerInput)
			throws Exception {

		LoginResponse responseBean = null;
		try {

			responseBean = homeService.registerUser(registerInput);

		} catch (Exception e) {
		
			logger.info("Exception", e);
		}

		return responseBean;

	}
	
}
