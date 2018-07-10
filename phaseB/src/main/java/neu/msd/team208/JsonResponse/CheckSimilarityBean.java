package neu.msd.team208.JsonResponse;

import java.io.Serializable;
import java.util.List;

/**
 *  reposnse for file bean
 * @author rachanatondare
 *
 */
public class CheckSimilarityBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> filePaths ;
	private Integer percentageSimilarity;
	private Integer userId;
	
	
	public List<String> getFilePaths() {
		return filePaths;
	}
	public void setFilePaths(List<String> filePaths) {
		this.filePaths = filePaths;
	}
	public Integer getPercentageSimilarity() {
		return percentageSimilarity;
	}
	public void setPercentageSimilarity(Integer percentageSimilarity) {
		this.percentageSimilarity = percentageSimilarity;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
