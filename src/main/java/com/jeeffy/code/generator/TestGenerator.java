package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;

public class TestGenerator extends Generator {

	public void generate(String tableName) {
		generateDaoTest(tableName);
	}

	private void generateDaoTest(String tableName) {
		String beanName = getBeanName(tableName);
		String template = "controller-test.tpl";
		String content = generate(template,tableName);
		
		writeContentToFile(content, FileUtil.getPackageDirectory("controller-test") + beanName + "ControllerTest.java");
	}
}
