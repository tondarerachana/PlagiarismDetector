package neu.msd.team208.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 * domian class for report table
 * @author rachanatondare
 *
 */
@Entity
@Table(name="Report")
public class ReportBean  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer reportId;
	private Integer userId;
	private String reportName;
	private String reportPath;
	
	@Id
	@Column(name = "report_Id", unique = true, nullable = false, precision = 20, scale = 0)
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	
	@ForeignKey(name ="User_Report_Aggregation_FK")
	@Column(name = "user_Id")
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "report_Name", nullable = false, precision = 50, scale = 0)
	public String getReportName() {
		return reportName;
	}
	
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	@Column(name = "report_Path", nullable = false, precision = 50, scale = 0)
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	

}
