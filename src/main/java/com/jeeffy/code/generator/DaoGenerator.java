package com.jeeffy.code.generator;

import com.jeeffy.code.util.FileUtil;

public class DaoGenerator extends Generator {

	public void generate(String tableName) {
		String beanName = getBeanName(tableName);
		String template = "dao.tpl";
		String content = generate(template,tableName);
		
		writeContentToFile(content, FileUtil.getPackageDirectory("dao") + beanName + "Dao.java");
	}
}
