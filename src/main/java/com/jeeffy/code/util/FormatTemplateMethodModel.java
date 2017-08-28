package com.jeeffy.code.util;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * Created by Jeeffy on 2017/8/26.
 */
public class FormatTemplateMethodModel implements TemplateMethodModel {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        String str = (String)arguments.get(0);
        String separator = "_";
        if (arguments.size()==2){
            separator = (String)arguments.get(1);
        }

        return StringUtil.format(str, separator);
    }
}
