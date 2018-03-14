package cn.finegames.shadow.core.interceptor;

import cn.finegames.shadow.core.generator.TraceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.UUID;

/**
 * trace拦截器
 *
 * @author wang zhaohui
 * @since 1.0.0
 */
@Slf4j
@Aspect
public final class TraceInterceptor {

    private TraceGenerator traceGenerator;

    public TraceInterceptor() {
        this.traceGenerator = this::getRandomTraceId;
    }

    @Before("tracePointcut()")
    public void beforeInvoke() {
        traceGenerator.initTraceContext();
    }

    @After("tracePointcut()")
    public void afterInvoke() {
        traceGenerator.destroyTraceContext();
    }

    @Pointcut("@annotation(cn.finegames.shadow.core.annotation.Trace)")
    private void tracePointcut() {
    }

    private String getRandomTraceId() {
        return UUID.randomUUID().toString();
    }
}
