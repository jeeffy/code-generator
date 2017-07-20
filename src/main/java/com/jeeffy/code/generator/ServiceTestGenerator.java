package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;

public class ServiceTestGenerator extends Generator {

	public void generate(String tableName) {
		generateDaoTest(tableName);
	}

	private void generateDaoTest(String tableName) {
		String beanName = getBeanName(tableName);
		String template = "service-test.tpl";
		String content = generate(template,tableName);
		
		writeContentToFile(content, FileUtil.getPackageDirectory("service-test") + beanName + "ServiceTest.java");
	}
}
