#简介

该项目主要利用Spring Boot的自动化配置特性来实现快速的将swagger2引入spring boot应用来生成API文档，简化原生使用swagger2的整合代码。

- GitHub：https://github.com/lazycathome/spring-boot-starter-swagger

**小工具一枚，欢迎使用和Star支持，如使用过程中碰到问题，可以提出Issue，我会尽力完善该Starter**

# 版本基础

- Spring Boot：1.5.x
- Swagger：2.7.x

# 如何使用

在该项目的帮助下，我们的Spring Boot可以轻松的引入swagger2，主需要做下面配置：

- 在`pom.xml`中引入依赖即可，不需要任何配置，无任何代码侵入：

```xml
<dependency>
	<groupId>cn.bigdb</groupId>
	<artifactId>spring-boot-starter-swagger</artifactId>
	<version>1.0</version>
</dependency>

# 参数配置

更细致的配置内容参考如下：

## 配置示例

```yml
springfox:
  api:
    env: dev    //开放的环境，默认是dev
    group:  lazycathome
    title:  lazy
    desc:   cat home
    version:  v1.0
    termsOfServiceUrl: https://www.github.com/lazycathome
    contact:
      name: liujiangtao
      url:  https://www.github.com/lazycathome
      email: liujt2009@gmail.com
    license:  Apache License 2.0
    licenseUrl: https://www.apache.org/licenses/LICENSE-2.0.html
