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
		return ${className}Service.get(${className});
    }
	
	@GetMapping("/{${id}}")
    public ${ClassName} detail(@PathVariable ${idType} ${id}) {
		return ${className}Service.getById(${id});
    }
    
    @PostMapping
    public ${ClassName} save(${ClassName} ${className}) {
		return ${className}Service.save(${className});
    }

    @DeleteMapping("/{${id}}")
    public int delete(@PathVariable ${idType} ${id}) {
		return ${className}Service.delete(${id});
    }
    
}