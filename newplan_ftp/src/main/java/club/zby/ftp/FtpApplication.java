package club.zby.ftp;


import club.zby.commen.Config.IdWorker;
import club.zby.ftp.Service.CustomMultipartResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class FtpApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(FtpApplication.class, args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

//    @Bean(name = "multipartResolver")
//    public MultipartResolver multipartResolver()
//    {
//        return new CustomMultipartResolver();
//    }
}



