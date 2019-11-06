package club.zby.newplan;


import club.zby.newplan.config.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient//开启“在注册中心里 发现其他微服务”的功能
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class NewApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(NewApplication.class, args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

}



