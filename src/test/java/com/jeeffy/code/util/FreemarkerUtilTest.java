package com.jeeffy.code.util;

import com.jeeffy.code.bean.Model;
import org.junit.Test;

/**
 * Created by Jeeffy on 2017/8/26.
 */
public class FreemarkerUtilTest {

    @Test
    public void test(){
        Model model = new Model();
        model.setPackageName("com.jeeffy");
        model.setClassType("User");
        model.setClassName("user");
        model.setTableName("sys_user");
        model.setId("id");
        model.setIdType("Integer");

        String template = "jpa/dao.tpl";
        FreemarkerUtil.process(model, template);
    }
}
