# code-generator user manual

## V1(see branch spring-data-jpa or spring-mybatis-file)
generate java code(bean/dao/mapper/service/controller/test) by config in properties

1. config your parameters in generator.properties, paramters explain as follows
 * package[require]: basic package name
 * location[require]: generated code will be placed here
 * db.type[default is mysql]: support types are mysql and oracle 
 * bean.name[require]: generated object name
 * bean.id[default is id,type is Integer]: please specify if your object id is not *id* 
 * bean.fields: format is [fieldName1:fieldType1,fieldName2:fieldType2...],fieldType default is String
 *

## V2
generate java code(bean/dao/mapper/service/controller/test) based on database

1. config your parameters in generator.properties, paramters explain as follows
 * package[require]: basic package name
 * location[require]: generated code will be placed here
 * tables[require]: table names, split by comma, if the value is __all__, generate all tables