# springboot-quartz
> 本项目使用springboot框架，集成quartz定时器

# 目录
* [1 创建项目](#01)
* [2 编写代码](#02)
* [3 测试](#03)

## <div id="01"></div>
## 1 创建项目
> 参照或复制[springboot-maven](https://github.com/zhigen/springboot-maven)

## <div id="02"></div>
## 2 编写代码
    2.1、pom.xml文件引入依赖
    2.2、编写注解定时器
    2.3、编写bean定时器
    2.4、编写动态增、删、改定时器测试类

## <div id="03"></div>
## 3 测试
    3.1、运行项目，观察注解及bean方式定时器
    3.2、POST http://localhost:8080/ 增加定时器
    3.3、DELETE http://localhost:8080/ 删除定时器
    3.4、PATCH http://localhost:8080/ 修改定时器