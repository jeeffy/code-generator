package ${packageName}.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${packageName}.dao.${ClassName}Dao;
import ${packageName}.bean.${ClassName};

@Service
public class ${ClassName}Service {
    @Autowired
	private ${ClassName}Dao ${className}Dao;
	
	public List<${ClassName}> findAll(){
	    return ${className}Dao.findAll();
	}
	
	public ${ClassName} findOne(${idType} ${id}){
		return ${className}Dao.findOne(${id});
	}

	@Transactional
	public ${ClassName} save(${ClassName} ${className}){
		return ${className}Dao.save(${className});
	}
	
	@Transactional
	public int delete(${idType} ${id}){
		${className}Dao.delete(${id});
		return 1;
	}
   
}
