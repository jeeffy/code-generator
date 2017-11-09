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

	public static String getPackage(){
		String packageName = prop.getProperty("package");
		if(packageName!=null){
			packageName = packageName.trim();
		}
		return packageName;
	}

	public static String getModule(){
		String packageName = getPackage();
		return packageName.substring(packageName.lastIndexOf('.')+1);
	}

	public static String getLocation(){
		String location = prop.getProperty("location");
		if(location!=null){
			location = location.trim();
		}
		return location;
	}

	public static List<String> getLayers() throws Exception {
		List<String> layers = null;
		String layerStr = prop.getProperty("layers");
		if(layerStr != null && !"".equals(layerStr)){
			layerStr = layerStr.trim();
		}else{
			layerStr = "controller, service, dao, mapper, bean, controllerTest, serviceTest";
		}

		if (layerStr != null && !"".equals(layerStr.trim())) {
			layers = Arrays.stream(layerStr.trim().split(","))
					.map(String::trim).collect(Collectors.toList());
		}
		return layers;
	}

	public static List<String> getTables() throws Exception {
		List<String> tables = null;
		String tableStr = prop.getProperty("tables");
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
		String str = prop.getProperty("removable-table-prefix");
		if(str!=null){
			str = str.trim();
		}
		return str;
	}

	public static String getDao(){
		String str = prop.getProperty("dao");
		if (str==null || "".equals(str)){
			str = "jpa";
		}else{
			str = str.trim().toLowerCase();
		}
		return str;
	}

}
