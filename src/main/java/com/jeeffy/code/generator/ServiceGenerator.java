package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;

public class ServiceGenerator extends Generator {

	public void generate(String tableName) {
		String beanName = getBeanName(tableName);
		String template = "service.tpl";
		String content = generate(template,tableName);
		
		writeContentToFile(content, FileUtil.getPackageDirectory("service") + beanName + "Service.java");
	}
}
