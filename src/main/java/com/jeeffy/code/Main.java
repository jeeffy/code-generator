package com.jeeffy.code;

import com.jeeffy.code.generator.*;
import com.jeeffy.code.util.PropertiesUtil;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		DirectoryGenerator.generateDirectory();
        List<String> beans = PropertiesUtil.getBeans();
        for(String beanName : beans){
            if(PropertiesUtil.getBeanId(beanName).size() != 0){
                BeanGenerator.generateBean(beanName);
                DaoGenerator.generateDao(beanName);
                ServiceGenerator.generateService(beanName);
                ControllerGenerator.generateController(beanName);
                ServiceTestGenerator.generateJunit(beanName);
                MapperGenerator.generateMapper(beanName);

                System.out.println(beanName + " has been generated.");
            }else {
                System.err.println(beanName + " has not id, ignore.");
            }
        }


	}
}
