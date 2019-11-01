package club.zby.Processor;

import club.zby.Entity.NewsContext;
import club.zby.Entity.SpiderData;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 15:53
 */
public abstract class AbstractProcessor<T> implements Processor<T> {

    protected String name;

    @Override
    public File parseToFile(File downloadFile) throws Exception {
        return null;
    }

    @Override
    public List<T> parseToList(File downloadFile) throws Exception {
        return null;
    }

    @Override
    public File parseToFileContext(URL url) throws Exception{
        throw new Exception("不支持解析数据至新文件");
    }
    @Override
    public NewsContext parseToListContext(URL url) throws Exception{
        throw new Exception("不支持解析数据至Java集合");
    }
}
