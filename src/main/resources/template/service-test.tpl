package ${packageName}.service;

import java.util.List;

import org.junit.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ${packageName}.service.${ClassName}Service;
import ${packageName}.bean.${ClassName};


public class ${ClassName}ServiceTest extends BaseServiceTest{
    
	private static ${idType} id = null;
	
	@Autowired
	${ClassName}Service ${className}Service;
	
	@Test
	public void testGetList(){
    	List<${ClassName}> list = ${className}Service.findAll();
		Assert.assertNotNull(list);
	}
	
    @Test
	public void testSave(){
    	${ClassName} ${className} = new ${ClassName}();
		${className}Service.save(${className});
	}
    
    @Test
    public void testFindOne(){
    	${ClassName} ${className} = ${className}Service.findOne(id);
    	Assert.assertNotNull(${className});
    }

    @Test
	public void testDelete(){
		${className}Service.delete(id);
	}
    
}