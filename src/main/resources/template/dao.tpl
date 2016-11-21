package ${packageName}.dao;

import java.util.List;
import java.util.Map;

import ${packageName}.bean.${ClassName};

public interface ${ClassName}Dao {

	public List<${ClassName}> getByMap(Map<String,Object> map);
	public ${ClassName} getById(${idType} ${id});
	public ${idType} create(${ClassName} ${className});
	public int update(${ClassName} ${className});
	public int delete(${idType} ${id});
}