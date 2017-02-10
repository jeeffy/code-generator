package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;
import com.jeeffy.code.util.PropertiesUtil;
import com.jeeffy.code.util.StringUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class BeanGenerator extends Generator {

	public void generate(String beanName) {
		Map<String, String> fieldMap = PropertiesUtil.getBeanFields(beanName);
		String ClassName = beanName;
		StringBuilder sb = new StringBuilder();
		sb.append("package "+ PropertiesUtil.getPackage()+".bean;\n\n");
		
		sb.append(generateImport(fieldMap));
		sb.append("\npublic class "+ClassName+" {\n");
		sb.append(generateFields(fieldMap));
		sb.append(generateGetAndSetMethods(fieldMap));
		sb.append(generateToString(beanName, fieldMap));
		sb.append("}");

		writeContentToFile(sb.toString(), FileUtil.getPackageDirectory("bean") + beanName + ".java");
	}
	
	private String generateImport(Map<String, String> fieldMap){
		StringBuilder sb = new StringBuilder();
		Set<String> set = new HashSet<String>();
		
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
	
	private String generateFields(Map<String, String> map) {
		Set<String> keySet = map.keySet();
		StringBuilder sb = new StringBuilder();
		
		for(String field : keySet){
			String fieldType = map.get(field);
			sb.append("\tprivate ").append(fieldType+" ").append(field+";\n");
			
		}
		sb.append("\n");
		return sb.toString();
	}
	
	private String generateGetAndSetMethods(Map<String, String> map) {
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

    private String generateToString(String beanName, Map<String, String> map) {
        Set<String> keySet = map.keySet();
        StringBuilder sb = new StringBuilder();
        sb.append("\t@Override\n");
        sb.append("\tpublic String toString() {\n");
        sb.append("\t\treturn \"" + beanName + "{\" +\n");
        boolean isFirst = true;
        for(String field : keySet){
            if(isFirst){
                sb.append("\t\t\t\""+field+"=\" + "+field+" +\n");
                isFirst = false;
            }else{
                sb.append("\t\t\t\", "+field+"=\" + "+field+" +\n");
            }
        }
        sb.append("\t\t\t'}';\n");
        sb.append("\t\t}\n");
        return sb.toString();
    }

}
