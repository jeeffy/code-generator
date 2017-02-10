package ${packageName}.dao;

import java.util.List;
import java.util.Map;

import ${packageName}.bean.${ClassName};

public interface ${ClassName}Dao {

	List<${ClassName}> getByMap(Map<String, Object> map);
	${ClassName} getById(${idType} ${id});
	${idType} create(${ClassName} ${className});
	int update(${ClassName} ${className});
	int delete(${idType} ${id});
}