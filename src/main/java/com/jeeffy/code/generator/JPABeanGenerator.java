package com.jeeffy.code.generator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.jeeffy.code.util.FileUtil;
import com.jeeffy.code.util.PropertiesUtil;
import com.jeeffy.code.util.StringUtil;


public class JPABeanGenerator extends Generator{

	public void generate(String tableName) {
		String beanName = getBeanName(tableName);
		Map<String, String> fieldMap = getFieldsMap(tableName);
		Map<String,String> idMap = getBeanId(tableName);
		String id = idMap.get("id");
		String ClassName = beanName;
		StringBuilder sb = new StringBuilder();
		sb.append("package "+ PropertiesUtil.getPackage()+".bean;\n\n");
		
		sb.append(generateImport(fieldMap));
		sb.append("\n@Entity\n@Table(name = \""+tableName+"\")");
		sb.append("\npublic class "+ClassName+"{\n");
		sb.append(generateFields(id, fieldMap));
		sb.append(generateGetAndSetMethods(fieldMap));
		sb.append("}");
		writeContentToFile(sb.toString(), FileUtil.getPackageDirectory("bean") + beanName + ".java");
	}
	
	private static String generateImport(Map<String, String> fieldMap){
		StringBuilder sb = new StringBuilder();
		Set<String> set = new HashSet<String>();
		
		set.add("com.fasterxml.jackson.annotation.JsonIgnore");
		set.add("javax.persistence.*");

		Set<String> keySet = fieldMap.keySet();
		for(String key : keySet){
			String value = fieldMap.get(key);
			if("BigDecimal".equals(value)){
				set.add("java.math.BigDecimal");
			}else if("Date".equals(value)){
				set.add("java.util.Date");
			}else if("Timestamp".equals(value)){
				set.add("java.sql.Timestamp");
			}
		}
		
		for(String clazz : set){
			sb.append("import "+ clazz +";\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	private static String generateFields(String id, Map<String, String> map) {
		Set<String> keySet = map.keySet();
		StringBuilder sb = new StringBuilder();
		
		for(String field : keySet){
			String fieldType = map.get(field);
			if(id.equals(field)){
				sb.append("\t@Id\n\t@GeneratedValue\n");
			}else if("createTime".equals(field) || "updateTime".equals(field)){
				sb.append("\t@JsonIgnore\n\t@Transient\n");
			}else if("createBy".equals(field) || "updateBy".equals(field)){
				sb.append("\t@JsonIgnore\n");
			}
			sb.append("\tprivate ").append(fieldType+" ").append(field+";\n");
			
		}
		sb.append("\n");
		return sb.toString();
	}
	
	private static String generateGetAndSetMethods(Map<String, String> map) {
		Set<String> keySet = map.keySet();
		StringBuilder sb = new StringBuilder();
		for(String key : keySet){
			String field = key;
			String fieldType = map.get(key);
			//generate get method
			sb.append("\tpublic ").append(fieldType+" ").append("get"+ StringUtil.firstUpperCase(field)+"() {\n\t\t")
			  .append("return "+field+";\n\t}\n");
			//generate set method
			sb.append("\tpublic ").append("void ").append("set"+ StringUtil.firstUpperCase(field)+"("+fieldType+" "+field+") {\n\t\t")
			  .append("this."+field+" = "+field+";\n\t}\n");
		}
		return sb.toString();
	}
}
