package com.jeeffy.code.generator;

import com.jeeffy.code.util.PropertiesUtil;
import com.jeeffy.code.util.StringUtil;

import java.io.*;
import java.util.Map;

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

    protected abstract void generate(String beanName);

    protected String generate(String template, String beanName) {
		String content = readTemplateContent(template);

		Map<String,String> idMap = PropertiesUtil.getBeanId(beanName);
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
}
