package com.jeeffy.code.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class PropertiesUtilTest {

	@Test
	public void testGetBeanFields(){
		Map<String, String> map = PropertiesUtil.getBeanFields("user");
		System.out.println(map);
        Assert.assertNotNull(map);
	}

    @Test
    public void testGetBeans() throws Exception {
        List<String> list = PropertiesUtil.getBeans();
        System.out.println(list);
        Assert.assertNotNull(list);
    }

}
