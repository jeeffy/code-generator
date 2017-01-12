package com.jeeffy.code;

import com.jeeffy.code.generator.*;
import com.jeeffy.code.util.FileUtil;
import com.jeeffy.code.util.PropertiesUtil;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		FileUtil.mkdirs();
        List<String> beans = PropertiesUtil.getBeans();
        for(String beanName : beans){
            if(PropertiesUtil.getBeanId(beanName).size() != 0){
                new BeanGenerator().generate(beanName);
                new DaoGenerator().generate(beanName);
                new ServiceGenerator().generate(beanName);
                new ControllerGenerator().generate(beanName);
                new ServiceTestGenerator().generate(beanName);
                new MapperGenerator().generate(beanName);

                System.out.println(beanName + " has been generated.");
            }else {
                System.err.println(beanName + " has not id, ignore.");
            }
        }


	}
}
