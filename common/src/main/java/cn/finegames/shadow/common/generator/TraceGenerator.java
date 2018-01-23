package cn.finegames.shadow.common.generator;

import cn.finegames.shadow.common.TraceContext;

import java.util.function.Supplier;

/**
 * trace生成器
 *
 * @author wang zhaohui
 *         http://blog.bcolor.cn
 *         metogefun@gmail.com
 * @since 1.0
 */
@FunctionalInterface
public interface TraceGenerator {

    /**
     * 生成trace Id
     *
     * @return traceId
     */
    String generate();


    /**
     * 返回 {@link TraceGenerator} 实现
     *
     * @param supplier
     * @return
     */
    static TraceGenerator getTraceGenerator(Supplier<TraceGenerator> supplier) {
        return supplier.get();
    }

    /**
     * init trace context
     */
    default void initTraceContext() {
        TraceContext.defaultContext().init(generate());
    }

    /**
     * destroy trace context
     */
    default void destroyTraceContext() {
        TraceContext.defaultContext().destroy();
    }
}
