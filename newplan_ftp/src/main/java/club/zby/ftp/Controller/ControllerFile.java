package club.zby.ftp.Controller;

import club.zby.ftp.Config.Result;
import club.zby.ftp.Config.StatusCode;
import club.zby.ftp.Untlis.FTPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/22 14:01
 */
@Api(value = "FTP")
@Controller
@RequestMapping(value = "FtpServer")
public class ControllerFile {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FTPUtil FTPUtil;

    private static final String imageBaseUrl = "ftp://39.96.160.110";


    @ResponseBody
    @ApiOperation(value="ftp文件上传", notes="ftp文件上传测试")
    @PostMapping(value = "upload")
    public Result uploadPic(MultipartFile multipartFile) {
        //获取当前登录者id
        String userid = (String)request.getAttribute("userid");
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
        //
        try {
            Result result = FTPUtil.uploadFile(filepath, newName, multipartFile.getInputStream());
            if(result.isFlag()){
                //存数据库  然后返回存储地址(这个还没做)
                result.setData(imageBaseUrl + filepath + newName);
                return result;
            }else {
                return result;
            }
        } catch (IOException e) {
            return new Result(false, StatusCode.ERROR,"FTP连接失败,请重试",null);
        }


    }

    @ResponseBody
    @ApiOperation(value="ftp文件列表", notes="ftp文件列表测试")
    @PostMapping(value = "fileList")
    public Result fileList() {
        return FTPUtil.fileList();
    }

    @ResponseBody
    @ApiOperation(value="下载文件", notes="文件下载测试")
    @PostMapping(value = "downFile")
    public Result downFile(@RequestParam("fileName") String fileName,@RequestParam("localPath") String localPath){
        return FTPUtil.downFile(fileName, localPath);
    }



}
