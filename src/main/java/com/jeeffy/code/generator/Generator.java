package com.jeeffy.code.generator;

import com.jeeffy.code.util.DBUtil;
import com.jeeffy.code.util.PropertiesUtil;
import com.jeeffy.code.util.StringUtil;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Generator {



    /**
     * basic template
     */
    interface TPL{
        String ClassName = StringUtil.wrapper("ClassName");
        String className = StringUtil.wrapper("className");
        String packageName = StringUtil.wrapper("packageName");
        String idType = StringUtil.wrapper("idType");
        String id = StringUtil.wrapper("id");
    }

    protected abstract void generate(String tableName);

    protected String generate(String template, String tableName) {
        String content = null;
        if (PropertiesUtil.isJpa()){
            content = readTemplateContent("jpa/"+template);
        }else{
            content = readTemplateContent(template);
        }


		Map<String,String> idMap = getBeanId(tableName);

		String beanName = getBeanName(tableName);
		String replacedContent = null;
		String ClassName = beanName;
		String className = StringUtil.firstLowerCase(beanName);
		String packageName = PropertiesUtil.getPackage();
		String idType = idMap.get("idType");
		String id = idMap.get("id");
        replacedContent = content.replace(TPL.ClassName, ClassName)
				.replace(TPL.className, className)
				.replace(TPL.packageName, packageName)
				.replace(TPL.idType, idType)
				.replace(TPL.id, id);

		return replacedContent;
	}
    
    protected boolean writeContentToFile(String content, String path) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)))){
			writer.write(content);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

    private String readTemplateContent(String template){
        InputStream stream = Generator.class.getResourceAsStream("/template/"+template);
        StringBuilder content = new StringBuilder();
        String line = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i != 0) {
                    content.append('\n');
                }
                content.append(line);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static Map<String, String> getBeanId(String tableName){
        return DBUtil.getIdMap(tableName);
    }

    /**
     * @return map key is field name, value is field type
     */
    public static Map<String, String> getFieldsMap(String tableName){
        Map<String,String> fieldMap = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : getColumnsMap(tableName).entrySet()){
            String key = entry.getKey();
            key = StringUtil.camelCase(key);
            fieldMap.put(key, entry.getValue());
        }
        return fieldMap;
    }


    public static String getBeanName(String tableName){
        String beanName = null;
        if(tableName!=null){
            beanName = StringUtil.removePrefix(tableName, PropertiesUtil.getRemovableTablePrefix());
            beanName = StringUtil.camelCase(beanName);
            beanName = StringUtil.firstUpperCase(beanName);
        }
        return beanName;
    }

    public static List<String> getTables() throws Exception {
        List<String> tables = null;
        String tableStr = PropertiesUtil.getTables();
        if(tableStr != null && "__all__".equals(tableStr.trim().toLowerCase())){
            tables = DBUtil.getAllTables();
        }else if (tableStr != null && !"".equals(tableStr.trim())) {
            tables = Arrays.stream(tableStr.trim().split(","))
                    .map(String::trim).collect(Collectors.toList());
        }
        return tables;
    }

    public static Map<String, String> getColumnsMap(String tableName){
        return DBUtil.getColumnNameAndJavaTypeMap(tableName);
    }
}
