package ${packageName}.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packageName}.dao.${ClassName}Dao;
import ${packageName}.bean.${ClassName};

@Service
public class ${ClassName}Service {
    @Autowired
	private ${ClassName}Dao ${className}Dao;
	
	public List<${ClassName}> list(${ClassName} ${className}) {
	    Map map = (Map)JSON.toJSON(${className});
	    return ${className}Dao.list(map);
	}
	
	public ${ClassName} get(${idType} ${id}) {
		return ${className}Dao.get(${id});
	}
	
	public ${ClassName} create(${ClassName} ${className}) {
		${className}Dao.create(${className});
		return ${className};
	}
	
	public ${ClassName} update(${ClassName} ${className}) {
		${className}Dao.update(${className});
		return ${className};
	}
	
	public int delete(${idType} ${id}) {
		return ${className}Dao.delete(${id});
	}
    
}