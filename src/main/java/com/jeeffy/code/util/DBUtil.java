package com.jeeffy.code.util;

public class DBUtil {

	public static final String ORACLE = "oracle";
	public static final String MYSQL = "mysql";
	
	public static String getDatabaseType(){
		return PropertiesUtil.getDbType();
	}
}
