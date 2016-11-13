# seckill
慕课秒杀系统练习
mvn archetype:create -DgroudId=org.seckill -DartifactId=seckill 
-DarchetypeArtifactId=maven-archetype-webapp
注意：使用上面的命令的时候，构建项目失败：是因为版本问题
将命令中的archetype:create，修改成为archetype:generate就可以使用。

一、默认生成的web.xml文件中的dtd引用的版本过低，默认使用的servlet2.3  jsp中的el表达式是不工作的。需要引入更高版本的dtd约束文件。可以使用tomcat中的一些实例文件中的web.xml中的约束定义。
二、默认的pom文件中的junit的版本号为3.1.2，需修改成为4.11。
默认的junit使用的是编程的方式使用单元测试，而修改后可以使用注解的方式使用单元测试。


create_definition:
    column_definition
  | [CONSTRAINT [symbol]] PRIMARY KEY [index_type] (index_col_name,...)
  | KEY [index_name] [index_type] (index_col_name,...)
  | INDEX [index_name] [index_type] (index_col_name,...)
  | [CONSTRAINT [symbol]] UNIQUE [INDEX][index_name] [index_type] (index_col_name,...)
  | [FULLTEXT|SPATIAL] [INDEX] [index_name] (index_col_name,...)
  | [CONSTRAINT [symbol]] FOREIGN KEY[index_name] (index_col_name,...) [reference_definition]
  | CHECK (expr) 
