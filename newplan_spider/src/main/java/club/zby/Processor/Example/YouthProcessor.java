package club.zby.Processor.Example;



import club.zby.Entity.SpiderData;
import club.zby.Loguntil.MyLogger;
import club.zby.Processor.AbstractProcessor;
import club.zby.Until.FileUtil;
import club.zby.Until.RandomNameUtil;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 中国青年网解析器，没有使用其他HTML解析工具，纯粹手写的一个简单的解析器。如果你喜欢快捷的解析html，那么Jsoup是你的一个选择
 */
public class YouthProcessor extends AbstractProcessor<SpiderData> {

    public YouthProcessor(String name){
        this.name = name;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public File parseToFile(File downloadFile) throws Exception {
        if(downloadFile == null){
            throw new Exception("parse file is null");
        }

        File destinationFile = FileUtil.createEmptyFile(new URL(FileUtil.getPrefix("ProcessorTmp")).getPath(), RandomNameUtil.getRandomFileName() + "_" + name);
        MyLogger.log("创建临时目录文件,准备放入解析结果" + destinationFile);
        MyLogger.log("为后续操作对待解析文件和保存结果的临时文件赋权：rw");
        try(RandomAccessFile randomAccessFile_read = new RandomAccessFile(downloadFile,"rw");
            RandomAccessFile randomAccessFile_write = new RandomAccessFile(destinationFile,"rw")){
            String curLine = "";
            MyLogger.log("开始按行读取文件");
            while ( (curLine = randomAccessFile_read.readLine()) != null){
                if (RandomNameUtil.isNull(curLine)){
                    continue;
                }
                if(curLine.contains("<li>")){
                    //读取文章时间
                    String time = curLine.substring(curLine.indexOf("<font>"),curLine.indexOf("</font>")).substring(6);
                    //读取文章标题
                    String content = curLine.split("</a></li>")[0].substring(curLine.lastIndexOf(".htm\">")).substring(6);
                    //读取文章内容地址
                    String detailed = curLine.substring(curLine.indexOf("news"),curLine.indexOf("\">"));
                    //同一转码
                    time = new String(time.getBytes(StandardCharsets.ISO_8859_1),"GBK");
                    content = new String(content.getBytes(StandardCharsets.ISO_8859_1),"GBK");
                    detailed = new String(detailed.getBytes(StandardCharsets.ISO_8859_1),"GBK");
                    MyLogger.log(time + content + detailed);

                    randomAccessFile_write.write((time + "\t" +content + "\t" + detailed).getBytes("GBK"));
                    randomAccessFile_write.write("\n".getBytes("GBK"));

                }
                if(curLine.contains("</ul>")){
                    break;
                }
            }
        }
        return destinationFile;
    }
}
