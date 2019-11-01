package club.zby.Boot.Example;

import club.zby.Boot.AbstractBoot;
import club.zby.Constants.Constants;
import club.zby.Loguntil.MyLogger;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 16:28
 * boot配置模板
 * 自定义boot配置初始化，继承AbstractBoot使ExampleBoot生效
 */
public class ExampleBoot extends AbstractBoot {

    //自定义文件名：DemoBoot.properties
    private static String bootFileName = "custom/DemoBoot.properties";

    protected Properties loadBootProperties() throws IOException, URISyntaxException {
        MyLogger.log("使用[自定义]boot配置");

        ClassPathResource classPathResource = new ClassPathResource(bootFileName);

        String url = classPathResource.getURL().toString();
        MyLogger.log("加载[自定义]配置文件，路径：" + url);
        bootPropertiesURL = new URL(url);

        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(bootPropertiesURL.toURI())));
        return properties;
    }

    protected boolean initConstants(Properties properties) {
        //init your constans here

        Constants.theadPoolSize = Integer.parseInt(properties.getProperty("ThreadPoolSize"));
        Constants.executeTimes = Integer.parseInt(properties.getProperty("executeTimes"));
        Constants.filePath = properties.getProperty("filePath");
        Constants.spiderPrefixName = properties.getProperty("spiderPrefixName");
        Constants.dataFileName = properties.getProperty("dataFileName");
        Constants.resultFileName = properties.getProperty("resultFileName");

        return true;
    }

}
