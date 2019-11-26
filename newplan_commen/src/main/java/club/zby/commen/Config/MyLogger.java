package club.zby.commen.Config;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * 对默认的log进行一次封装
 */
public class MyLogger {

    //默认log配置文件
    private static String logBootFile = "log4j.properties";

    //加载log4j配置文件
    static {
        ClassPathResource classPathResource = new ClassPathResource(logBootFile);

        try {
            String url = classPathResource.getURL().toString();


        System.out.println("默认日志配置文件：" + url);
        File file = new File(url);
        if(file.exists()){
            System.out.println("log4j: 默认位置存在配置文件,无需手动加载配置文件");
        }else{
            //配置文件不在默认位置,通过调用api告知log4j配置文件的位置
            PropertyConfigurator.configure(MyLogger.class.getResource(File.separator).getPath() + "/log4j.properties");
        }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private final static Logger logger = Logger.getLogger(MyLogger.class);

    private static Logger getLogger() {
        return logger;
    }

    public static void log(String log) {
        logger.info(log);
    }

    public static void warning(String log) {
        logger.warn(log);
    }

    public static void error(String log) {
        logger.error(log);
    }
}
