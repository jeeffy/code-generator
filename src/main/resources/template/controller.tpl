package ${packageName}.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ${packageName}.bean.${ClassName};
import ${packageName}.service.${ClassName}Service;

@RequestMapping(value = "/${className}s")
@RestController
public class ${ClassName}Controller {
	
	@Autowired
	private ${ClassName}Service ${className}Service;
	
	
	@GetMapping
    public List<${ClassName}> list(${ClassName} ${className}) {
		return ${className}Service.list(${className});
    }
	
	@GetMapping("/{${id}}")
    public ${ClassName} get(@PathVariable ${idType} ${id}) {
		return ${className}Service.get(${id});
    }
    
    @PostMapping
    public ${ClassName} create(@RequestBody ${ClassName} ${className}) {
		return ${className}Service.create(${className});
    }

    @PutMapping("/{${id}}")
    public ${ClassName} update(@RequestBody ${ClassName} ${className}) {
		return ${className}Service.update(${className});
    }
    
    @DeleteMapping("/{${id}}")
    public void delete(@PathVariable ${idType} ${id}) {
		${className}Service.delete(${id});
    }
    
}