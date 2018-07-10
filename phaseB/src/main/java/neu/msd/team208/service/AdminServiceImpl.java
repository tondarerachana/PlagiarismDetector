package neu.msd.team208.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.msd.team208.Dao.AdminDao;
import neu.msd.team208.JsonResponse.DeleteFileBean;
import neu.msd.team208.JsonResponse.DeleteReportBean;
import neu.msd.team208.JsonResponse.DeleteUserBean;
import neu.msd.team208.JsonResponse.StatusBean;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao adminDao;
	
	@Transactional
	public StatusBean deleteUser(DeleteUserBean deleteUser) throws Exception{
		return adminDao.deleteUser(deleteUser);
	}
	
	@Transactional
	public StatusBean deleteReport(DeleteReportBean deleteReport) throws Exception{
		return adminDao.deleteReport(deleteReport);
	}

	@Transactional
	public StatusBean deleteFile(DeleteFileBean deleteFile) throws Exception{
		return adminDao.deleteFile(deleteFile);
	}

}
