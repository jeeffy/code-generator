package ${packageName}.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import ${packageName}.bean.${classType};
import ${packageName}.service.${classType}Service;

@RequestMapping(value = "/${format(className, "-")}s")
@RestController
public class ${classType}Controller {
	
	@Autowired
	private ${classType}Service ${className}Service;
	
	
    @GetMapping
    public Page<${classType}> list(${classType} ${className}, Integer page) {
        return ${className}Service.list(${className}, page);
    }

	@GetMapping("/{${id}}")
    public ${classType} get(@PathVariable ${idType} ${id}) {
		return ${className}Service.get(${id});
    }
    
    @PostMapping
    public ${classType} save(@RequestBody ${classType} ${className}) {
		return ${className}Service.save(${className});
    }

}