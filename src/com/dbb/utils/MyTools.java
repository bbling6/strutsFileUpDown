package com.dbb.utils;

import java.util.UUID;

public class MyTools {
	
	//���������µ��ļ�������ֹ�ļ�������
	public static String getNewFileName(String filename){
		
		int lastindex=filename.lastIndexOf(".");
		
		//uuid����16���Ƶ��������
		String newFileName=UUID.randomUUID().toString()
		+filename.substring(lastindex, filename.length());
		
		return newFileName;
	}

}
