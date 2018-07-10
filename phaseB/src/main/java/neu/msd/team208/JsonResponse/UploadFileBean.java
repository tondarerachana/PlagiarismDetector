package neu.msd.team208.JsonResponse;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author rachanatondare
 *
 */
public class UploadFileBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<MultipartFile> codeFiles;
	private Integer userId;
	
	
	public List<MultipartFile> getCodeFiles() {
		return codeFiles;
	}
	public void setCodefiles(List<MultipartFile> codeFiles) {
		this.codeFiles = codeFiles;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
