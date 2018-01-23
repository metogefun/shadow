package cn.finegames.shadow.common.interceptor;

import cn.finegames.shadow.common.generator.TraceGenerator;
import lombok.Setter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * trace拦截器
 *
 * @author wang zhaohui
 *         http://blog.bcolor.cn
 *         metogefun@gmail.com
 * @since 1.0
 */
@Setter
public class TraceInterceptor extends HandlerInterceptorAdapter {

    private TraceGenerator traceGenerator;

    public TraceInterceptor() {
        this.traceGenerator = UUID.randomUUID()::toString;
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
}
