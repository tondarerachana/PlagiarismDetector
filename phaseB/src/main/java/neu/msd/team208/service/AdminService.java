package neu.msd.team208.service;

import neu.msd.team208.JsonResponse.DeleteFileBean;
import neu.msd.team208.JsonResponse.DeleteReportBean;
import neu.msd.team208.JsonResponse.DeleteUserBean;
import neu.msd.team208.JsonResponse.StatusBean;

/**
 * Admin Operations Service Layer
 * @author vihabidre
 *
 */
public interface AdminService {
	
	/**
	 * 
	 * @param deleteUser
	 * @return
	 */
	StatusBean deleteUser(DeleteUserBean deleteUser) throws Exception;
	
	/**
	 * 
	 * @param deleteReport
	 * @return
	 */
	StatusBean deleteReport(DeleteReportBean deleteReport) throws Exception;
	
	/**
	 * 
	 * @param deleteFile
	 * @return
	 */
	StatusBean deleteFile(DeleteFileBean deleteFile) throws Exception;
}
