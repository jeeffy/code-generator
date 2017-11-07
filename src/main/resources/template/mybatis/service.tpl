package ${packageName}.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packageName}.dao.${classType}Dao;
import ${packageName}.bean.${classType};

@Service
public class ${classType}Service {

    @Autowired
	private ${classType}Dao ${className}Dao;
	
	public List<${classType}> list(${classType} ${className}) {
	    return ${className}Dao.list(${className});
	}
	
	public ${classType} get(${idType} ${id}) {
		return ${className}Dao.get(${id});
	}
	
	public ${classType} create(${classType} ${className}) {
		${className}Dao.create(${className});
		return ${className};
	}
	
	public ${classType} update(${classType} ${className}) {
		${className}Dao.update(${className});
		return ${className};
	}
	
	public void delete(${idType} ${id}) {
		${className}Dao.delete(${id});
	}
    
}