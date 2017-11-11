package ${packageName}.dao;

import java.util.List;
import java.util.Map;

import ${packageName}.bean.${classType};

public interface ${classType}Dao {

	List<${classType}> list(${classType} ${className});
	${classType} get(${idType} ${id});
	${idType} save(${classType} ${className});
	int update(${classType} ${className});
	int delete(${idType} ${id});
}