package com.jeeffy.code.util;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class PropertiesUtilTest {

	@Test
	public void testGetBeanFields(){
		Map<String, String> map = PropertiesUtil.getBeanFields();
		System.out.println(map);
        Assert.assertNotNull(map);
	}
}
