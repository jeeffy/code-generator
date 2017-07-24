package com.jeeffy.code.util;

import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {
	private static Properties prop;

	static {
		prop = new Properties();
		try {
			InputStream stream = PropertiesUtil.class.getResourceAsStream("/generator.properties");
			prop.load(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPackage(){
		String packageName = prop.getProperty("package");
		if(packageName!=null){
			packageName = packageName.trim();
		}
		return packageName;
	}

	public static String getLocation(){
		String location = prop.getProperty("location");
		if(location!=null){
			location = location.trim();
		}
		return location;
	}

	public static String getModuleName(){
		String str = prop.getProperty("package");
		if(str!=null){
			str = str.trim().substring(str.lastIndexOf(".")+1);
		}
		return str;
	}

	public static String getTables(){
		String str = prop.getProperty("tables");
		if(str!=null){
			str = str.trim();
		}
		return str;
	}

	public static String getRemovableTablePrefix(){
		String str = prop.getProperty("removable-table-prefix");
		if(str!=null){
			str = str.trim();
		}
		return str;
	}

	public static String getDaoLayerImplement(){
		String str = prop.getProperty("dao-layer-implement");
		if (str==null || "".equals(str)){
			str = "mybatis";
		}else{
			str = str.trim().toLowerCase();
		}
		return str;
	}

	public static boolean isJpa(){
		String str = getDaoLayerImplement();
		if ("jpa".equals(str)){
			return true;
		}
		return false;
	}
}
