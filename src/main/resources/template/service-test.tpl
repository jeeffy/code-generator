package ${packageName}.service;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ${packageName}.BaseTest;
import ${packageName}.service.${ClassName}Service;
import ${packageName}.bean.${ClassName};


public class ${ClassName}ServiceTest extends BaseTest{
    
	private static ${idType} id = null;
	
	@Autowired
	${ClassName}Service ${className}Service;
	
	@Test
	public void testGetByMap(){
    	List<${ClassName}> list = ${className}Service.getByMap(null);
		assertNotNull(list);
	}
	
    @Test
	public void testCreate(){
    	${ClassName} ${className} = new ${ClassName}();
		${className}Service.create(${className});
	}
    
    @Test
    public void testGetById(){
    	${ClassName} ${className} = ${className}Service.getById(id);
    	assertNotNull(${className});
    }

    @Test
	public void testDelete(){
		${className}Service.delete(id);
	}
    
}