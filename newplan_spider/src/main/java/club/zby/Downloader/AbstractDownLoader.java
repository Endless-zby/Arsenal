package club.zby.Downloader;

import club.zby.Loguntil.MyLogger;
import club.zby.Until.FileUtil;
import club.zby.Until.RandomNameUtil;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.net.URL;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 15:36
 */
public abstract class AbstractDownLoader implements DownLoader {

    protected String name;
    protected URL url;

    @Override
    public void reset(URL url) {
        if(url == null && this.url == null){
            MyLogger.error("url is null. Downloader stopped.");
        }
        this.url = url;
        MyLogger.log("Downloader reset start.");
        MyLogger.log("new URL is : " + url.getPath());
    }

    @Override
    public File run() throws Exception {
        if(url == null || name == null){
            throw new Exception("Downloader can't run without url or name");
        }
        MyLogger.log("Downloader start run!");
        MyLogger.log("URL is : " + url.getPath());
        MyLogger.log("name is : " + this.name);
        return FileUtil.createEmptyFile(new URL(getPrefixPath()).getPath() ,getName());
    }

    @Override
    public String getName() {
        if(RandomNameUtil.isNull(name)){
            MyLogger.warning("未命名下载器。。。");
            name = RandomNameUtil.getRandomFileName();
            MyLogger.log("随机建立下载器：" + name);
        }
        MyLogger.log("下载器：" + name);
        return name;
    }


    protected String getPrefixPath() throws Exception {
        return FileUtil.getPrefix("download");
    }
}
