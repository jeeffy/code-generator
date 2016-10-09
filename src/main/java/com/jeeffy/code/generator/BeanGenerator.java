package com.jeeffy.code.generator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.jeeffy.code.util.PropertiesUtil;
import com.jeeffy.code.util.StringUtil;


public class BeanGenerator extends AbstractGenerator{

	public static void generateBean(String beanName) {
		Map<String, String> fieldMap = PropertiesUtil.getBeanFields();
		String ClassName = beanName;
		StringBuilder sb = new StringBuilder();
		sb.append("package "+ PropertiesUtil.getPackage()+".bean;\n\n");
		
		sb.append(generateImport(fieldMap));
		sb.append("\n@Entity\n@Table(name = \""+StringUtil.toUnderscoreCase(ClassName)+"\")");
		sb.append("\npublic class "+ClassName+"{\n");
		sb.append(generateFields(fieldMap));
		sb.append(generateGetAndSetMethods(fieldMap));
		sb.append("}");
		writeContentToFile(sb.toString(), DirectoryGenerator.getPackageDirectory("bean") + beanName + ".java");
	}
	
	private static String generateImport(Map<String, String> fieldMap){
		StringBuilder sb = new StringBuilder();
		Set<String> set = new HashSet<String>();
		
		set.add("javax.persistence.Entity");
		set.add("javax.persistence.GeneratedValue");
		set.add("javax.persistence.Id");
		set.add("javax.persistence.Table");
		
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
	
	private static String generateFields(Map<String, String> map) {
		Set<String> keySet = map.keySet();
		StringBuilder sb = new StringBuilder();
		
		String id = PropertiesUtil.getBeanId().get("id");
		for(String field : keySet){
			String fieldType = map.get(field);
			if(id.equals(field)){
				sb.append("\t@Id\n\t@GeneratedValue\n");
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
