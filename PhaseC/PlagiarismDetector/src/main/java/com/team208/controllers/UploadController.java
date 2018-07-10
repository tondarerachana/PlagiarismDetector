package com.team208.controllers;

/**
 * Reference: 
 * http://www.mkyong.com/spring-boot/spring-boot-file-upload-example/
 * https://stackoverflow.com/questions/37666144/reactjs-component-to-upload-a-file-to-a-spring-mvc-data-rest-server?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * this class defines the rest end point upload file functionality
 * @author viha bidre
 *
 */
@CrossOrigin
@Controller
public class UploadController {

	/**
	 * method to get the uploaded info
	 * @return
	 */
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	/**
	 * method to upload file to DownloadedReports2 folder
	 * @param name
	 * @param file
	 * @param course
	 * @param hw
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String>  handleFileUpload(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file, @RequestParam String course, @RequestParam String hw) throws Exception{

		String current;
		String filePath = "/";
		String downloadedReports="/DownloadedReports2/";
		current = new java.io.File( "." ).getCanonicalPath();
		Path pathAc = Paths.get(current+downloadedReports+filePath+course+filePath+hw);
		Files.createDirectories(pathAc);
		File filepath = new File(pathAc+filePath+name);
    	
        if (name.contains("/")) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Folder separators not allowed.");
        }  else if (!name.endsWith(".zip")) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("File type not allowed.  Must be a Zip file type ending in '.zip'.");
        }

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                try (BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(filepath))){
                		stream.write(bytes);
                    stream.flush();
                }
                
                return ResponseEntity.ok("File " + name + " uploaded.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("You failed to upload " + name + " because the file was empty.");
        }
    }

}

