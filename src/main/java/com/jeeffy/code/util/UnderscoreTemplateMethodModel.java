package com.jeeffy.code.util;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * Created by Jeeffy on 2017/8/26.
 */
public class UnderscoreTemplateMethodModel implements TemplateMethodModel {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        String str = (String)arguments.get(0);
        return StringUtil.toUnderscoreCase(str);
    }
}
