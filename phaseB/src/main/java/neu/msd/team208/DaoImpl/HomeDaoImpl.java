package neu.msd.team208.DaoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import neu.msd.team208.Dao.HomeDao;
import neu.msd.team208.Helper.RegisterUtils;
import neu.msd.team208.JsonResponse.LoginInput;
import neu.msd.team208.JsonResponse.LoginResponse;
import neu.msd.team208.JsonResponse.RegisterUserBean;

/**
 * Implementation for services on home page
 * @author rachanatondare
 *
 */
@Repository
public class HomeDaoImpl implements HomeDao{


	/**
	 * Login service for user
	 */
	public LoginResponse loginUser(LoginInput loginInput) throws Exception {


		LoginResponse response = null;

		response = new LoginResponse();

		return response;
	}


	/**
	 *  regiter user service
	 */
	public LoginResponse registerUser(RegisterUserBean registerInput) throws Exception {

		LoginResponse response = null;
		RegisterUtils userUtils = null;

		userUtils = new RegisterUtils();

		boolean userExistsFlag =  userUtils.userExists(registerInput.getEmailId());

		response = new LoginResponse();
		if(userExistsFlag) {
			response.setStatusCode(101);

		}else
		{
			response.setStatusCode(0);
		}


		return response;
	}







}
