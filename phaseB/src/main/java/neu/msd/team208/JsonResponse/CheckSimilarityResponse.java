package neu.msd.team208.JsonResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author rachanatondare
 *
 */
public class CheckSimilarityResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> filePaths ;
	private String reportPath;
	
	public List<String> getFilePaths() {
		return filePaths;
	}
	public void setFilePaths(List<String> filePaths) {
		this.filePaths = filePaths;
	}
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
	

}
