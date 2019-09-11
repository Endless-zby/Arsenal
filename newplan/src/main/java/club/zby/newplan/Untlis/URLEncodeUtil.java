/**
 * @公司 *松的个人工作室
 * @用户: Administrator
 * @类命: URLEncodeUtil
 * @创建时间: 2018/10/30/030/ 15:03
 * @创建人:
 * @描叙:
 */
package club.zby.newplan.Untlis;

import java.io.UnsupportedEncodingException;

public class URLEncodeUtil {
    private final static String ENCODE = "UTF-8";
    /**
     * URL 解码
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * URL 转码
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}

