package com.dbb.utils;

import java.util.UUID;

public class MyTools {
	
	//重新生成新的文件名，防止文件被覆盖
	public static String getNewFileName(String filename){
		
		int lastindex=filename.lastIndexOf(".");
		
		//uuid产生16进制的随机序列
		String newFileName=UUID.randomUUID().toString()
		+filename.substring(lastindex, filename.length());
		
		return newFileName;
	}

}
