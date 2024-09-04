# Achilles 低代码开发平台(半成品)
## jdk版本17
***
- 现有的低代码开发平台基本上是基于jdk的Script引擎.  
通过springMVC的RequestMappingHandlerMapping注册接口, 该接口执行外部输入的Groovy代码.
- Achilles则是通过jdk的JavaCompile.CompilationTask将外部输入的java代码编译为字节码, 再通过打破双亲委派机制加载卸载字节码.  
最后仍是通过springMVC的RequestMappingHandlerMapping注册接口, 不过该接口执行的是对象的execute方法.   
**具体的代码在org.earthdog.achilles.tools.loader包中.**
- test包下的org.earthdog.achilles.Test类做了script与sourceLoad的简单性能测试, sourceLoad每次的加载时间较长, 但执行时间很短.