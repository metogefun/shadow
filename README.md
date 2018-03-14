# shadow

目前只上线dubbo版本

在调用发起时提供唯一的trace id，并在整个方法调用链将其传递下去，结合log框架将trace id和业务日志相关联，为调用链日志追踪以及查询提供方便。


## 系统要求

* Java 1.8

## 使用方式(dubbo)

如果是一个controller作为一个调用的开始，需要在提供一个拦截器为每个调用提供一个唯一的trace id。可以参照``cn.finegames.shadow.demo.web.TraceWebInterceptor``。并在使用dubbo框架的工程引入``shadow-dubbo``模块即可。

非web形式可以在方法上添加``cn.finegames.shadow.core.annotation.Trace``注解。