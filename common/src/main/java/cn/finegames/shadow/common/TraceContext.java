package cn.finegames.shadow.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Optional;

import static cn.finegames.shadow.common.TraceConst.TRACE_ID;

/**
 * the context of trace
 *
 * @author wang zhaohui
 *         http://blog.bcolor.cn
 *         metogefun@gmail.com
 * @since 1.0
 */
@Slf4j
public final class TraceContext implements AutoCloseable {

    private ThreadLocal<Trace> traces = new InheritableThreadLocal<>();

    private volatile static TraceContext traceContext;

    private TraceContext() {
    }

    /**
     * context init
     *
     * @param traceId
     */
    public void init(String traceId) {
        traces.set(Trace.newInstance(traceId));
        MDC.put(TRACE_ID, traceId);
        if (log.isDebugEnabled()) {
            log.debug("thread {} init trace {}", Thread.currentThread(), traceId);
        }
    }


    /**
     * the id of trace
     *
     * @return the id of {@link Trace}
     */
    public Optional<String> retainTrace() {
        Trace trace = traces.get();
        if (trace == null) {
            return Optional.empty();
        }

        if (log.isDebugEnabled()) {
            log.debug("thread {} retain trace {} + 1", Thread.currentThread(), trace.getTraceId());
        }
        return Optional.of(trace.retain().getTraceId());
    }

    /**
     * context destroy
     */
    public void destroy() {
        Trace trace = traces.get();
        if (trace == null) {
            MDC.clear();
        }

        if (trace.release()) {
            traces.set(null);
            MDC.clear();

            if (log.isDebugEnabled()) {
                log.debug("thread {} destroy trace {} context!", Thread.currentThread(), trace.getTraceId());
            }
        }
    }

    @Override
    public void close() throws RuntimeException {
        destroy();
    }

    /**
     * default trace context
     *
     * @return
     */
    public static final TraceContext defaultContext() {
        if (traceContext == null) {
            synchronized (TraceContext.class) {
                if (traceContext == null) {
                    traceContext = new TraceContext();
                }
            }
        }

        return traceContext;
    }

}
