package ${packageName}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${packageName}.dao.${classType}Dao;
import ${packageName}.bean.${classType};

@Service
public class ${classType}Service {
    @Autowired
	private ${classType}Dao ${className}Dao;

	public Page<${classType}> list(${classType} ${className}, Pageable page) {
		Example<${classType}> example = Example.of(${className});
		return ${className}Dao.findAll(example, page);
	}

	public ${classType} get(${idType} ${id}) {
		return ${className}Dao.findOne(${id});
	}

	@Transactional
	public ${classType} save(${classType} ${className}) {
		return ${className}Dao.save(${className});
	}

	@Transactional
	public void delete(${idType} ${id}) {
		${className}Dao.delete(${id});
	}
    
}