package neu.msd.team208.JsonResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author rachanatondare
 *
 */
public class GetReportsResponseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> reportPath;

	
	public List<String> getReportPath() {
		return reportPath;
	}

	public void setReportPath(List<String> reportPath) {
		this.reportPath = reportPath;
	}

	
	

}
