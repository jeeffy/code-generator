package ${packageName}.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ${packageName?substring(0, packageName?last_index_of("."))}.BaseTest;
import ${packageName}.bean.${classType};


public class ${classType}ServiceTest extends BaseTest {

    private static ${idType} id = null;

    @Autowired
    ${classType}Service ${className}Service;

    @Test
    public void testList() {
        ${classType} ${className} = new ${classType}();
        Pageable page = new PageRequest(0, 10);
        Page<${classType}> ${className}s = ${className}Service.list(${className}, page);
        Assert.assertNotNull(${className}s);
    }

    @Test
    public void testGet() {
        ${classType} ${className} = ${className}Service.get(id);
        Assert.assertNotNull(${className});
    }

    @Test
    public void testSave() {
        ${classType} ${className} = new ${classType}();
        <#list fields as field>
        ${className}.set${field.fieldName?cap_first}(null);
        </#list>
        ${className}Service.save(${className});
    }

    @Test
    public void testDelete() {
        ${className}Service.delete(id);
    }

}