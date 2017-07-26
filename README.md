# code-generator user manual


## V2
generate java code(bean/dao/mapper/service/controller/test) based on tables in database

1. config parameters in generator.properties
 * package[require]: basic package name
 * location[require]: generated code will be placed here
 * tables[require]: table names, split by comma, if the value is `__all__`, generate all tables
 * removable_table_prefix: remove prefix of table when generate code 
 * dao-layer-implement: mybatis(default) and jpa are supported 
2. run Main, you will find generate code in location.