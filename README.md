#nethp-components-zootopia-chameleon

### nethp-components-zootopia-chameleon介绍
`nethp-components-zootopia-chameleon`是自定义的`archetype`，通过使用`nethp-components-zootopia-chameleon`可以生成满足我们项目需求的springboot工程模板，提高开发效率的同时可以统一团队内的项目结构风格

### nethp-components-zootopia-chameleon通过idea创建项目
####1、选择maven项目
####2、注意使用jdk1.8
####3、添加archetype
    groupId:com.jd.nethp.components.zootopia.chameleon
    artifactId:nethp-components-zootopia-chameleon
    version:1.1-SNAPSHOT


####4、选择已添加的该archetype，然后录入新项目的groupId、artifactId即可

### 创建项目后如何启动：
#### 1、录入新工程的APP_NAME
    在common模块AppConstant类中录入APP_NAME
#### 2、mysql数据库连接
#### 3、jimdb缓存连接
#### 4、ducc连接
    1）如需ducc配置，需要在common模块中pom.xml的ducc依赖包引用打开
    2）将配置文件中的ducc配置打开，并配上自己的ducc配置即可
    3）duccConfig工具在service模块的utils文件中有示例，@Value注解注意导入org.springframework.beans.factory.annotation.Value
#### 5、完成以上配置即可启动项目

### 命令使用步骤
#### 1、mvn clean install
#### 2、mvn archetype:generate -DgroupId=com.jd.medicine.demo -DartifactId=medicine-demo -Dversion=1.0.0-SNAPSHOT -DarchetypeGroupId=com.jd.nethp.components.zootopia.chameleon -DarchetypeArtifactId=nethp-components-zootopia-chameleon -DarchetypeVersion=1.1-SNAPSHOT -X -DarchetypeCatalog=local



