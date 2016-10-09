package com.jeeffy.code.generator;

public class DaoGenerator extends AbstractGenerator{

	public static void generateDao(String beanName) {
		String template = "dao.tpl";
		String content = generate(template,beanName);
		
		writeContentToFile(content, DirectoryGenerator.getPackageDirectory("dao") + beanName + "Dao.java");
	}
}
