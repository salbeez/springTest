package com.guest.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.guest.controller.test.UploadController;

public class UploadFileUtiles {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtiles.class);

	public static String uploadFile(String uploadPath, String originalName,byte[] fileData) throws Exception {
		
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString()+"_"+originalName;
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath+savedPath,savedName);
		FileCopyUtils.copy(fileData, target);
		
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		String uploadedFileName = null;
		
		if(MediaUtils.getMediaType(formatName) !=null){
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}else{
			uploadedFileName = makeIcon(uploadPath,savedPath,savedName);			
		}
		return "";
	}
	
	private static String makeIcon(String uploadPath, String path, String fileName) {
		String iconName = uploadPath+path+File.separator+fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar,'/');
	}

	public static String calcPath(String uploadPath){
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);// File.separator=/ 또는 \\
		String monthPath = yearPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath,yearPath,monthPath,datePath);
		logger.info("calcPath : "+datePath);
		return datePath;
	}

	private static void makeDir(String uploadPath, String ... paths) {
		if(new File(paths[paths.length-1]).exists()){
			return;
		}
		
		for(String path : paths){
			File dirPath  = new File(uploadPath+path);
			if(!dirPath.exists()){
				dirPath.mkdir();//폴더 만들기
			}
		}
	}
	//썸네일
	public static String makeThumbnail(String uploadPath,String path, String fileName)throws Exception{
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath+path,fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumbnailName = uploadPath+path+File.separator+"s_"+fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar,'/');
	}
	
}
