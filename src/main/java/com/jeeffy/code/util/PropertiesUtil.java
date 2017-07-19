package com.jeeffy.code.util;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
	
	public static String getLocation(){
		String location = prop.getProperty("location");
		if(location!=null){
			location = location.trim();
		}
		return location;
	}



	@Deprecated
    public static List<String> getBeans() throws Exception {
        List<String> beans = null;
        String tables = prop.getProperty("tables");
        if(tables != null && "__all__".equals(tables.trim().toLowerCase())){
            beans = DBUtil.getAllTables().stream().map(StringUtil::toCapitalizeCamelCase).collect(Collectors.toList());
        }else if (tables != null && !"".equals(tables.trim())) {
            tables = tables.trim();
            beans = Arrays.stream(tables.split(","))
                    .map(String::trim).map(StringUtil::toCapitalizeCamelCase).collect(Collectors.toList());
        }
        return beans;
    }

	@Deprecated
	public static Map<String, String> getBeanId(String beanName){
	    String tableName = StringUtil.toUnderscoreCase(beanName);
        return DBUtil.getPrimaryKey(tableName);
	}
	
	/**
	 * @return map key is field name, value is field type
	 */
	@Deprecated
	public static Map<String, String> getBeanFields(String beanName){
        String tableName = StringUtil.toUnderscoreCase(beanName);
        return DBUtil.getFormattedColumnNameTypeMap(tableName);
	}


	public static String getModuleName(){
        String str = prop.getProperty("package");
        if(str!=null){
            str = str.trim().substring(str.lastIndexOf(".")+1);
        }
        return str;
    }


	public static List<String> getTables() throws Exception {
		List<String> tables = null;
		String tableStr = prop.getProperty("tables");
		if(tables != null && "__all__".equals(tableStr.trim().toLowerCase())){
			tables = DBUtil.getAllTables();
		}else if (tables != null && !"".equals(tableStr.trim())) {
			tables = Arrays.stream(tableStr.trim().split(","))
					.map(String::trim).collect(Collectors.toList());
		}
		return tables;
	}
}
