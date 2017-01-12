package com.jeeffy.code.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class DBUtilTest {

	@Test
	public void testGetPrimaryKey(){
		Map<String, String> map = DBUtil.getPrimaryKey("role_resource");
		System.out.println(map);
        Assert.assertNotNull(map);
	}

    @Test
    public void testGetColumnNameTypeMap(){
        Map<String, String> map = DBUtil.getFormattedColumnNameTypeMap("user");
        System.out.println(map);
        Assert.assertNotNull(map);
    }

    @Test
    public void testGetTables() throws Exception {
        List<String> list = DBUtil.getAllTables();
        System.out.println(list);
    }

}
