package com.jeeffy.code.util;

import com.jeeffy.code.bean.Model;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;

public class FreemarkerUtil {
    private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

    public static void process(Model model, String type) {
        try {
            URL templatePath = FreemarkerUtil.class.getResource("/template/");
            cfg.setDirectoryForTemplateLoading(new File(templatePath.toURI()));
            cfg.setDefaultEncoding("UTF-8");

            String template = model.getDao() + "/" + type + ".tpl";
            Template temp = cfg.getTemplate(template);

            String outPath = FileUtil.getOutPath(model, type);
            Writer out = new OutputStreamWriter(new FileOutputStream(new File(outPath)));
            temp.process(model, out);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}