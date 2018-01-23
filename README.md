# shadow

为dubbo、sprin cloud提供trace framework。

## 系统要求

* Java 1.8
* Slf4j

## dubbo实现方式

需要提供一个拦截器实现，来提供traceId。

如果Spring MVC是入口，可以在工程中添加``cn.finegames.shadow.common.interceptor.TraceInterceptor``拦截器。如果是Java Application需要自己提供拦截器，或后期提供拦截器实现。

提供拦截器后，引入dubbo模块就可使用。