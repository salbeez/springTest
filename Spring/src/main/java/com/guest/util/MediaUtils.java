package com.guest.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	/*	
	클래스 초기화 블럭 : 클래스 변수의 복잡한 초기화에 사용된다. 클래스가 처음 로딩될 때 한번만 수행된다.
	인스턴스 초기화 블럭 : 인스턴스 변수의 복잡한 초기화에 사용된다. 인스턴스가 생성될때 마다 수행된다. (생성자보다 먼저 수행된다.)
	class InitBlock{
    	static {
			클래스 초기화 블럭 
		}
		
    	{    인스턴스 초기화 블럭 }
	} 
	*/
	
	private static Map<String,MediaType> mediaMap;
	
	static{
		mediaMap = new HashMap<>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}

	public static MediaType getMediaType(String type){
		return mediaMap.get(type.toUpperCase());
	}
}
