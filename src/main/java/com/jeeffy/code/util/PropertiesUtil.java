package com.jeeffy.code.util;

import com.jeeffy.code.constant.Const;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


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

	public static String getProperty(String property){
		String value = prop.getProperty(property);
		if(value != null){
			value = value.trim();
		}
		return value;
	}

	public static String getPackage(){
		return getProperty("package");
	}

	public static String getModule(){
		String module = getProperty("module");
		if (module != null && !"".equals(module)){
			return module;
		}else{
			String packageName = getPackage();
			return packageName.substring(packageName.lastIndexOf('.')+1);
		}

	}

	public static String getLocation(){
		return getProperty("location");
	}

	public static List<String> getLayers() throws Exception {
		List<String> layers = null;
		String layerStr = getProperty("layers");
		if(layerStr != null && !"".equals(layerStr)){
			layerStr = layerStr.trim();
		}else{
			layerStr = "controller, service, dao, bean, serviceTest";
		}

		if (layerStr != null && !"".equals(layerStr.trim())) {
			layers = Arrays.stream(layerStr.trim().split(","))
					.map(String::trim).collect(Collectors.toList());
		}
		return layers;
	}

	public static List<String> getTables() throws Exception {
		List<String> tables = null;
		String tableStr = getProperty("tables");
		if(tableStr != null && !"".equals(tableStr)){
			tableStr = tableStr.trim();
		}
		if ("__all__".equals(tableStr)){
			return DBUtil.getAllTables();
		}
		if (tableStr != null && !"".equals(tableStr.trim())) {
			tables = Arrays.stream(tableStr.trim().split(","))
					.map(String::trim).collect(Collectors.toList());
		}
		return tables;
	}

	public static String getRemovableTablePrefix(){
		return getProperty("removable-table-prefix");
	}

	public static String getDao(){
		String value = getProperty("dao");
		if (value==null || "".equals(value)){
			value = "mybatis";
		}else{
			value = value.trim().toLowerCase();
		}
		return value;
	}

}
