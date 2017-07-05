package com.jeeffy.code.util;

import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DBUtil {
    public static final String MYSQL = "mysql";
    public static final String ORACLE = "oracle";
    private static Properties prop;
    private static final Map<String, Connection> cache = new ConcurrentHashMap<>();

    static {
        prop = new Properties();
        try {
            InputStream stream = DBUtil.class.getResourceAsStream("/jdbc.properties");
            prop.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get db type from jdbc
     * @return
     */
    public static String getDatabaseType(){
		try {
			String driverClassName = prop.getProperty("jdbc.driverClassName");
			if(driverClassName.contains("mysql")){
				return MYSQL;
			}else if(driverClassName.contains("oracle")){
				return ORACLE;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Connection getConnection() throws Exception {
		Connection connection = null;
        if(cache.get("connection")==null){
            String driverClassName = prop.getProperty("jdbc.driverClassName");
            String url = prop.getProperty("jdbc.url");
            String userName = prop.getProperty("jdbc.username");
            String password = prop.getProperty("jdbc.password");
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, userName, password);
            cache.put("connection", connection);
        }else{
            connection = cache.get("connection");
        }

		return connection;
	}

	public static List<String> getAllTables() throws Exception {
		List<String> tableList = new ArrayList<>();
        ResultSet rs = null;
		try {
			DatabaseMetaData meta= getConnection().getMetaData();

			if("mysql".equals(getDatabaseType())){
				rs = meta.getTables(null, "%","%",new String[]{"TABLE"});
			}else if("oracle".equals(getDatabaseType())){
				rs = meta.getTables(null, getDatabaseInstance(),"%",new String[]{"TABLE"});
			}
			while(rs.next()){
				tableList.add(rs.getString("TABLE_NAME"));
			}
		}finally {
            if(rs!=null && !rs.isClosed()){
                rs.close();
            }
        }
        return tableList;
	}
	
	public static Map<String,String> getFormattedColumnNameTypeMap(String tableName){
		Map<String,String> colMap = new LinkedHashMap<>();
		try(ResultSet colRet = getConnection().getMetaData().getColumns(null, "%", tableName, "%")) {
			while (colRet.next()) {
				String columnName = colRet.getString("COLUMN_NAME");
				int digits = colRet.getInt("DECIMAL_DIGITS");
				int dataType = colRet.getInt("DATA_TYPE");
				String columnType=null;
				String typeName = colRet.getString("TYPE_NAME");
				int columnSize = colRet.getInt("COLUMN_SIZE");
				if("mysql".equals(getDatabaseType())){
					columnType = getDataType(dataType,digits);
				}else if("oracle".equals(getDatabaseType())){
					columnType = getDataTypeForOracle(typeName,columnSize,digits);
				}
				colMap.put(StringUtil.format(columnName), columnType);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return colMap;
	}
	

	/**
	 * translate database type into java type
	 * @param type
	 * @return
	 */
	private static String getDataType(int type,int digits){
	        String dataType="";

	        switch(type){
		        case Types.VARCHAR:  //12
	                dataType="String";
	                break;
		        case Types.INTEGER:    //4
		        	dataType="Integer";
		        	break;
		        case Types.BIT:    //-7
		        	dataType="Integer";
		        	break;	
	            case Types.LONGVARCHAR: //-1
	                dataType="Long";
	                break;
	            case Types.BIGINT: //-1
	                dataType="Long";
	                break;    
	            case Types.DOUBLE: //8
	                dataType="Double";
	                break;    
	            case Types.DECIMAL:    //3
	                dataType="BigDecimal";
	                break;    
	            case Types.NUMERIC: //2
	                switch(digits){
	                    case 0:
	                        dataType="Integer";
	                        break;
	                    default:
	                        dataType="Double";
	                }
	                break;
	            case Types.DATE:  //91
	                dataType="Date";
	                break;
	            case Types.TIMESTAMP: //93
	                dataType="Date";
	                break;
	            default:
	                dataType="String";
	        }
	        return dataType;
	   }
	   
	private static String getDataTypeForOracle(String typeName,int columnSize,int digits){
        String dataType="";

        if("VARCHAR2".equals(typeName)){
        	dataType = "String";
        }else if("DATE".equals(typeName)){
        	dataType = "Date";
        }else if("NUMBER".equals(typeName)&& digits>0){
        	dataType = "Double";
        }else if("NUMBER".equals(typeName)&& digits==0 && columnSize<=10){
        	dataType = "Integer";
        }else if("NUMBER".equals(typeName)&& digits==0&& columnSize>10){
        	dataType = "Long";
        }else{
        	dataType = "String";
        }
        return dataType;
   }

    private static String getDatabaseInstance(){
        String dbType = getDatabaseType();
        String instance = null;
        if(ORACLE.equals(dbType)){
            try {
                String jdbcUrl = prop.getProperty("jdbc.url");
                if(jdbcUrl!=null){
                    instance = jdbcUrl.substring(jdbcUrl.lastIndexOf(":")+1).toUpperCase();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
	/**
	 * @param tableName
	 * @return primary key if table contains one
	 */
	public static Map<String,String> getPrimaryKey(String tableName){
		Map<String,String> map = new HashMap<>();
		try(ResultSet pkRSet = getConnection().getMetaData().getPrimaryKeys(null, null, tableName)) {
			while( pkRSet.next() ) {
				String primaryKey = pkRSet.getString("COLUMN_NAME");
				String primaryKeyType = getFormattedColumnNameTypeMap(pkRSet.getString("TABLE_NAME")).get(primaryKey);
                map.put("id", primaryKey);
                map.put("idType", primaryKeyType == null ? "Integer" : primaryKeyType);
			} 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return map;
	}
}
