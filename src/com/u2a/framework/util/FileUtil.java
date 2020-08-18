package com.u2a.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.brick.data.DataMap;
import com.brick.data.IMap;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;


/**
 * 对于文件操作的工具类
 */


public class FileUtil{

  
/*
 * 产生文件夹路径，配置路径/年/月/日
 * 
 * 格式如：配置路径/2009/5/15/
 * 
 */
  

  public static String createDatePath(){
	
//	   while(config.endsWith("/")||config.endsWith("\\")){
//		config=config.substring(0, config.length()-1);
//	   }
	   
	 return DateTimeUtil.getLocalYear()+"/"+ DateTimeUtil.getLocalMonth()+"/"+DateTimeUtil.getLocalDay();

  } 
  
    /*
	 * 产生文件名:yyyyMMddHHmmss,并在后面加n位随机数
	 * 
	 * 格式如：200905011021155489.jpg
	 */
   
    public static String createFileName(int n) {
		String date = DateTimeUtil.getLocalDateStr("yyyyMMddHHmmss");
		// 取4位随机数
		String b = String.valueOf(Math.random());
		String c = b.substring(2,n+2);
		return date + c;
	}
    /*
	 * 取文件扩展名
	 * 格式如：jpg
	 */
    public static String getSmallName(String fileName) {
		String tempName = "";
		if (fileName != null) {
			tempName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		}
		return tempName;  
	}
	/**
	 * 删除图片
	 */
	  public static boolean delete(String path){
			 
		java.io.File file=new java.io.File(path);
		if(file.exists()){
			return file.delete();
		}
		return false;
	   } 
		/**
		 * 判断文件路径是否存在，不存在则创建
		 * @Description 
		 * @param @param in
		 * @param @return    
		 * @return IMap<String,Object>   
		 * @author duch
		 * @date Mar 10, 2012 4:00:05 AM
		 */
		public static void getFilePath(String url) {
			java.io.File file = new java.io.File(url);
			if (!file.exists()) {
				file.mkdir();
			}
		}
		 

		public static void saveFile(String filePath, String context)
				throws IOException {
			String fileName = filePath;
			String[] parts = fileName.split("/");
			int partslen = parts.length, i;
			StringBuffer dirName = new StringBuffer();
			for (i = 0; i < partslen - 1; i++) {
				dirName.append(parts[i] + "/");
			}
			File dir = new File(dirName.toString());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file, false);
			if (context != null) {
				out.write(context.getBytes("utf-8"));
			}
			out.flush();
			out.close();
		}
		public static boolean createFolder(String path) {
			File file = new java.io.File(path);
			if (!file.exists()) {
				file.mkdir();
			}
			return true;
		}

		

		public static String getExtensionName(String filename) {
			if ((filename != null) && (filename.length() > 0)) {
				int dot = filename.lastIndexOf('.');
				if ((dot > -1) && (dot < (filename.length() - 1))) {
					return filename.substring(dot + 1);
				}
			}
			return filename;
		}
}
