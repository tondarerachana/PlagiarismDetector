package com.team208.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.team208.s3.services.S3Services;

/**
 * class with S3  setup
 * @author aman rayat
 *
 */
@SpringBootApplication
public class SpringS3AmazonApplication implements CommandLineRunner{

	@Autowired
	S3Services s3Services;

	@Value("${jsa.s3.key}")
	private String uploadKey;
	
	@Value("${jsa.s3.bucket}")
	private String bucket;
		
	private String downloadKey = "s3uploadtest3/test2.html";
	
	@Override
	public void run(String... args) throws Exception {
		// Does nothing
	}
}
