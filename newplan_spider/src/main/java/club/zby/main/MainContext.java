package club.zby.main;

import club.zby.DataService.AbstractDataService;
import club.zby.DataService.DataService;
import club.zby.Entity.NewsContext;
import club.zby.Entity.SpiderData;
import club.zby.Loguntil.MyLogger;
import club.zby.Spider.MySpider;
import club.zby.Spider.MySpiderContext;
import club.zby.Until.MySpiderContextFactory;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/1 18:34
 * MySpider运行测试demo, 对中国青年网的部分数据进行爬取, 只支持单线程, 两种数据保存方式
 * 获取中国青年网前五页的资讯列表中的每一个资讯详情
 */
public class MainContext {

    private DataService dataService;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static URL[] urls;
    public static boolean useThreads = false;


    public static void main(String[] args) throws Exception {
        MyLogger.log("获取所有待爬取的URL");
        urls = new URL[500];
        AbstractDataService abstractDataService = new AbstractDataService();
        abstractDataService.init();
        List<SpiderData> youthNews = abstractDataService.selectList();
        MyLogger.log("队列初始大小：" + urls.length);
        MyLogger.log("获取文章数量：" + youthNews.size());

        for (int i = 0; i < youthNews.size(); i++) {
            urls[i] = new URL("http://" + youthNews.get(i).getUrl());
        }


        long start = System.currentTimeMillis();
        //单线程启动demo
        if(!useThreads){
            //从工厂类中获得一个爬虫实例
            MySpiderContext mySpider = MySpiderContextFactory.getSpiderDataToDataService(urls);
            mySpider.start();

        }
        //多线程启动demo

        long end = System.currentTimeMillis();
//        MyLogger.log("系統运行时间(除线程等待休眠时间)：" + (end - start - ((urls.length-1) * 1000 * 60)) + " ms");
        MyLogger.log("Main ended");



    }
}
