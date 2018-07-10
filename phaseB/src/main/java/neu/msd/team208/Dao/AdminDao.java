package neu.msd.team208.Dao;

import neu.msd.team208.JsonResponse.DeleteFileBean;
import neu.msd.team208.JsonResponse.DeleteReportBean;
import neu.msd.team208.JsonResponse.DeleteUserBean;
import neu.msd.team208.JsonResponse.StatusBean;

/**
 *  interface for admin functionalities
 * @author rachanatondare
 *
 */
public interface AdminDao {
	
	/**
	 * 
	 * @param deleteuser
	 * @return
	 * @throws Exception
	 */
	StatusBean deleteUser(DeleteUserBean deleteuser) throws Exception;
	
	/**
	 * 
	 * @param deleteReport
	 * @return
	 * @throws Exception
	 */
	StatusBean deleteReport(DeleteReportBean deleteReport) throws Exception;
	
	/**
	 * 
	 * @param deleteFile
	 * @return
	 * @throws Exception
	 */
	StatusBean deleteFile(DeleteFileBean deleteFile) throws Exception;
}
