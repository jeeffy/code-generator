package com.jeeffy.code.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeeffy on 1/12/17.
 */
public class GeneratorGroup {
    private static List<Generator> list = new ArrayList<>();

    public static void add(Generator generator){
            list.add(generator);
    }

    public static void run(String beanName){
        for (Generator generator : list){
            generator.generate(beanName);
        }
    }
}
