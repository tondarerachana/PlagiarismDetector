package neu.msd.team208.service;

import java.util.List;

import neu.msd.team208.JsonResponse.CheckSimilarityBean;
import neu.msd.team208.JsonResponse.CheckSimilarityResponse;
import neu.msd.team208.JsonResponse.GetReportsResponseBean;
import neu.msd.team208.JsonResponse.StatusBean;
import neu.msd.team208.JsonResponse.UploadFileBean;
import neu.msd.team208.JsonResponse.UploadFileBeanResponse;

/**
 * Service layer
 * @author rachanatondare
 * @version 1.0.0
 */
public interface UserHomePageService {
	
	/**
	 * 
	 * @param fileData
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
	 * @param userID
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
