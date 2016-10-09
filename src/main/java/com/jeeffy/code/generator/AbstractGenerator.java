package com.jeeffy.code.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.jeeffy.code.util.PropertiesUtil;
import com.jeeffy.code.util.StringUtil;

public abstract class AbstractGenerator {

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

    protected static String generate(String template, String beanName) {
		String content = getTemplateContent(template);

		Map<String,String> idMap = PropertiesUtil.getBeanId();
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
    
    private static String getTemplateContent(String template){
		String path = PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		File file = new File(path+"/template/"+template);
		String str = read(file);
		return str;
	}
    
    private static String read(File file) {
		StringBuffer res = new StringBuffer();
		String line = null;
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			int i = 0;
			while ((line = reader.readLine()) != null) {
				if (i != 0) {
					res.append('\n');
				}
				res.append(line);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}
    
    protected static boolean writeContentToFile(String content, String path) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)))){
			writer.write(content);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
