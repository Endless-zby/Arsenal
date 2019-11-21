package club.zby.ftp.Service;

import club.zby.commen.Config.Result;
import club.zby.commen.Config.StatusCode;
import club.zby.ftp.Entity.FileInfo;
import club.zby.ftp.Untlis.FTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/23 9:18
 */
@Service
public class FileService {

    @Autowired
    private FTPUtil FTPUtil;

    @Autowired
    private DbService dbService;

    @Value("${FTP.imageBaseUrl}")
    private String imageBaseUrl;


    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    public Result uploadPic(MultipartFile multipartFile,String userid){

        //获取上传文件(用户头像图片)文件名
        String oddName = multipartFile.getOriginalFilename();
        //获取文件名后缀
        String suffix = oddName.substring(oddName.lastIndexOf("."));
        //重新命名图片
        long time = new Date().getTime();
        //生成新的文件名
        String newName = "file_" + "_" + time + suffix;
        //文件存放目录(相对于ftp地址的path，比如这里的地址是/www/wwwroot/zby/Pic)
        String filepath = "/";
        try {
            Result result = FTPUtil.uploadFile(filepath, oddName, multipartFile.getInputStream());
            if(result.isFlag()){
//                result.setData(imageBaseUrl + filepath + newName);
//                return result;
                String path = dbService.uploadPic(userid, oddName, multipartFile.getSize(),imageBaseUrl + filepath + oddName);
                if(path != null){
                    result.setData(path);
                    return result;
                }
                return new Result(false, StatusCode.ERROR,"文件已保存,但是数据存储失败,联系管理员",null);
            }else {
                return result;
            }
        } catch (IOException e) {
            return new Result(false, StatusCode.ERROR,"FTP连接失败,请重试",null);
        }
    }

    /**
     * 文件下载，记录文件被下载次数
     * @param fileName
     * @param localPath
     * @return
     */
    public Result downFile(String fileName,String localPath){
        dbService.downNumAddOne(fileName);
        return FTPUtil.downFile(fileName, localPath);
    }

    /**
     * ftp文件列表，需要查询文件的下载次数
     * @return
     */
    public Result fileList() {
        return FTPUtil.fileList();
    }

    /**
     * 从ftp删除文件
     * @param fileName
     * @return
     */
    public Result deleteFile(String fileName){
        return FTPUtil.deleteFile(fileName);
    }

}
