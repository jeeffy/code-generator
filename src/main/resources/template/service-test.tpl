package ${packageName}.service;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ${packageName}.BaseTest;
import ${packageName}.service.${ClassName}Service;
import ${packageName}.bean.${ClassName};


public class ${ClassName}ServiceTest extends BaseTest {
    
	private static ${idType} id = null;
	
	@Autowired
	${ClassName}Service ${className}Service;
	
	@Test
	public void testGet() {
	    ${ClassName} ${className} = new ${ClassName}();
    	List<${ClassName}> list = ${className}Service.get(${className});
		assertNotNull(list);
	}

	@Test
    public void testGetById() {
        ${ClassName} ${className} = ${className}Service.getById(id);
        assertNotNull(${className});
    }

    @Test
	public void testCreate() {
    	${ClassName} ${className} = new ${ClassName}();
		${className}Service.create(${className});
	}

    @Test
    public void testUpdate() {
        ${ClassName} ${className} = new ${ClassName}();
        ${className}Service.update(${className});
    }

    @Test
	public void testDelete() {
		${className}Service.delete(id);
	}
    
}