package ${packageName}.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packageName}.dao.${classType}Dao;
import ${packageName}.bean.${classType};

@Service
public class ${classType}Service {
    @Autowired
	private ${classType}Dao ${className}Dao;
	
	public List<${classType}> list(${classType} ${className}) {
	    Map map = (Map)JSON.toJSON(${className});
	    return ${className}Dao.list(map);
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