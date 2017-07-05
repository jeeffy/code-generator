package com.jeeffy.code.generator;

import com.jeeffy.code.util.DBUtil;
import com.jeeffy.code.util.FileUtil;
import com.jeeffy.code.util.PropertiesUtil;
import com.jeeffy.code.util.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapperGenerator extends Generator {

	public void generate(String beanName) {
		
		String tableName = StringUtil.toUnderscoreCase(beanName);
		String template = "mapper.tpl";
		List<String> columnList = getColumnNameList(beanName);
		Map<String,String> map = PropertiesUtil.getBeanId(beanName);
		String primaryKey = StringUtil.toUnderscoreCase(map.get("id"));
		String primaryKeyType = getPrimaryKeyType(map.get("idType"));
		String content = generate(template,beanName);

		content = generateResultMap(content,primaryKey, columnList);
		content = generateQueryConditionSql(content, columnList);
		content = generateSelectListSql(content, tableName);
        if(primaryKey!=null){
            content = generateInsertPk(content, primaryKey, primaryKeyType);
            content = generateInsertSql(content, tableName, columnList);
            content = generateSelectSql(content, tableName, primaryKey);
            content = generateUpdateSql(content, tableName, primaryKey,columnList);
            content = generateDeleteSql(content, tableName, primaryKey);
            content = generatePkType(content, primaryKeyType);
            
        }
        
        writeContentToFile(content, FileUtil.getPackageDirectory("mapper") + beanName + "DaoMapper.xml");
	}

    private String generateQueryConditionSql(String content, List<String> columnList) {
        StringBuilder sb = new StringBuilder();
        StringBuffer keywords = new StringBuffer();
		keywords.append("\t\t\t<if test=\"keywords != null and keywords != ''\">\n\t\t\t\tand (\n");
		for(String col : columnList){
			String field = StringUtil.format(col);
			if(!field.contains("time") && !field.contains("Time") &&
					!field.contains("Date") && !field.contains("date") && !"createBy".equals(field)&&
					!"updateBy".equals(field)&&!"version".equals(field)){
				sb.append("\t\t\t<if test=\""+field+" != null and "+field+" != ''\">\n");
				sb.append("\t\t\t\tand "+col+" = #{"+field+"}\n");
				sb.append("\t\t\t</if>\n");
				keywords.append("\t\t\t\tor "+col+" like concat('%',#{keywords},'%')\n");
			}

		}
		keywords.delete(65,68); //此处为删除第一次拼接的 or
		keywords.append("\t\t\t\t)\n\t\t\t</if>\n");
		sb.append(keywords);
        String newContent = content.replace(StringUtil.wrapper("queryConditionSql"), sb.toString());
        return newContent;
    }

    private String generateResultMap(String content,String primaryKey, List<String> columnList){
		StringBuilder sb = new StringBuilder();
		for(String col : columnList){
			String field = StringUtil.format(col);
            if(col.equalsIgnoreCase(primaryKey)){
                sb.append("\t\t<id property=\"").append(field).append("\" column=\"").append(col).append("\"/>\n");
            }else{
                sb.append("\t\t<result property=\"").append(field).append("\" column=\"").append(col).append("\"/>\n");
            }
			
		}
		String newContent = content.replace(StringUtil.wrapper("resultMap"), sb.toString());
		return newContent;
	}
	private String generateSelectListSql(String content, String tableName){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName).append("\n");
        sb.append("\t\t<include refid=\"queryCondition\" />");
		String newContent = content.replace(StringUtil.wrapper("selectListSql"), sb.toString());
		return newContent;
	}
	private String generateSelectSql(String content, String tableName, String primaryKey){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName).append(" WHERE ")
		  .append(primaryKey).append(" =#{").append(StringUtil.format(primaryKey)).append("}");
		String newContent = content.replace(StringUtil.wrapper("selectSql"), sb.toString());
		return newContent;
	}
	private String generateInsertSql(String content, String tableName, List<String> columnList){
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ").append(tableName).append("(\n");
		for(int i=0;i<columnList.size();i++){
			String col = columnList.get(i);
			if(i==(columnList.size()-1)){
				sb.append("\t\t\t").append(col).append("\n");
			}else{
				sb.append("\t\t\t").append(col).append(",\n");
			}
		}
		sb.append("\t\t)VALUES(\n");
		for(int i=0;i<columnList.size();i++){
			String col = columnList.get(i);
			String field = StringUtil.format(col);
			String dbType = DBUtil.getDatabaseType();
			if("createTime".equals(field)||"updateTime".equals(field)){
				if(DBUtil.MYSQL.equals(dbType)){
					sb.append("\t\t\tNOW(),\n");
				}else if(DBUtil.ORACLE.equals(dbType)){
					sb.append("\t\t\tSYSDATE,\n");
				}
			}else{
				sb.append("\t\t\t#{").append(field).append("},\n");
			}
		}
		sb.append("\t\t)");
		String newContent = content.replace(StringUtil.wrapper("insertSql"), sb.toString());
		newContent = StringUtil.removeLast(newContent, ",");
		return newContent;
	}
	private String generateUpdateSql(String content, String tableName, String primaryKey, List<String> columnList){
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(tableName).append(" \n\t\t<set>\n");
		for(int i=0;i<columnList.size();i++){
			String col = columnList.get(i);
			String field = StringUtil.format(col);
			if(!primaryKey.equals(col)&&!("createTime").equals(field)&&!("createBy").equals(field)){
				String dbType = DBUtil.getDatabaseType();
				if("version".equals(field)) {
					sb.append("\t\t\t\t").append(col).append(" = version + 1,\n");
				}else {
					sb.append("\t\t\t<if test=\"" + field + " != null and " + field + " != ''\">\n");
					if ("updateTime".equals(field)) {
						if (DBUtil.MYSQL.equals(dbType)) {
							sb.append("\t\t\t\t").append(col).append(" = NOW(),\n");
						} else if (DBUtil.ORACLE.equals(dbType)) {
							sb.append("\t\t\t\t").append(col).append(" = SYSDATE,\n");
						}
					} else {
						sb.append("\t\t\t\t").append(col).append(" = #{").append(field).append("},\n");
					}
					sb.append("\t\t\t</if>\n");
				}
			}
		}
		sb.append("\t\t</set>\n");
		if(columnList.contains("version")) {
			sb.append("\t\tWHERE ").append(primaryKey).append(" = #{").append(StringUtil.format(primaryKey)).append("} and version = #{version}");
		}else {
			sb.append("\t\tWHERE ").append(primaryKey).append(" = #{").append(StringUtil.format(primaryKey)).append("}");
		}
		String newContent = content.replace(StringUtil.wrapper("updateSql"), sb.toString());
		newContent = StringUtil.removeLast(newContent, ",");
		return newContent;
	}
	private String generateDeleteSql(String content, String tableName, String primaryKey){
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ").append(tableName).append(" WHERE ")
		  .append(primaryKey).append(" = #{").append(StringUtil.format(primaryKey)).append("}");
		String newContent = content.replace(StringUtil.wrapper("deleteSql"), sb.toString());
		return newContent;
	}
	private String generateInsertPk(String content, String primaryKey, String primaryKeyType){
		StringBuilder sb = new StringBuilder();
		String dbType = DBUtil.getDatabaseType();
		if(DBUtil.MYSQL.equals(dbType)){
			sb.append("<selectKey resultType=\"").append(primaryKeyType).append("\"  order=\"AFTER\" keyProperty=\"").append(StringUtil.format(primaryKey)).append("\" >\n");
			sb.append("\t\t\tSELECT LAST_INSERT_ID()\n");
			sb.append("\t\t</selectKey>");
		}else if(DBUtil.ORACLE.equals(dbType)){
			sb.append("<selectKey resultType=\"").append(primaryKeyType).append("\"  order=\"BEFORE\" keyProperty=\"").append(StringUtil.format(primaryKey)).append("\" >\n");
            sb.append("\t\t\tSELECT ").append(PropertiesUtil.getModule().toUpperCase()).append("_SEQUENCE.NEXTVAL FROM DUAL\n");
			sb.append("\t\t</selectKey>");
		}
		String newContent = content.replace(StringUtil.wrapper("insertPk"), sb.toString());
		return newContent;
	}
	
	private List<String> getColumnNameList(String beanName){
		Map<String, String> fieldsMap = PropertiesUtil.getBeanFields(beanName);
		List<String> list = fieldsMap.keySet().stream().map(StringUtil::toUnderscoreCase).collect(Collectors.toList());
        return list;
	}
	
	private String getPrimaryKeyType(String idType){
		if("Integer".equals(idType)){
			return "int";
		}else{
			return idType.toLowerCase();
		}
	}
	
	private String generatePkType(String content, String primaryKeyType){
		String newContent = content.replace(StringUtil.wrapper("pkType"), primaryKeyType);
		return newContent;
	}
}