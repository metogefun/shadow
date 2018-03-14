package cn.finegames.shadow.demo.service;

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
public class CustomerProvider {

    public static void main(String[] args) throws IOException {

        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:customer-provider.xml");
        context.start();

        log.info("dubbo provider start success!!!!");

        System.in.read();
    }
}
