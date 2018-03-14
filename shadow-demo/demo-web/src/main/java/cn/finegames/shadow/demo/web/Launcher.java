package cn.finegames.shadow.demo.web;

import cn.finegames.shadow.demo.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 *
 * @author wang zhaohui
 * @since 1.0.0
 */
@Slf4j
@RestController
@SpringBootApplication
@ImportResource("classpath:order-consumer.xml")
public class Launcher {

    @Autowired
    private TraceWebInterceptor traceWebInterceptor;

    @Autowired
    private OrderService orderService;


    @GetMapping("/order")
    private String getOrder() {
        log.info("Request getOrderId !!!!");
        return orderService.getOrderId();
    }

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        SpringApplication.run(Launcher.class, args);
    }


    @Bean
    public WebMvcConfigurer gameInterceptorConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(traceWebInterceptor).addPathPatterns("/**");
            }
        };
    }
}
