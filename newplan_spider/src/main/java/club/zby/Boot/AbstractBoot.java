package club.zby.Boot;

import club.zby.Loguntil.MyLogger;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Boot的最小功能实现
 */
public abstract class AbstractBoot implements Boot{

    protected static URL bootPropertiesURL;
    //默认配置文件名：boot.properties
    private static String bootFileName = "boot.properties";


    @Override
    public void beforeBoot() {
        //do some work beforeBoot
    }

    @Override
    public void boot() {
        //Let's link start!
        try {
            Properties properties = loadBootProperties();

            boolean initFlag =  initConstants(properties);

            if(initFlag){
                MyLogger.log("boot success! ^_^");
            }

        } catch (Exception e) {
            MyLogger.warning("boot启动出现了一些问题,但不致命");
        }
    }

    @Override
    public void afterBoot() {

    }



    protected Properties loadBootProperties() throws IOException, URISyntaxException {
        MyLogger.log("使用[默认]boot配置");

        ClassPathResource classPathResource = new ClassPathResource(bootFileName);

        String url = classPathResource.getURL().toString();
        MyLogger.log("加载[默认]配置文件，路径：" + url);
        bootPropertiesURL = new URL(url);

        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(bootPropertiesURL.toURI())));
        return properties;
    }

    protected boolean initConstants(Properties properties){
        //初始化一些系统常量
        MyLogger.log("初始化boot数据： " + "ThreadPoolSize  ----------" + properties.getProperty("ThreadPoolSize"));
        MyLogger.log("初始化boot数据： " + "executeTimes  ----------" + properties.getProperty("executeTimes"));
        MyLogger.log("初始化boot数据： " + "filePath  ----------" + properties.getProperty("filePath"));
        MyLogger.log("初始化boot数据： " + "spiderPrefixName  ----------" + properties.getProperty("spiderPrefixName"));
        MyLogger.log("初始化boot数据： " + "dataFileName  ----------" + properties.getProperty("dataFileName"));
        MyLogger.log("初始化boot数据： " + "resultFileName  ----------" + properties.getProperty("resultFileName"));
        return true;
    }
}
