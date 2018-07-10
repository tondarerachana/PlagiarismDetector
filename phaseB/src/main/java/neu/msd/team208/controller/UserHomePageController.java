package neu.msd.team208.controller;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import neu.msd.team208.JsonResponse.CheckSimilarityBean;
import neu.msd.team208.JsonResponse.CheckSimilarityResponse;
import neu.msd.team208.JsonResponse.GetReportsResponseBean;
import neu.msd.team208.JsonResponse.StatusBean;
import neu.msd.team208.JsonResponse.UploadFileBean;
import neu.msd.team208.JsonResponse.UploadFileBeanResponse;
import neu.msd.team208.service.UserHomePageService;

/**
 * controller for user home page APIs
 * @author rachanatondare
 *
 */
@Controller
public class UserHomePageController {
	
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LogFactory.getLog(HomePageController.class);
	
	@Autowired
	UserHomePageService userHomeService;
	
	/**
	 * upload file api
	 * @param fileData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public  UploadFileBeanResponse uploadFile(
			@RequestBody final UploadFileBean fileData)
			throws Exception {

		UploadFileBeanResponse responseBean = null;
		try {

			responseBean = userHomeService.uploadFiles(fileData);

		} catch (Exception e) {
		
			logger.info("Exception", e);
		}

		return responseBean;

	}
	
	/**
	 * check similarity api
	 * @param filesData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkSimilarity", method = RequestMethod.POST)
	@ResponseBody
	public  CheckSimilarityResponse getSimilarity(
			@RequestBody final CheckSimilarityBean filesData)
			throws Exception {

		CheckSimilarityResponse responseBean = null;
		try {

			responseBean = userHomeService.checkSimilarity(filesData);

		} catch (Exception e) {
		
			logger.info("Exception", e);
		}

		return responseBean;
	}
	
	/**
	 *  get reports api
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getReports", method = RequestMethod.GET)
	@ResponseBody
	public  GetReportsResponseBean getReports(
			@RequestBody List<Integer> userId)
			throws Exception {

		GetReportsResponseBean responseBean = null;
		try {

			responseBean = userHomeService.getReports(userId);

		} catch (Exception e) {
		
			logger.info("Exception", e);
		}

		return responseBean;
	}

	/**
	 * email user api
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	@ResponseBody
	public  StatusBean email(
			@RequestBody String emailId)
			throws Exception {

		StatusBean responseBean = null;
		try {

			responseBean = userHomeService.email(emailId);

		} catch (Exception e) {
		
			logger.info("Exception", e);
		}

		return responseBean;
	}
}
