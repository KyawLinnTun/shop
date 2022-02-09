package com.myprojects.admin.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class ImagesUtil {
	private static final Logger logger = Logger.getLogger(ImagesUtil.class);
	public static String uploadMultipartFile(MultipartFile clientFile, String subFolder,
			String prefixFileName, Long id) throws Exception {
		String fileName = generateFileName(clientFile, prefixFileName, id);
		File dest = new File(CommonConstant.IMAGE_SAVE_DIRECTORY + subFolder, fileName);
		FileUtils.writeByteArrayToFile(dest, clientFile.getBytes());
		return subFolder + fileName;
	}

	public static String uploadBase64File(String clientFileData, String subFolder, String prefixFileName, Long id)
			throws Exception {
		if(!CommonUtil.isEmpty(clientFileData)) {
			String fileName = generateFileNameForBase64(prefixFileName, id);
			byte[] decoded = Base64.decodeBase64(clientFileData);
			File dest = new File(CommonConstant.IMAGE_SAVE_DIRECTORY + subFolder, fileName);
			FileUtils.writeByteArrayToFile(dest, decoded);
			return subFolder + fileName;
		}
		return "";
	}

	private static String generateFileName(MultipartFile multipartFile, String prefixFileName, Long id) {
		String nano_time = String.valueOf(System.nanoTime());
		String fileName = (prefixFileName != null && !prefixFileName.isEmpty()) ? prefixFileName + "_" : "";
		fileName = id + "_" + fileName + nano_time + multipartFile.getOriginalFilename().replace(" ", "_");
		return fileName;
	}

	private static String generateFileNameForBase64(String prefixFileName, Long id) {
		String nano_time = String.valueOf(System.nanoTime());
		String fileName = (prefixFileName != null && !prefixFileName.isEmpty()) ? prefixFileName + "_" : "";
		fileName = id + "_" + fileName + nano_time + CommonConstant.IMAGE_SUFFIX;
		return fileName;
	}

	public static void deleteFile(String basePath, String fileName) throws Exception {
		File file = new File(basePath + File.separator + fileName);
		FileUtils.forceDelete(file);
	}
	
	public static void deleteSubFile(String basePath, String fileName) throws Exception {
		File file = new File(basePath + File.separator + fileName);
		File[] list = file.listFiles();
	    if (list != null) {
	        for (File temp : list) {
	            //recursive delete
	        	FileUtils.forceDelete(temp);
	        }
	    }
		
	}
	
	public static void deleteImage(String subDirectory, String imageName) {
		if (!CommonUtil.validString(subDirectory) || !CommonUtil.validString(imageName)) {
			return;
		}
		File image = new File(CommonConstant.IMAGE_SAVE_DIRECTORY + subDirectory + imageName);
		if (image.exists()) {
			image.delete();
		}
	}

	public static void writeBase64ToImage(String imageString, String filePath, String fileName) {
		String saveDirectory = CommonConstant.IMAGE_SAVE_DIRECTORY + filePath;
		try {
			File dest = new File(saveDirectory);
			if (!dest.exists())
				dest.mkdirs();

			byte[] decoded = Base64.decodeBase64(imageString);

			File fileDest = new File(saveDirectory, fileName);

			FileUtils.writeByteArrayToFile(fileDest, decoded);

			// writeBase64File(imageString, fileName, saveDirectory);
		} catch (IllegalStateException e) {
			logger.error(">>> error occur while write base64 to image file =>" + fileName);
		} catch (IOException e) {
			logger.error(">>> error occur while write base64 to image file =>" + fileName);
		}
	}

}
