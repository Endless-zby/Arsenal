package club.zby.main;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/1 15:19
 */
public class Test {

//    private static String bootFileName = "custom/DemoBoot.properties";
    private static String bootFileName = "download";
    protected static URL bootPropertiesURL;


    public static void main(String[] args) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource(bootFileName);
        System.out.println(classPathResource.getURL());

//        InputStream inputStream =classPathResource.getInputStream();
        bootPropertiesURL = new URL(classPathResource.getURL().toString());

        Properties properties = new Properties();

        properties.load(new FileInputStream(new File(bootPropertiesURL.toURI())));

    }
}
