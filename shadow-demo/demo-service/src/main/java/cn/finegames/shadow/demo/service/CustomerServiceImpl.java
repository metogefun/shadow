package cn.finegames.shadow.demo.service;

import cn.finegames.shadow.demo.api.CustomerService;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author wang zhaohui
 * @since 1.0.0
 */
@Slf4j
public class CustomerServiceImpl implements CustomerService {


    @Override
    public Long getCustomerId() {

        log.info("invoke getCustomerId!!!!");
        return Double.valueOf(Math.random() * 1000000).longValue();
    }
}
