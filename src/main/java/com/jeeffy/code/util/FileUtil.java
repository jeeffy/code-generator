package com.jeeffy.code.util;


import com.jeeffy.code.bean.Model;
import com.jeeffy.code.constant.Const;

import java.io.File;

public class FileUtil {
	public static void mkdirs(String[] types){
		String location = PropertiesUtil.getLocation();
		String packageDir = "/" + PropertiesUtil.getPackage().replace(".", "/");
		
		String mainJavaDir = location + "/src/main/java";
		String mainResourcesDir = location + "/src/main/resources";
		String testJavaDir = location + "/src/test/java";

		for (String type : types){
			String dir;
			if (Const.TYPE_MAPPER.equals(type)){
				dir = mainResourcesDir +"/mapper/" + PropertiesUtil.getModule();
			}else if(Const.TYPE_CONTROLLER_TEST.equals(type)){
				dir = testJavaDir + packageDir +"/" + Const.TYPE_CONTROLLER;
			}else if(Const.TYPE_SERVICE_TEST.equals(type)){
				dir = testJavaDir + packageDir +"/" + Const.TYPE_SERVICE;
			}else{
				dir = mainJavaDir + packageDir +"/" + type;
			}
			mkdir(dir);
		}

	}

	public static void mkdir(String dir) {
		File file = new File(dir); 
		if(!file.exists()){ 
			file.mkdirs();
		}
	}

	public static String getOutPath(Model model, String type){
		String outPath = null;
		if (Const.TYPE_BEAN.equals(type)){
			outPath = getDirectory(type) + model.getClassType() + ".java";
		}else if(Const.TYPE_MAPPER.equals(type)){
			outPath = getDirectory(type) + model.getClassType() + StringUtil.firstUpperCase(type) + ".xml";
		}else {
			outPath = getDirectory(type) + model.getClassType() + StringUtil.firstUpperCase(type) + ".java";
		}
		return outPath;
	}

	public static String getDirectory(String type){
		String location = PropertiesUtil.getLocation();
		String directory;
		String packageDir = "/" + PropertiesUtil.getPackage().replace(".", "/");
		
		String mainJavaDir = location + "/src/main/java";
		String mainResourcesDir = location + "/src/main/resources";
		String testJavaDir = location + "/src/test/java";

		if(Const.TYPE_CONTROLLER_TEST.equals(type)){
			directory = testJavaDir + packageDir + "/" + Const.TYPE_CONTROLLER + "/";
		}else if(Const.TYPE_SERVICE_TEST.equals(type)){
			directory = testJavaDir + packageDir + "/" + Const.TYPE_SERVICE + "/";
		}else if(Const.TYPE_MAPPER.equals(type)){
			directory = mainResourcesDir +"/mapper/" + PropertiesUtil.getModule() + "/";
		}else{
			directory = mainJavaDir + packageDir + "/" + type + "/";
		}
		return directory;
	}
	
}