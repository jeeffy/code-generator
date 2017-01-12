package com.jeeffy.code;

import com.jeeffy.code.generator.*;
import com.jeeffy.code.util.FileUtil;
import com.jeeffy.code.util.PropertiesUtil;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		FileUtil.mkdirs();
        GeneratorGroup.add(new BeanGenerator());
        GeneratorGroup.add(new DaoGenerator());
        GeneratorGroup.add(new ServiceGenerator());
        GeneratorGroup.add(new ControllerGenerator());
        GeneratorGroup.add(new ServiceTestGenerator());
        GeneratorGroup.add(new MapperGenerator());

        List<String> beans = PropertiesUtil.getBeans();
        for(String beanName : beans){
            if(PropertiesUtil.getBeanId(beanName).size() != 0){
                GeneratorGroup.run(beanName);
                System.out.println(beanName + " has been generated.");
            }else {
                System.err.println(beanName + " has not id, ignore.");
            }
        }


	}
}
