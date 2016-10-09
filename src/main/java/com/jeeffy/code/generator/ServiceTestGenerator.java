package com.jeeffy.code.generator;

public class ServiceTestGenerator extends AbstractGenerator{

	public static void generateJunit(String beanName) {
		generateDaoTest(beanName);
	}
	private static void generateDaoTest(String beanName) {
		String template = "service-test.tpl";
		String content = generate(template,beanName);
		
		writeContentToFile(content, DirectoryGenerator.getPackageDirectory("service-test") + beanName + "ServiceTest.java");
	}
}
