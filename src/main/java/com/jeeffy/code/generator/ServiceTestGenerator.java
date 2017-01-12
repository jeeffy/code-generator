package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;

public class ServiceTestGenerator extends Generator {

	public void generate(String beanName) {
		generateDaoTest(beanName);
	}

	private void generateDaoTest(String beanName) {
		String template = "service-test.tpl";
		String content = generate(template,beanName);
		
		writeContentToFile(content, FileUtil.getPackageDirectory("service-test") + beanName + "ServiceTest.java");
	}
}
