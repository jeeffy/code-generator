package com.jeeffy.code.generator;

public class ServiceGenerator extends AbstractGenerator{

	public static void generateService(String beanName) {
		String template = "service.tpl";
		String content = generate(template,beanName);
		
		writeContentToFile(content, DirectoryGenerator.getPackageDirectory("service") + beanName + "Service.java");
	}
}
