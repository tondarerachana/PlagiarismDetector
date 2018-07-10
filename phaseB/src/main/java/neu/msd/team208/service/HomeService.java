package neu.msd.team208.service;

import neu.msd.team208.JsonResponse.LoginInput;
import neu.msd.team208.JsonResponse.LoginResponse;
import neu.msd.team208.JsonResponse.RegisterUserBean;

/**
 * Service layer
 * @author rachanatondare
 * @version 1.0.0
 */
public interface HomeService {
	
	/**
	 * 
	 * @param loginInput
	 * @return
	 * @throws Exception
	 */
	public LoginResponse loginUser( LoginInput loginInput) throws Exception;

	/**
	 * 
	 * @param registerInput
	 * @return
	 * @throws Exception
	 */
	public LoginResponse registerUser( RegisterUserBean registerInput) throws Exception;
	
	
}
