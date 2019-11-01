package club.zby.Until;

import club.zby.Boot.Example.ExampleBoot;
import club.zby.DataService.Example.exampleDataService;
import club.zby.Downloader.Example.StreamDownloader;
import club.zby.Processor.Example.YouthProcessor;
import club.zby.ScheduleQueue.Example.exampleScheduleQueue;
import club.zby.Spider.MySpider;

import java.net.URL;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 14:50
 */
public class MySpiderFactory{


    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,单线程服务，存储数据至本地临时文件）
     * @return
     */
    public static MySpider getSpiderDataToLocalAndNoDataService(URL[] urls) throws Exception {
        MySpider mySpider = new MySpider(urls)
                .addBoot(new ExampleBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new YouthProcessor("zhaoboya"))
                .addScheduleQueue(new exampleScheduleQueue());
        return mySpider;
    }

    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,多线程服务，存储数据至本地临时文件）
     * @return
     */
    public static MySpider getSpiderDataToLocalNoDataServiceForTheads(URL url) throws Exception {
        MySpider spider = new MySpider(new URL[]{url})
                .addBoot(new ExampleBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new YouthProcessor("zhaoboya"))
                .addScheduleQueue(new exampleScheduleQueue());
        return spider;
    }

    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,单线程服务，存储数据至本地mysql数据库）
     * @return
     */
    public static MySpider getSpiderDataToDataService(URL[] urls) throws Exception {
        MySpider mySpider = new MySpider(urls)
                .addBoot(new ExampleBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new YouthProcessor("zhaoboya"))
                .addScheduleQueue(new exampleScheduleQueue())
                .addDataService(new exampleDataService());
        return mySpider;
    }

    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,多线程服务，存储数据至本地mysql数据库）
     * @return
     */
    public static MySpider getSpiderDataToDataServiceForTheads(URL url) throws Exception {
        MySpider spider = new MySpider(new URL[]{url})
                .addBoot(new ExampleBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new YouthProcessor("zhaoboya"))
                .addScheduleQueue(new exampleScheduleQueue())
                .addDataService(new exampleDataService());
        return spider;
    }


}
