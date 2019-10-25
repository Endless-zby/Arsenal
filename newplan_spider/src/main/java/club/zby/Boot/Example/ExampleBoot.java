package club.zby.Boot.Example;

import club.zby.Boot.AbstractBoot;
import club.zby.Constants.Constants;
import club.zby.Loguntil.MyLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    private static String bootFileName = "DemoBoot.properties";

    protected Properties loadBootProperties() throws IOException, URISyntaxException {
        MyLogger.log("使用自定义boot配置");
        String url = "file://" + AbstractBoot.class.getResource(File.separator).getPath() + File.separator + "properties" + File.separator + "Example" + File.separator + bootFileName;
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
