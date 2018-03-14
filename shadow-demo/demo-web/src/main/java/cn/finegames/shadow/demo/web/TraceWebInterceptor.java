package cn.finegames.shadow.demo.web;

import cn.finegames.shadow.core.generator.TraceGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * TODO
 *
 * @author wang zhaohui
 * @since 1.0.0
 */
@Component
public class TraceWebInterceptor extends HandlerInterceptorAdapter {

    private TraceGenerator traceGenerator;

    public TraceWebInterceptor() {
        this.traceGenerator = this::gerRandomTraceId;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        traceGenerator.initTraceContext();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        traceGenerator.destroyTraceContext();
    }

    private String gerRandomTraceId() {
        return UUID.randomUUID().toString();
    }
}
