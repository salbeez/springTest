package com.guest.controller.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.guest.util.MediaUtils;
import com.guest.util.UploadFileUtiles;
import com.kosta.myapp.HomeController;

import oracle.net.aso.h;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Resource(name = "uploadPath") // servlet_context.xml의 빈 사용할려고
	private String uploadPath;

	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public String uploadForm() {
		return "test/uploadForm";
	}

	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws IOException {

		logger.info("originalName : " + file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());

		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());

		model.addAttribute("savedName", savedName);
		return "test/uploadResult";
	}

	private String uploadFile(String originalName, byte[] fileData) throws IOException {

		UUID uid = UUID.randomUUID();// 고유값 만들고
		String savedName = uid.toString() + "_" + originalName;

		File target = new File(uploadPath, savedName);// 일단 파일을 만들고 (내용이 비어있는)
		FileCopyUtils.copy(fileData, target);// 만든 파일 안에 내용을 넣는다
		return savedName;
	}

	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public String uploadAjax() {
		return "test/uploadAjax";
	}

	@ResponseBody//jsp 연결하지 않고 그냥 쓰겠다
	@RequestMapping(value="/uploadAjax", method= RequestMethod.POST,produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		//형식은 JSON으로 
		logger.info("originalName : "+file.getOriginalFilename());
		return new ResponseEntity<>(UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),HttpStatus.CREATED); //
	}
	
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName)throws Exception{
		InputStream in =null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("FILE NAME : "+fileName);
		try {
			String formatName = fileName.substring(fileName.indexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath+fileName);
			
			if(mType != null){
				headers.setContentType(mType);
			}else{
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\""); 
			}
			
			entity = new ResponseEntity<byte[]> (IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
		
	}
}
