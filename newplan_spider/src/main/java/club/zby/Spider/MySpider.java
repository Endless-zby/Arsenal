package club.zby.Spider;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 14:53
 */

import club.zby.Boot.Boot;
import club.zby.Boot.Example.ExampleBoot;
import club.zby.DataService.DataService;
import club.zby.Downloader.DownLoader;
import club.zby.Downloader.Example.StreamDownloader;
import club.zby.Loguntil.MyLogger;
import club.zby.Processor.Processor;
import club.zby.ScheduleQueue.Example.exampleScheduleQueue;
import club.zby.ScheduleQueue.ScheduleQueue;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * MySpider实体类
 */
public class MySpider {

    private Boot boot;
    private ScheduleQueue scheduleQueue;
    private DownLoader downloader;
    private Processor processor;
    private DataService dataService;
    private static URL[] urls;

    public MySpider(URL[] urls){
        this.urls = urls;
    }

    private void init() throws Exception {
        //boot
        if(boot == null){
            boot = new ExampleBoot();
        }
        boot.beforeBoot();
        //主配置加载
        boot.boot();
        boot.afterBoot();

        //scheduleQueue
        if(scheduleQueue == null){
            scheduleQueue = new exampleScheduleQueue();
        }
        for (URL url: urls) {
            scheduleQueue.addNewURL(url);
        }

        //downloader
        if(downloader == null){
            downloader = new StreamDownloader();
        }

        //processor
        if(processor == null){
            throw new Exception("请编写解析规则（核心区域）");
        }

        //dataService
        if(dataService == null){
            MyLogger.warning("数据服务启动失败，程序结果将放弃储存数据库，只输出文件！");
        }else{
            dataService.init();
        }

    }

    public void start() throws Exception {
        MyLogger.log("Main start work!");

        init();

        while(scheduleQueue.size() > 0){
            downloader.reset(scheduleQueue.nextURL());
            File downloadFile = downloader.run();
            MyLogger.log("下载待爬起网页文件完成！");
//            if(dataService == null){
//                MyLogger.log("准备解析网页内容。。");
//                File tempFile = processor.parseToFile(downloadFile);
//            }else{
//                List list = processor.parseToList(downloadFile);
//                dataService.adds(list);
//            }

            MyLogger.log("准备解析网页内容。。");
            File tempFile = processor.parseToFile(downloadFile);


            if(scheduleQueue.size() >= 1){
                MyLogger.log("线程休眠中，一分钟后爬取下一页--------------------------------------------------------------");
                Thread.sleep(1000*60);//一分钟爬一次
            }
        }

        MyLogger.log("just do it! boy");
    }

    public MySpider addBoot(Boot boot){
        this.boot = boot;
        return this;
    }

    public MySpider addScheduleQueue(ScheduleQueue scheduleQueue){
        this.scheduleQueue = scheduleQueue;
        return this;
    }

    public MySpider addDownloader(DownLoader downloader){
        this.downloader = downloader;
        return this;
    }

    public MySpider addProcessor(Processor processor) throws Exception {
        this.processor = processor;
        return this;
    }

    public MySpider addDataService(DataService dataService){
        this.dataService = dataService;
        return this;
    }


}
