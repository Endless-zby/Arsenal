package club.zby.main;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/4 14:44
 */
public class HttpUntil {


    public static void main(String[] args) {


        HashMap<String, String> params = new HashMap<>();
        params.put("username","160809011103");
        params.put("password","zby123456");
        params.put("lt","LT-642915-WbNswefynHVt7TcDwBEujhMnVyqscK");
        params.put("execution","e5s1");
        params.put("_eventId","submit");

//        String s1 = sendPost("http://cas.xijing.edu.cn:8081/default/commom/login/com.carry.commom.UserLogin.ValidationUserType.biz.ext", params);
        String s1 = sendPost("https://cas.xijing.edu.cn/xjtyrz/login", params);
        System.out.println(s1);


    }


        /**
         * 向指定URL发送GET方法的请求
         *
         * @param url
         *            发送请求的URL
         * @param param
         *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @return URL 所代表远程资源的响应结果
         */
        public static String sendGet(String url, String param) {
            String result = "";
            BufferedReader in = null;
            try {
                String urlNameString = url + "?" + param;
                URL realUrl = new URL(urlNameString);
                // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                // 设置通用的请求属性
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 建立实际的连接
                connection.connect();
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                // 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));
                }
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送GET请求出现异常！" + e);
                e.printStackTrace();
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return result;
        }

        /**
         * 向指定 URL 发送POST方法的请求
         *
         * @param url
         *            发送请求的 URL
         * @param params
         *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @return 所代表远程资源的响应结果
         */
        public static String sendPost(String url, Map<String, String> params) {
            URL u = null;
            HttpURLConnection con = null;
            // 构建请求参数
            StringBuffer sb = new StringBuffer();
            String par = null;
            if (params != null) {
                for (Map.Entry<String, String> e : params.entrySet()) {
                    sb.append(e.getKey());
                    sb.append("=");
                    sb.append(e.getValue());
                    sb.append("&");
                }
                sb.substring(0, sb.length() - 1);
                par = sb.substring(0,sb.length() - 1);
            }
            System.out.println("send_url:" + url);
            System.out.println("send_data:" + par);
            // 尝试发送请求
            try {
                u = new URL(url);
                con = (HttpURLConnection) u.openConnection();
                //// POST 只能为大写，严格限制，post会不识别
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setUseCaches(false);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                osw.write(par);
                osw.flush();
                osw.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }

            // 读取返回内容
            StringBuffer buffer = new StringBuffer();
            try {
                //一定要有返回值，否则无法把请求发送给server端。
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                    buffer.append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }


}
