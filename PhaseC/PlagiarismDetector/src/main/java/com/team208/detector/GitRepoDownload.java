package com.team208.detector;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import com.team208.utilities.Constants;

/**
 * This class takes the link of a github repository , studentID,homework, course and downloads the github repositories 
 * @author viha bidre
 *
 */
public class GitRepoDownload {
	//Declare variables 
	BufferedWriter bw = null;	
	private GitRepoDownload() {
		throw new IllegalStateException("Utility class");
	}
	private static final Logger LOGGER = Logger.getLogger(GitRepoDownload.class.getName());
	/**
	 * 
	 * @param course
	 * @param hw
	 * @param studentID
	 * @param gitRepoLink  
	 * @throws IOException
	 */
	//Method to download a repository from github 
	public static  void downloadRepo(String course, String hw,String studentID, String gitRepoLink, String lang) throws  IOException{
		//Declare variables
		String current;
		String filePath = "/";
		String downloadedReports="/DownloadedReports/";
		try {
			current = new java.io.File( "." ).getCanonicalPath();
			//Create path
			Path path = Paths.get(current+downloadedReports+filePath+course+filePath+hw);
			//Download on this path
			File localPath = null;
			if(gitRepoLink.contains("github.com")) {
				Files.createDirectories(path);
				localPath = File.createTempFile(studentID+"-", "",new File(path.toString()));
				Files.delete(localPath.toPath());
				Git.cloneRepository()
				.setURI(gitRepoLink)
				.setDirectory(localPath)
				.call() ;
			}
			else {
				unZipIt(gitRepoLink);
				localPath = new File(gitRepoLink.split("/")[gitRepoLink.split("/").length - 1].replaceAll(".zip", ""));
			}

			List<File> files = getAllPYFiles(localPath,lang);
			Path pathAc = Paths.get(current+downloadedReports+filePath+course+filePath+hw + "actual");
			Files.createDirectories(pathAc);
			Path pathAc2 = Paths.get(current+downloadedReports+filePath+course+filePath+hw + "actual/" + studentID);
			Files.createDirectories(pathAc2);
			for(File file : files) {
				String originalName = file.getName();
				File newFile = new File(pathAc2+filePath+originalName);
				byte[] encoded = Files.readAllBytes(Paths.get(file.getCanonicalPath()));
				String content =  new String(encoded, Charset.defaultCharset());
				//write content to file
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))){
					bw.write(content);
				}

			}
			FileUtils.deleteDirectory(new File(path.toString()));

		} catch (GitAPIException|IOException e) {
			LOGGER.info("Context : "+e.getMessage());
		} 
	}
	/**
	 * 
	 * @param dir
	 * @return fileTree
	 */
	//Method to filter only .py files from downloaded when github repositories for students are downloaded
	public static List<File> getAllPYFiles(File dir, String lang){
		List<File> fileTree = new ArrayList<>();
		if(dir==null||dir.listFiles()==null){
			return fileTree; 
		}
		for (File entry : dir.listFiles()) {
			if (entry.isFile()) {
				if(lang.equals("java17")|| lang.equals("java15")||lang.equals("java12") || lang.equals("java11")) {
					if(entry.getName().endsWith(".java"))
						fileTree.add(entry);
				}
				if(lang.equals("python3")) {
					if(entry.getName().endsWith(".py"))
						fileTree.add(entry);
				}
				if(lang.equals("c/c++")){
					if(entry.getName().endsWith(".cpp"))
						fileTree.add(entry);
				}

			} 
			else fileTree.addAll(getAllPYFiles(entry,lang));
		}
		return fileTree;
	}

	public static void unZipIt(String zipFile1){

		 try {
				try(ZipFile zipFile = new ZipFile(zipFile1)){
					Enumeration<?> enu = zipFile.entries();
					while (enu.hasMoreElements()) {
						ZipEntry zipEntry = (ZipEntry) enu.nextElement();

						String name = zipEntry.getName();
						long size = zipEntry.getSize();
						long compressedSize = zipEntry.getCompressedSize();
						File file = new File(name);
						if (name.endsWith("/")) {
							file.mkdirs();
							continue;
						}

						File parent = file.getParentFile();
						if (parent != null) {
							parent.mkdirs();
						}

						InputStream is = zipFile.getInputStream(zipEntry);
						try(FileOutputStream fos = new FileOutputStream(file)){
							byte[] bytes = new byte[1024];
							int length;
							while ((length = is.read(bytes)) >= 0) {
								fos.write(bytes, 0, length);
							}
							is.close();
							fos.flush();
						}
					}
				}
			} catch (IOException e) {
				LOGGER.info(Constants.CONTEXT+e.getMessage());
			}
	}    
	/**
	 * 
	 * @param fileURL
	 * @throws IOException
	 */
	//Download the Jplag Jar and store at plag-2.11.9-SNAPSHOT-jar-with-dependencies.jar
	//Jar should be downloaded the same place where git repositories are downloaded 
	public static void downloadJar(String fileURL) throws IOException{
		try {
			FileUtils.copyURLToFile(new URL(fileURL), new File("jplag-2.11.9-SNAPSHOT-jar-with-dependencies.jar"));
		} catch (IOException e)
		{
			LOGGER.info("Context : "+e.getMessage());

		}

	}
}