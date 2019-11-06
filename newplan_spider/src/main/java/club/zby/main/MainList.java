package club.zby.main;

import club.zby.Loguntil.MyLogger;
import club.zby.Spider.MySpider;
import club.zby.Until.MySpiderFactory;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 14:38
 * MySpider运行测试demo, 对中国青年网的部分数据进行爬取, 可以单线程, 也可以多线程启动同时爬取
 * 获取中国青年网前五页的资讯列表
 */
public class MainList {

    //创建五个线程
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static URL[] urls;
    public static boolean useThreads = false;


    public static void main(String[] args) throws Exception {

        //URL数据demo
        urls = new URL[]{
                new URL("http://news.youth.cn/gn/index.htm"),
                new URL("http://news.youth.cn/gn/index_1.htm"),
                new URL("http://news.youth.cn/gn/index_2.htm"),
                new URL("http://news.youth.cn/gn/index_3.htm"),
                new URL("http://news.youth.cn/gn/index_4.htm")
        };
        long start = System.currentTimeMillis();
        //单线程启动demo
        if(!useThreads){
            //从工厂类中获得一个爬虫实例
            MySpider mySpider =  MySpiderFactory.getSpiderDataToLocalAndNoDataService(urls);
            mySpider.start();

        }
        else{
            for (URL url:
                    urls) {
                executorService.submit(() -> {
                    try {
                        MySpider mySpider =  MySpiderFactory.getSpiderDataToLocalNoDataServiceForTheads(url);
                        mySpider.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            executorService.shutdown();
        }

        long end = System.currentTimeMillis();
        MyLogger.log("系統运行时间(除线程等待休眠时间)：" + (end - start - ((urls.length-1) * 1000 * 60)) + " ms");
        MyLogger.log("Main ended");
    }

}