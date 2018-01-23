package cn.finegames.shadow.dubbo;

import cn.finegames.shadow.common.TraceContext;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static cn.finegames.shadow.common.TraceConst.TRACE_ID;

/**
 * dubbo trace consumer filter
 *
 * @author wang zhaohui
 *         http://blog.bcolor.cn
 *         metogefun@gmail.com
 * @since 1.0
 */
@Slf4j
@Activate(group = Constants.PROVIDER)
public class TraceConsumerFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try (TraceContext ctx = TraceContext.defaultContext()) {
            Optional<String> optional = ctx.retainTrace();
            if (optional.isPresent()) {
                invocation.getAttachments().put(TRACE_ID, optional.get());
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("invoke {}.{} without trace id", invoker.getInterface(), invocation.getMethodName());
                }
            }
            return invoker.invoke(invocation);
        }
    }
}
