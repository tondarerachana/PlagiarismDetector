package neu.msd.team208.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.msd.team208.Dao.HomeDao;
import neu.msd.team208.JsonResponse.LoginInput;
import neu.msd.team208.JsonResponse.LoginResponse;
import neu.msd.team208.JsonResponse.RegisterUserBean;

/**
 * service layer for homepage operations
 * @author rachanatondare
 *
 */
@Service
public class HomeServiceImpl implements HomeService{
	
	
	/**
	 * Dao layer
	 */
	@Autowired
	HomeDao homeDao;

	@Transactional	
	public LoginResponse loginUser(LoginInput loginInput) throws Exception {
	
		return homeDao.loginUser(loginInput);
	}

	@Transactional
	public LoginResponse registerUser(RegisterUserBean registerInput) throws Exception {
		return homeDao.registerUser(registerInput);
	}

}
