package neu.msd.team208.DaoImpl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Repository;

import neu.msd.team208.Dao.UserHomePageDao;
import neu.msd.team208.Helper.FileComapreUtilities;
import neu.msd.team208.Helper.Node;
import neu.msd.team208.JsonResponse.CheckSimilarityBean;
import neu.msd.team208.JsonResponse.CheckSimilarityResponse;
import neu.msd.team208.JsonResponse.GetReportsResponseBean;
import neu.msd.team208.JsonResponse.StatusBean;
import neu.msd.team208.JsonResponse.UploadFileBean;
import neu.msd.team208.JsonResponse.UploadFileBeanResponse;

/**
 * Implementation for services on user home page
 * @author rachanatondare
 *
 */
@Repository
public class UserHomePageDaoImpl implements UserHomePageDao {

	/**
	 * 
	 */
	public UploadFileBeanResponse uploadFiles(UploadFileBean fileData) throws Exception {
		
		UploadFileBeanResponse response = null;
				
		response = new UploadFileBeanResponse();
		return response;
	}

	/**
	 * 
	 */
	public GetReportsResponseBean getReports( List<Integer> userID) throws Exception {
		
		GetReportsResponseBean response = null;
		
		response = new  GetReportsResponseBean();
		
		return response;
	}

	
	/**
	 * 
	 */
	public CheckSimilarityResponse checkSimilarity(CheckSimilarityBean filesData) throws Exception {
		
		CheckSimilarityResponse response = null;
		FileComapreUtilities fileUtil = null;
		String tree =null;
		Node n = null ;
		
		response = new  CheckSimilarityResponse();
		fileUtil = new FileComapreUtilities();
		File f = getFileFrmPath(filesData.getFilePaths());
		 
		tree = fileUtil.recurseTree(n, 10, f);
		return response;
		
		
	}

	private File getFileFrmPath(List<String> filePaths) {
			File f = new File(filePaths.get(0));
			
		return f;
	}

	/**
	 * 
	 */
	public StatusBean email(String emailId) throws Exception {
		
		StatusBean status = null;
		
		status = new StatusBean();
		return status;
	}
	
	 
	
	
}
