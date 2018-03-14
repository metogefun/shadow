package cn.finegames.shadow.demo.service;

import cn.finegames.shadow.demo.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * TODO
 *
 * @author wang zhaohui
 * @since 1.0.0
 */
@Slf4j
public class OrderProvider {

    public static void main(String[] args) throws IOException {

        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:order-provider.xml");
        context.start();

        log.info("dubbo provider start success!!!!");

        OrderService orderService = context.getBean("orderService", OrderService.class);
        orderService.getOrderId();

        System.in.read();
    }
}
