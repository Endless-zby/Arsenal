/**
 * @Author: 赵博雅
 * @Date: 2019/10/24 17:18
 */
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class spider implements PageProcessor {

    @Override
    public void process(Page page) {
//        System.out.println( "信息：" +page.getHtml().xpath("//*[@id='Main']/div[1]/div[2]/ul/li[1]/div/div[1]/p/text()") .toString()  );
//        System.out.println( "公司：" +page.getHtml().xpath("//*[@id='Main']/div[1]/div[2]/ul/li[1]/div/div[2]/div[1]/h3/a/text()") .toString()  );
//        System.out.println( "类型：" +page.getHtml().xpath("//*[@id='Main']/div[1]/div[2]/ul/li[1]/div/div[2]/div[1]/p/text()") .toString()  );
        System.out.println( "职位：" +page.getHtml().xpath("//*[@id='main']/div[1]/div[1]/div[1]/div[2]/div[2]/h1/text()") .toString()  );
        System.out.println( "薪资：" +page.getHtml().xpath("//*[@id='main']/div[1]/div[1]/div[1]/div[2]/div[2]/span/text()") .toString()  );
        System.out.println( "学历：" +page.getHtml().xpath("//*[@id='main']/div[1]/div[1]/div[1]/div[2]/p/text()") .toString()  );
        System.out.println( "福利：" +page.getHtml().xpath("//*[@id='main']/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/span[1]/text()") .toString()  );


    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(1000).setRetrySleepTime(5);

    }


    public static void main(String[] args) {
//        Spider.create(new spider()).addUrl("https://www.zhipin.com/c101010100/?ka=open_joblist").run();
        Spider.create(new spider()).addUrl("https://www.zhipin.com/job_detail/3ded0d912dac595903V53tu8E1o~.html?ka=search_list_1").run();
        //27
    }
}
