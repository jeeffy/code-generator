package com.jeeffy.code.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class PropertiesUtilTest {

	@Test
	public void testGetBeanFields(){
		Map<String, String> map = PropertiesUtil.getBeanFields();
		System.out.println(map);
        Assert.assertNotNull(map);
	}

}
