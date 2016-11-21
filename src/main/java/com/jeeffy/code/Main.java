package com.jeeffy.code;

import com.jeeffy.code.generator.BeanGenerator;
import com.jeeffy.code.generator.ControllerGenerator;
import com.jeeffy.code.generator.DaoGenerator;
import com.jeeffy.code.generator.DirectoryGenerator;
import com.jeeffy.code.generator.MapperGenerator;
import com.jeeffy.code.generator.ServiceGenerator;
import com.jeeffy.code.generator.ServiceTestGenerator;
import com.jeeffy.code.util.PropertiesUtil;

public class Main {

	public static void main(String[] args) {
		DirectoryGenerator.generateDirectory();

		String beanName = PropertiesUtil.getBeanName();

		BeanGenerator.generateBean(beanName);
		DaoGenerator.generateDao(beanName);
		ServiceGenerator.generateService(beanName);
		ControllerGenerator.generateController(beanName);
		ServiceTestGenerator.generateJunit(beanName);
		MapperGenerator.generateMapper(beanName);

		System.out.println(beanName + " has been generated.");
	}
}
