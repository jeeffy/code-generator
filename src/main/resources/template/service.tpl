package ${packageName}.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packageName}.dao.${ClassName}Dao;
import ${packageName}.bean.${ClassName};
import ${packageName}.service.${ClassName}Service;

@Service
public class ${ClassName}Service {
    @Autowired
	private ${ClassName}Dao ${className}Dao;
	
	public List<${ClassName}> getByMap(Map<String,Object> map){
	    return ${className}Dao.getByMap(map);
	}
	
	public ${ClassName} getById(${idType} ${id}){
		return ${className}Dao.getById(${id});
	}
	
	public ${ClassName} create(${ClassName} ${className}){
		${className}Dao.create(${className});
		return ${className};
	}
	
	public ${ClassName} update(${ClassName} ${className}){
		${className}Dao.update(${className});
		return ${className};
	}
	
	public int delete(${idType} ${id}){
		return ${className}Dao.delete(${id});
	}
    
}