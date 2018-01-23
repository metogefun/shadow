package cn.finegames.shadow.common;

import lombok.Getter;
import lombok.ToString;

/**
 * Trace信息
 * <p>
 * 线程安全
 *
 * @author wang zhaohui
 *         http://blog.bcolor.cn
 *         metogefun@gmail.com
 * @since 1.0
 */
@Getter
@ToString
class Trace implements ReferenceCounted {

    private int refCnt;

    private String traceId;

    private Trace(String traceId) {
        this.traceId = traceId;
        this.refCnt = 1;
    }

    @Override
    public int refCnt() {
        return refCnt;
    }

    @Override
    public Trace retain() {
        return retain(1);
    }

    @Override
    public Trace retain(int increment) {
        refCnt += increment;
        return this;
    }
    
    @Override
    public boolean release(int decrement) {
        return (refCnt -= decrement) == 0;
    }

    public static Trace newInstance(String traceId) {
        return new Trace(traceId);
    }
}
