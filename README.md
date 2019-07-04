# code-generator user manual


## V2
generate java code(bean/dao/mapper/service/controller/test) based on tables in database, primary key is required.

1. config parameters in generator.properties
 * package[require]: basic package name
 * location[require]: generated code will be placed here
 * tables[require]: table names, split by comma, if the value is `__all__`, generate all tables
 * removable_table_prefix: remove prefix of table when generate code 
 * layers: support values are controller, service, dao, mapper, bean, controllerTest, serviceTest
2. run Generator, you will find generated code in location.