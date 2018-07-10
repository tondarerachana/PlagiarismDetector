package neu.msd.team208.DaoImpl;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import neu.msd.team208.Dao.AdminDao;
import neu.msd.team208.Helper.AdminUtils;
import neu.msd.team208.JsonResponse.DeleteFileBean;
import neu.msd.team208.JsonResponse.DeleteReportBean;
import neu.msd.team208.JsonResponse.DeleteUserBean;
import neu.msd.team208.JsonResponse.StatusBean;
import neu.msd.team208.controller.AdminPageController;

@Repository
public class AdminDaoImpl implements AdminDao{
	
	private static final Logger logger = (Logger) LogFactory.getLog(AdminPageController.class);

	/**
	 * method to delete user
	 */
	public StatusBean deleteUser(DeleteUserBean deleteuser) throws Exception {
		StatusBean statusBean = null;
		AdminUtils adminUtils = null;
		boolean userExists = false;
		
		adminUtils = new AdminUtils();
		userExists =  adminUtils.checkUserExists(deleteuser.getUserId());
		
		if(userExists) {
			logger.info("User Exists");
		}
		statusBean = new StatusBean();

		return statusBean;
	}

	/**
	 *   method to delete report
	 */
	public StatusBean deleteReport(DeleteReportBean deleteReport) throws Exception {
		StatusBean statusBean = null;

		AdminUtils adminUtils = null;
		boolean reportExists = false;
		
		adminUtils = new AdminUtils();
		reportExists =  adminUtils.checkFileExists(deleteReport.getReportId());
		
		if(reportExists) {
			logger.info("report Exists");
		}
		
		statusBean = new StatusBean();

		return statusBean;
	}

	/**
	 *  method to delete file
	 */
	public StatusBean deleteFile(DeleteFileBean deleteFile) throws Exception {
		StatusBean statusBean = null;

		AdminUtils adminUtils = null;
		boolean fileExists = false;
		
		adminUtils = new AdminUtils();
		fileExists =  adminUtils.checkFileExists(deleteFile.getFileId());
		
		if(fileExists) {
			logger.info("file Exists");
		}
		statusBean = new StatusBean();

		return statusBean;
	}

}
