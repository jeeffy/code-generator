package ${packageName}.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import ${packageName}.BaseTest;
import ${packageName}.service.${classType}Service;
import ${packageName}.bean.${classType};


public class ${classType}ServiceTest extends BaseTest {

    private static ${idType} id = null;

    @Autowired
    ${classType}Service ${className}Service;

    @Test
    public void testList() {
        ${classType} ${className} = new ${classType}();
        Page<${classType}> contracts = ${className}Service.list(${className}, 0);
        assertNotNull(contracts);
    }

    @Test
    public void testGet() {
        ${classType} ${className} = ${className}Service.get(id);
        assertNotNull(${className});
    }

    @Test
    public void testSave() {
        ${classType} ${className} = new ${classType}();
        ${className}Service.save(${className});
    }

    @Test
    public void testDelete() {
        ${className}Service.delete(id);
    }

}