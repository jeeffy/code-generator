package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;

public class ControllerGenerator extends Generator {

	public void generate(String beanName) {
		String template = "controller.tpl";
		String content = generate(template,beanName);
		writeContentToFile(content, FileUtil.getPackageDirectory("controller") + beanName + "Controller.java");
	}
}
