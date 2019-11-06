package club.zby.main;

import club.zby.Loguntil.MyLogger;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



import java.io.IOException;
import java.util.Map;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/4 10:33
 */
public class Tou {
    public static void main(String[] args) throws IOException {
        String url = "https://www.toutiao.com/api/pc/hot_gallery/?widen=1";

        String result = sendGet(url);
        Map parse = (Map) JSONObject.parse(result);

        MyLogger.log("message:" + parse.get("message"));
        MyLogger.log("data:" + parse.get("data"));

        JSONArray parseArray = (JSONArray) parse.get("data");


        for (int i = 0; i < parseArray.size(); i++) {
            TouTiaoList toutiao = parseArray.getObject(i, TouTiaoList.class);
            MyLogger.log("title：" + StringEscapeUtils.unescapeJava(toutiao.getTitle()));
            MyLogger.log("gallary_flag：" + toutiao.getGallary_flag());
            MyLogger.log("article_url：" + toutiao.getArticle_url());
            MyLogger.log("cover_image_url：" + toutiao.getCover_image_url());
            MyLogger.log("gallery_image_count：" + toutiao.getGallery_image_count());
        }
    }

    public static String getContext(){

        String url = "https://www.toutiao.com/group/6755144585489416717/";


        return null;
    }






/**
 * {
 * 	"title": "\u5c0f\u7c73CC9 Pro \u5b98\u65b9\u8d44\u6599\u5168\u66dd\u5149\uff0c11\u67085\u65e5\u53d1\u5e03\uff0c\u9664\u4e86\u4ef7\u683c\u5df2\u6ca1\u6709\u60ac\u5ff5",
 * 	"gallary_flag": 2,
 * 	"image_list": [{
 * 		"url": "//p9.pstatp.com/list/364x360/pgc-image/9f5886a90f914030a417fe705a5490c4"
 *        }, {
 * 		"url": "//p1.pstatp.com/list/272x178/pgc-image/389d1095a6214ed9a27a30064bf8f923"
 *    }, {
 * 		"url": "//p1.pstatp.com/list/272x178/pgc-image/0d35b3bed98540f69b0f1d1ddf59f906"
 *    }],
 * 	"article_url": "/group/6755144585489416717/",
 * 	"cover_image_url": "//p9.pstatp.com/list/300x170/pgc-image/9f5886a90f914030a417fe705a5490c4",
 * 	"gallery_image_count": 24
 * }
 */





        public static String sendPost(String urlParam) throws Exception {
            // 创建httpClient实例对象
            HttpClient httpClient = new HttpClient();
            // 设置httpClient连接主机服务器超时时间：15000毫秒
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
            // 创建post请求方法实例对象
            PostMethod postMethod = new PostMethod(urlParam);
            // 设置post请求超时时间
            postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
            postMethod.addRequestHeader("Content-Type", "application/json");

            httpClient.executeMethod(postMethod);

            String result = postMethod.getResponseBodyAsString();
            postMethod.releaseConnection();
            return result;
        }

        public static String sendGet(String urlParam) throws HttpException, IOException {
            // 创建httpClient实例对象
            HttpClient httpClient = new HttpClient();
            // 设置httpClient连接主机服务器超时时间：15000毫秒
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
            // 创建GET请求方法实例对象
            GetMethod getMethod = new GetMethod(urlParam);
            // 设置post请求超时时间
            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
            getMethod.addRequestHeader("Content-Type", "application/json");

            httpClient.executeMethod(getMethod);

            String result = getMethod.getResponseBodyAsString();
            getMethod.releaseConnection();
            return result;
        }



}
