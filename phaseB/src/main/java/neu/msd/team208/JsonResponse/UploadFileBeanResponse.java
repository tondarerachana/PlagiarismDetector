package neu.msd.team208.JsonResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author rachanatondare
 *
 */
public class UploadFileBeanResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> filePath ;

	public List<String> getFilePath() {
		return filePath;
	}

	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}
	

}
