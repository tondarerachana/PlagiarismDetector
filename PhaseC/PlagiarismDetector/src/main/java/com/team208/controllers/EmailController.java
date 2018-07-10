package com.team208.controllers;

import java.util.List;
import java.util.logging.Logger;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.team208.jsonresponse.EmailJsonResponse;
import com.team208.jsonresponse.StatusBean;
import com.team208.utilities.Constants;

/**
 * this class defines the rest end point for email functionality
 * @author rachanatondare
 *
 */
@CrossOrigin
@RestController
@RequestMapping(path="/team208")
public class EmailController {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(EmailController.class.getName());

	@Autowired
	private JavaMailSender sender;


	/**
	 * method to send emails to passed lsit of emails
	 * @param emails
	 * @return
	 */
	@RequestMapping(path="/Email", method = RequestMethod.POST)
	@ResponseBody StatusBean home(@RequestBody List<EmailJsonResponse> emails) {
		StatusBean status = new StatusBean();
		try {


			for(EmailJsonResponse s : emails) {

				sendEmail(s.getEmail(), s.getContent(), s.getLink());
			}
			status.setStatus(Constants.SUCCESS_STATUS);
			status.setStatusCode(Constants.SUCCESS_STATUS_CODE);


		} catch (Exception e) {
			LOGGER.info(Constants.CONTEXT+e.getMessage());
			status.setStatus(Constants.FAILURE_EXCEPTION_STATUS);
			status.setStatusCode(Constants.FAILURE_EXCEPTION_STATUS_CODE);

		}
		return status;
	}

	/**
	 * method to create body of email based on content
	 * @param email
	 * @param content
	 * @param link
	 */
	private void sendEmail(String email, String content, String link) {
		MimeMessage message = null;
		try
		{
			message = sender.createMimeMessage();
			String msg ="";
			// Enable the multipart flag!

			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			if(content.equals("DROP")) {
				msg = "<b>YOUR COURSE WAS DROPPED SUCCESSFULLY </b>";
			}else if(content.equals("ADD")) {
				msg = "<b>YOUR COURSE WAS ADDED SUCCESSFULLY </b>";
			}else   if(content.equals("CAUGHT")) {
				msg = "<b><font color=red>YOUR CAUGHT FOR PLAGIARISM.PLEASE MEET THE PROFESSOR DURING HIS VISITING HOURS </font></b>";
			}else   if(content.equals("REPORT")) {
				msg = "<b>YOUR REPORT HAS BEEN GENRERATED. PLEASE FIND THE LINK TO DOWNLOAD THE REPORT </b><br>"+ link;

			}else {
				msg = "<b>SYSTEM ERROR TRY AGAIN. CONTACT ADMIN RACHANA </b>";

			}


			helper.setTo(email);

			helper.setText(msg, true);

			helper.setSubject("PLAGIARISM APP STATUS");
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(msg,"UTF-8","html");

		}

		catch (Exception e) {
			LOGGER.info(Constants.CONTEXT+e.getMessage());

		}

		sender.send(message);
	}


}
