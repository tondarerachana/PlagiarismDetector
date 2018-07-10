package neu.msd.team208.Dao;

import java.util.List;

import neu.msd.team208.JsonResponse.CheckSimilarityBean;
import neu.msd.team208.JsonResponse.CheckSimilarityResponse;
import neu.msd.team208.JsonResponse.GetReportsResponseBean;
import neu.msd.team208.JsonResponse.StatusBean;
import neu.msd.team208.JsonResponse.UploadFileBean;
import neu.msd.team208.JsonResponse.UploadFileBeanResponse;

/**
 * Interface Layer
 * @author rachanatondare
 * @version 1.0.0
 */
public interface UserHomePageDao {
	
	/**
	 * 
	 * @param loginInput
	 * @return
	 * @throws Exception
	 */
	public UploadFileBeanResponse uploadFiles(UploadFileBean fileData) throws Exception;
	
	/**
	 * 
	 * @param filesData
	 * @return
	 * @throws Exception
	 */
	public CheckSimilarityResponse checkSimilarity(CheckSimilarityBean filesData) throws Exception;

	

	/**
	 * 
	 * @param filesData
	 * @return
	 * @throws Exception
	 */
	public GetReportsResponseBean getReports( List<Integer> userID) throws Exception;

	/**
	 * 
	 * @param emailId
	 * @return
	 */
	public StatusBean email(String emailId) throws Exception;
}
