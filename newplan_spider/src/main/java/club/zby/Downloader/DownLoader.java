package club.zby.Downloader;

import java.io.File;
import java.net.URL;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 15:34
 * 使用Java网络编程接口下载网络资源,存储至本地
 */
public interface DownLoader {

    void reset(URL url);

    //start download!
    File run() throws Exception;

    String getName();

}
