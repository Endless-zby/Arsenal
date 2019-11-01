package club.zby.Processor;

import club.zby.Entity.NewsContext;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * 爬虫的处理核心，专门对爬取数据进行提炼，常用技术正则表达式
 *
 * 结果直接存在内存中也就是使用Connection集合存着，还有一种是导出到临时文件中
 */
public interface Processor<T> {


    File parseToFile(File downloadFile) throws Exception;

    List<T> parseToList(File downloadFile) throws Exception;

    File parseToFileContext(URL url) throws Exception;

    NewsContext parseToListContext(URL url) throws Exception;

}
