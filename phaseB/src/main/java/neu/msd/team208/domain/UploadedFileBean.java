package neu.msd.team208.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * domian class for file table
 * @author rachanatondare
 *
 */
@Entity
@Table(name="Uploaded_File")
public class UploadedFileBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer fileId;
	private Integer userId;
	private String fileName;
	private String filePath;
	
	
	@Id
	@Column(name = "file_Id", unique = true, nullable = false, precision = 20, scale = 0)
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	@ForeignKey(name ="User_UploadedFile_Aggregation_FK")
	@Column(name = "user_Id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "file_Name", nullable = false, precision = 50, scale = 0)
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "file_Path", nullable = false, precision = 50, scale = 0)
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	

}
