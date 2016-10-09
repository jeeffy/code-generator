package com.jeeffy.code.generator;

public class ControllerGenerator extends AbstractGenerator{

	public static void generateController(String beanName) {
		String template = "controller.tpl";
		String content = generate(template,beanName);
		writeContentToFile(content, DirectoryGenerator.getPackageDirectory("controller") + beanName + "Controller.java");
	}
}
