package com.zby;

import com.zby.redis.config.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/1 17:01
 */
@EnableSwagger2
@SpringBootApplication
public class RedisTestApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(RedisTestApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}