package com.jeeffy.code.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.util.Date;
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

	public static String getConfig(String item){
		String config = prop.getProperty(item);
		if(config!=null){
			config = config.trim();
		}
		return config;
	}

	public static String getPackage(){
		return getConfig("package");
	}
	
	public static String getLocation(){
		return getConfig("location");
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

		if (fields==null && !"".equals(fields)){
			map = getBeanFieldsByJson(map,true);
		}else{
			String[] fieldArr = fields.split(",");
			for(String field : fieldArr){
				if(field.contains(":")){
					map.put(field.substring(0,field.indexOf(":")).trim(), field.substring(field.indexOf(":")+1).trim());
				}else{
					map.put(field.trim(), "String");
				}
			}
		}

		return map;
	}

	private static Map<String, String> getBeanFieldsByJson(Map<String, String> map, boolean format) {
		String path = getConfig("bean.json.path");
		JSONObject json = JSON.parseObject(FileUtil.read(path));
		for (Map.Entry<String, Object> entry : json.entrySet()){

			Object value = entry.getValue();
			String valueType = null;
			if (value instanceof String){
				valueType = "String";
			}else if(value instanceof Integer){
				valueType = "Integer";
			}else if(value instanceof Double){
				valueType = "Double";
			}else if (value instanceof Date){
				valueType = "Date";
			}else {
				valueType = "String";
			}
			if (format){
				map.put(StringUtil.format(entry.getKey()), valueType);
			}else{
				map.put(entry.getKey(), valueType);
			}

		}
		return map;
	}

	public static String getBeanName(){
		String bean = prop.getProperty("bean.name");
		if(bean!=null && !"".equals(bean)){
			bean = bean.trim();
		}
		bean = bean.substring(0, 1).toUpperCase()+bean.substring(1);
		return bean;
	}
	
	public static String getDbType(){
		String type = prop.getProperty("db.type");
		if(type!=null && !"".equals(type.trim())){
			type = type.trim().toLowerCase();
		}{
			type = DBUtil.MYSQL;
		}
		return type;
	}
	
	public static String getModule(){
        String str = prop.getProperty("package");
        if(str!=null){
            str = str.trim().substring(str.lastIndexOf(".")+1);
        }
        return str;
    }
}
