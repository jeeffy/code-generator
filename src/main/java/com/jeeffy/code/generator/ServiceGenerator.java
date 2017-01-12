package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;

public class ServiceGenerator extends Generator {

	public void generate(String beanName) {
		String template = "service.tpl";
		String content = generate(template,beanName);
		
		writeContentToFile(content, FileUtil.getPackageDirectory("service") + beanName + "Service.java");
	}
}
