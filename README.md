# code-generator user manual

generate java code(bean/dao/mapper/service/controller/test) by parameters

1. config your parameters in generator.properties, paramters explain as follows
 * package[require]: basic package name
 * location[require]: generated code will be placed here
 * bean.name[require]: generated object name
 * bean.id[default is id,type is Integer]: please specify if your object id is not *id* 
 * bean.fields: format is [fieldName1:fieldType1,fieldName2:fieldType2...],fieldType default is String