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
		List<${classType}> list = ${className}Dao.selectList(null);
		return list;
	}
	
	public ${classType} get(${idType} ${id}) {
		return ${className}Dao.selectById(${id});
	}
	
	public ${classType} save(${classType} ${className}) {
		${className}Dao.insert(${className});
		return ${className};
	}
	
	public ${classType} update(${classType} ${className}) {
		${className}Dao.updateById(${className});
		return ${className};
	}
	
	public void delete(${idType} ${id}) {
		${className}Dao.deleteById(${id});
	}
    
}