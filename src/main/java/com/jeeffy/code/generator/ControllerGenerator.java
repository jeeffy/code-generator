package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;

public class ControllerGenerator extends Generator {

	public void generate(String tableName) {
		String beanName = getBeanName(tableName);
		String template = "controller.tpl";
		String content = generate(template,tableName);
		writeContentToFile(content, FileUtil.getPackageDirectory("controller") + beanName + "Controller.java");
	}
}
