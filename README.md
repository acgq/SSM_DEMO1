# 基于SSM框架的人力资源管理后台

这是使用Spring,Spring MVC,Mybatis创建的一个简单的人力资源管理后台。
实现的功能：
- 简单的登陆和退出功能
- 员工和部门信息的增加，修改和删除

## 本地部署
- git clone
- 创建数据库,打开项目,在pom.xml和database.properties中修改数据库
- 使用mvn flyway:migrate迁移数据库
- mvn jetty:run
