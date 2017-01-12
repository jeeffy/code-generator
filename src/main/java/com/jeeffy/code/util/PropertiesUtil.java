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
		String aPackage = prop.getProperty("package");
		if(aPackage!=null){
			aPackage = aPackage.trim();
		}
		return aPackage;
	}
	
	public static String getLocation(){
		String location = prop.getProperty("location");
		if(location!=null){
			location = location.trim();
		}
		return location;
	}

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

	public static Map<String, String> getBeanId(String beanName){
	    String tableName = StringUtil.toUnderscoreCase(beanName);
        return DBUtil.getPrimaryKey(tableName);
	}
	
	/**
	 * @return map key is field name, value is field type
	 */
	public static Map<String, String> getBeanFields(String beanName){
        String tableName = StringUtil.toUnderscoreCase(beanName);
        return DBUtil.getFormattedColumnNameTypeMap(tableName);
	}
	
	public static String getModule(){
        String str = prop.getProperty("package");
        if(str!=null){
            str = str.trim().substring(str.lastIndexOf(".")+1);
        }
        return str;
    }
}
