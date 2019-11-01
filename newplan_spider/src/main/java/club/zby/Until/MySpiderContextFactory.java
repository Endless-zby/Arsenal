package club.zby.Until;


import club.zby.Boot.Example.ExampleBoot;
import club.zby.DataService.Example.exampleDataService;
import club.zby.DataService.NewsContextService;
import club.zby.Downloader.Example.StreamDownloader;
import club.zby.Processor.Example.NewsProcessor;
import club.zby.Processor.Example.YouthProcessor;
import club.zby.ScheduleQueue.Example.exampleScheduleQueue;
import club.zby.Spider.MySpider;
import club.zby.Spider.MySpiderContext;

import java.net.URL;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/31 16:20
 */
public class MySpiderContextFactory {


    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,单线程服务，存储数据至本地临时文件）
     * @return
     */
    public static MySpiderContext getSpiderContextToLocalAndNoDataService(URL[] urls) throws Exception {
        MySpiderContext mySpiderContext = new MySpiderContext(urls)
                .addBoot(new ExampleBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new NewsProcessor())
                .addScheduleQueue(new exampleScheduleQueue());
        return mySpiderContext;
    }

    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,单线程服务，存储数据至本地mysql数据库）
     * @return
     */
    public static MySpiderContext getSpiderDataToDataService(URL[] urls) throws Exception {
        MySpiderContext mySpiderContext = new MySpiderContext(urls)
                .addBoot(new ExampleBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new NewsProcessor())
                .addScheduleQueue(new exampleScheduleQueue())
                .addDataService(new NewsContextService());
        return mySpiderContext;
    }


}
