package cn.finegames.shadow.core.annotation;

import java.lang.annotation.*;

/**
 * trace注解用于非web入口日志追踪。通过拦截器拦截添加该注解的方法。
 *
 * @author wang zhaohui
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Trace {
}
