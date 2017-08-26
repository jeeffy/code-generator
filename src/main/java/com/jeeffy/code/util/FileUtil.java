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
				dir = mainResourcesDir + packageDir +"/" + type;
			}else if(Const.TYPE_CONTROLLER_TEST.equals(type)){
				dir = testJavaDir + packageDir +"/" + Const.TYPE_CONTROLLER;
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
			outPath = FileUtil.getDirectory(type) + model.getClassType() + ".java";
		}else if(Const.TYPE_MAPPER.equals(type)){
			outPath = FileUtil.getDirectory(type) + model.getClassType() + StringUtil.firstUpperCase(type) + ".xml";
		}else {
			outPath = FileUtil.getDirectory(type) + model.getClassType() + StringUtil.firstUpperCase(type) + ".java";
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
		}else if(Const.TYPE_MAPPER.equals(type)){
			directory = mainResourcesDir + packageDir + "/" + type + "/";
		}else{
			directory = mainJavaDir + packageDir + "/" + type + "/";
		}
		return directory;
	}
	
}