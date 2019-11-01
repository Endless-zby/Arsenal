package club.zby.Until;

import club.zby.Loguntil.MyLogger;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class FileUtil {


    public static File createEmptyFile(String directoryPath, String newFileName) throws IOException {
        String newfilePath = directoryPath + File.separator + newFileName;
        MyLogger.log("create new file:" + newfilePath);

        File dir = new File(directoryPath);
        File newFile = new File(newfilePath);

        //检查dir是否存在，若不存在就创建
        if (!dir.isDirectory()) {
            if(!dir.mkdirs()){
                //创建失败就直接返null
                return null;
            }
        }

        if (newFile.exists()) {
            if(!newFile.delete()){
                //删除已存在文件失败就返null
                return null;
            }
        }
        if (newFile.createNewFile()) {
            MyLogger.log("create:" + newFileName + "success");
            return newFile;
        }

        return null;
    }

    public static String getPrefix(String custom) throws Exception {
        if (RandomNameUtil.isNull(custom)) {
            throw new Exception("getPrefix error");
        }

        return "file://" + FileUtil.class.getResource(File.separator).getPath() + File.separator + custom + File.separator;

    }
}
