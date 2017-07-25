package ${packageName}.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${packageName}.dao.${ClassName}Dao;
import ${packageName}.bean.${ClassName};

@Service
public class ${ClassName}Service {
    @Autowired
	private ${ClassName}Dao ${className}Dao;
	
	public List<${ClassName}> list(${ClassName} ${className}) {
	    Example<${ClassName}> example = Example.of(${className});
        return ${className}Dao.findAll(example);
	}
	
	public ${ClassName} get(${idType} ${id}) {
		return ${className}Dao.findOne(${id});
	}

	@Transactional
	public ${ClassName} save(${ClassName} ${className}) {
		${className}Dao.save(${className});
		return ${className};
	}

	@Transactional
	public int delete(${idType} ${id}) {
		${className}Dao.delete(${id});
		return 1;
	}
    
}