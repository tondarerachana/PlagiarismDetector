package com.team208.s3.services;

/**
 * 
 * @author amanrayat
 * 
 *
 */
public interface S3Services {
	
	/**
	 * This function is used to download the file
	 * The Keyname is the name of the key by which is file was 
	 * uploaded to the s3 
	 * The file key of the file will be (*folderKey* /name of the file)
	 */

	public void downloadFile(String keyName);
	

	/**
	 * This function is used to upload the complete folder 
	 * to the s3
	 * @param dir_path is the path of the directory to be uploaded 
	 * @param key_prefix is the key of the directory 
	 * @param buket_name is the name of the bucket 
	 * @param recursive is the parameter which tells to upload the sub folders 
	 */
	
	public void uploadDirectory(String dir_path,String key_prefix,String bucket_name,Boolean recursive);
}
