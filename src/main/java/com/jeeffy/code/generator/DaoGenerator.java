package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;

public class DaoGenerator extends Generator {

	public void generate(String beanName) {
		String template = "dao.tpl";
		String content = generate(template,beanName);
		
		writeContentToFile(content, FileUtil.getPackageDirectory("dao") + beanName + "Dao.java");
	}
}
