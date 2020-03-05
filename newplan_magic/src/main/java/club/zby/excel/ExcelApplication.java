package club.zby.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class ExcelApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class, args);
    }

}



