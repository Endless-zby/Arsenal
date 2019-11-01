package club.zby.Processor.Example;

import club.zby.Entity.NewsContext;
import club.zby.Loguntil.MyLogger;
import club.zby.Processor.AbstractProcessor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.Date;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/31 16:35
 */
public class NewsProcessor extends AbstractProcessor {

    @Override
    public NewsContext parseToListContext(URL url) throws Exception{

        Document document = Jsoup.connect(url.toString()).get();

        String title = document.getElementsByClass("pbt").text();
        String time = document.getElementsByClass("pwz").text();
//        String detailed = time.substring(time.indexOf("发稿时间："),time.indexOf(" 作者"));
//        String zuozhe = time.substring(time.indexOf("作者："),time.indexOf(" 来源"));
        Element editor_baidu = document.getElementById("editor_baidu");
        Elements trsEditor = document.getElementsByClass("TRS_Editor");

        MyLogger.log("title标题：  ---   " + title);
//        MyLogger.log("author作者：  ---   " + StringUtils.remove(zuozhe,"作者："));
//        MyLogger.log("time  ---   " + time);
//        MyLogger.log("detailed时间：  ---   " + StringUtils.remove(detailed,"发稿时间："));
        MyLogger.log("TRS_Editor内容：  ---   " + trsEditor.text());
        MyLogger.log("editor_baidu责任编辑：  ---   " + StringUtils.remove(editor_baidu.text(),"责任编辑："));
        MyLogger.log("releaseTime爬取时间：  ---   " + new Date());

        NewsContext newsContext = new NewsContext();

        newsContext.setTitle(title);
        newsContext.setContext(trsEditor.text());
        newsContext.setReleaseTime(new Date());
        newsContext.setUrl(url.toString());
        newsContext.setAuthor("赵博雅");
        newsContext.setSendtime("4444");
        newsContext.setEditor(StringUtils.remove(editor_baidu.text(),"责任编辑："));

        return newsContext;


    }
}
