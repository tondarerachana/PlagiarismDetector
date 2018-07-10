package com.team208.s3.services.impl;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.team208.s3.services.S3Services;

@Service
public class S3ServicesImpl implements S3Services {

	private Logger logger = LoggerFactory.getLogger(S3ServicesImpl.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${jsa.s3.bucket}")
	private String bucketName;

	/**
	 * This function is used to download the file
	 * The Keyname is the name of the key by which is file was 
	 * uploaded to the s3 
	 * The file key of the file will be (*folderKey* /name of the file)
	 */

	@Override
	public void downloadFile(String keyName) {

		byte[] buffer;
		try {
			S3Object s3object = s3client.getObject(bucketName, keyName);
			S3ObjectInputStream inputStream = s3object.getObjectContent();
			buffer = new byte[inputStream.available()];
			while(inputStream.read(buffer) > 0)
				FileUtils.copyInputStreamToFile(inputStream, new File("src/main/resources/test2.html"));
		} catch (IOException e) {
			logger.debug("context from downloadFile", e);
		}
	}

	/**
	 * This function is used to upload the complete folder 
	 * to the s3
	 * @param dirPath is the path of the directory to be uploaded 
	 * @param keyPrefix is the key of the directory 
	 * @param buket_name is the name of the bucket 
	 * @param recursive is the parameter which tells to upload the sub folders 
	 */
	@Override
	public void uploadDirectory(String dirPath,String keyPrefix,String bucketName,Boolean recursive) {
		TransferManager xferMgr = new TransferManager();
		MultipleFileUpload xfer = xferMgr.uploadDirectory(bucketName, keyPrefix, new File(dirPath), recursive);
		try {
			xfer.waitForCompletion();
		} catch(AmazonServiceException e) {
			logger.debug("Amazon sevice error:"+ e.getMessage());
			System.exit(1);
		} catch (AmazonClientException e) {
			logger.debug("Amazon client error:"+ e.getMessage());
			System.exit(1);
			logger.debug("context", e);
		} catch (InterruptedException e) {
			logger.debug("Transfer interrupted:"+ e.getMessage());
			System.exit(1);
			logger.debug("context", e);
		    Thread.currentThread().interrupt();
		}

		xferMgr.shutdownNow();
	}
}
