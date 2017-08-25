package com.jeeffy.code.util;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.jeeffy.code.bean.Model;
import freemarker.template.*;

public class FreemarkerUtil {
    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        URL templatePath = FreemarkerUtil.class.getResource("/template/");
        cfg.setDirectoryForTemplateLoading(new File(templatePath.toURI()));
        cfg.setDefaultEncoding("UTF-8");
        //cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //cfg.setLogTemplateExceptions(false);


        Model model = new Model();
        model.setName("user");
        model.setPackageName("com.jeeffy");
        model.setTable("sys_user");
        model.setClassType("User");
        model.setClassName("user");
        model.setId("id");
        model.setIdType("Integer");

        Template temp = cfg.getTemplate("jpa/dao.tpl");

        String path = "D:/test/a.java";
        Writer out = new OutputStreamWriter(new FileOutputStream(new File(path)));
        temp.process(model, out);
    }
}