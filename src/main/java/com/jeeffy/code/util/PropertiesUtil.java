package com.jeeffy.code.util;

import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;


public class PropertiesUtil {
	private static Properties prop;
	
	static {
		prop = new Properties();
		try {
			String path = PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			prop.load(new FileInputStream(path + "/generator.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getPackage(){
		String cgpackage = prop.getProperty("package");
		if(cgpackage!=null){
			cgpackage = cgpackage.trim();
		}
		return cgpackage;
	}
	
	public static String getLocation(){
		String location = prop.getProperty("location");
		if(location!=null){
			location = location.trim();
		}
		return location;
	}
	
	public static Map<String, String> getBeanId(){
		Map<String, String> map = new LinkedHashMap<>();
		String id = prop.getProperty("bean.id");
		if(id!=null && !"".equals(id.trim())){
			id = id.trim();
			map.put("id", id);
			map.put("idType", getBeanFields().get(id));
		}else{
			map.put("id", "id");
			map.put("idType", getBeanFields().get("id"));
		}
		
		return map;
	}
	
	/**
	 * @return map key is field name, value is field type
	 */
	public static Map<String, String> getBeanFields(){
		Map<String, String> map = new LinkedHashMap<>();
		String fields = prop.getProperty("bean.fields");
		
		//use default if bean.id is empty
		String id = prop.getProperty("bean.id");
		if(id==null || "".equals(id.trim())){
			map.put("id", "Integer");
		}
				
		String[] fieldArr = fields.split(",");
		for(String field : fieldArr){
			if(field.contains(":")){
				map.put(field.substring(0,field.indexOf(":")).trim(), field.substring(field.indexOf(":")+1).trim());
			}else{
				map.put(field, "String");
			}
		}
		return map;
	}
	
	public static String getBeanName(){
		String bean = prop.getProperty("bean.name");
		if(bean!=null){
			bean = bean.trim();
		}
		bean = bean.substring(0, 1).toUpperCase()+bean.substring(1);
		return bean;
	}
	
}
