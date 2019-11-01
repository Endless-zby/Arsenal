package club.zby.Spider;


import club.zby.Boot.Boot;
import club.zby.Boot.Example.ExampleBoot;
import club.zby.DataService.DataService;
import club.zby.Entity.NewsContext;
import club.zby.Loguntil.MyLogger;
import club.zby.Processor.Processor;
import club.zby.ScheduleQueue.Example.exampleScheduleQueue;
import club.zby.ScheduleQueue.ScheduleQueue;
import club.zby.Downloader.DownLoader;
import club.zby.Downloader.Example.StreamDownloader;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/31 10:18
 */
public class MySpiderContext {

    private Boot boot;
    private ScheduleQueue scheduleQueue;
    private DownLoader downloader;
    private Processor processor;
    private DataService dataService;
    private static URL[] urls;

    public MySpiderContext(URL[] urls){
        this.urls = urls;
    }

    private void init() throws Exception {
        //boot
        if(boot == null){
            boot = new ExampleBoot();
        }
        boot.beforeBoot();
        boot.boot();
        boot.afterBoot();

        //scheduleQueue
        if(scheduleQueue == null){
            scheduleQueue = new exampleScheduleQueue();
        }
        for (URL url: urls) {
            scheduleQueue.addNewURL(url);
        }

        //processor
        if(processor == null){
            throw new Exception("please add your own processor");
        }

        //dataService
        if(dataService == null){
            MyLogger.log("there's no dataService,so data will don't save into your database.");
        }else{
            dataService.init();
        }

    }

    public void start() throws Exception {
        MyLogger.log("Main start work!");

        init();

        ArrayList<NewsContext> newsContexts = new ArrayList<>();

        while(scheduleQueue.size() > 0){

            if(dataService == null){
                MyLogger.log("准备解析网页内容。。");
                File tempFile = processor.parseToFileContext(scheduleQueue.nextURL());
            }else{
                MyLogger.log("准备解析网页内容。。");
                NewsContext newsContext = processor.parseToListContext(scheduleQueue.nextURL());
                newsContexts.add(newsContext);
            }

//            if(scheduleQueue.size() >= 1){
//                MyLogger.log("线程休眠中，一分钟后爬取下一页--------------------------------------------------------------");
//                Thread.sleep(1000*60);//一分钟爬一次
//            }
        }
        dataService.adds(newsContexts);

        MyLogger.log("just do it! boy");
    }

    public MySpiderContext addBoot(Boot boot){
        this.boot = boot;
        return this;
    }

    public MySpiderContext addScheduleQueue(ScheduleQueue scheduleQueue){
        this.scheduleQueue = scheduleQueue;
        return this;
    }

    public MySpiderContext addDownloader(DownLoader downloader){
        this.downloader = downloader;
        return this;
    }

    public MySpiderContext addProcessor(Processor processor) throws Exception {
        this.processor = processor;
        return this;
    }

    public MySpiderContext addDataService(DataService dataService){
        this.dataService = dataService;
        return this;
    }


}
