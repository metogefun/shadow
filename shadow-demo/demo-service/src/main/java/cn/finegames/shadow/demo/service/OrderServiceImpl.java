package cn.finegames.shadow.demo.service;

import cn.finegames.shadow.core.annotation.Trace;
import cn.finegames.shadow.demo.api.CustomerService;
import cn.finegames.shadow.demo.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * TODO
 *
 * @author wang zhaohui
 * @since 1.0.0
 */
@Slf4j
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerService customerService;

    @Trace
    @Override
    public String getOrderId() {
        log.info("invoke getOrderId!!!");

        Long customerId = customerService.getCustomerId();

        return String.valueOf(UUID.randomUUID().getLeastSignificantBits() + "####" + customerId);
    }
}
