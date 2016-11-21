package com.jeeffy.code.generator;


import java.io.File;

import com.jeeffy.code.util.PropertiesUtil;

public class DirectoryGenerator {
	public static void generateDirectory(){
		String location = PropertiesUtil.getLocation();
		String packageDir = "/" + PropertiesUtil.getPackage().replace(".", "/");
		
		String mainJavaDir = location + "/src/main/java";
		String mainResourcesDir = location + "/src/main/resources";
		String testJavaDir = location + "/src/test/java";
		
		String beanDir = mainJavaDir + packageDir +"/bean";
		String daoDir = mainJavaDir + packageDir +"/dao";
		String serviceDir = mainJavaDir + packageDir +"/service";
		String controllerDir = mainJavaDir + packageDir +"/controller";
		
		String mapperDir = mainResourcesDir + packageDir +"/mapper";
		
		String serviceTestDir = testJavaDir + packageDir+"/service";
		
		mkdir(beanDir); 
		mkdir(daoDir); 
		mkdir(serviceDir); 
		mkdir(controllerDir); 
		
		mkdir(mapperDir); 
		
		mkdir(serviceTestDir); 
		
	}

	private static void mkdir(String dir) {
		File file = new File(dir); 
		if(!file.exists()){ 
			file.mkdirs();
		}
	}
	
	public static String getPackageDirectory(String name){
		String location = PropertiesUtil.getLocation();
		String directory = null;
		String packageDir = "/" + PropertiesUtil.getPackage().replace(".", "/");
		
		String mainJavaDir = location + "/src/main/java";
		String mainResourcesDir = location + "/src/main/resources";
		String testJavaDir = location + "/src/test/java";

		if("service-test".equals(name)){
			directory = testJavaDir + packageDir +"/service/";
		}else if("mapper".equals(name)){
			directory = mainResourcesDir + packageDir +"/mapper/";
		}else{
			directory = mainJavaDir + packageDir +"/"+ name +"/";
		}
		return directory;
	}
	
}