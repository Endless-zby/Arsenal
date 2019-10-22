package club.zby.newplan.controller.ftpserviceclient;

import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
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

/**
 * @Author: 赵博雅
 * @Date: 2019/10/22 18:05
 */
@Api(value = "ftp")
@Controller
@RequestMapping(value = "FtpHandle")
public class ControllerFtp {

    @Autowired
    private FtpClient ftpClient;
    @Autowired
    private HttpServletRequest request;


    @ResponseBody
    @ApiOperation(value="ftp文件上传", notes="ftp文件上传测试")
    @PostMapping(value = "upload")
    public Result uploadPic(MultipartFile multipartFile) {
        String status = (String) request.getAttribute("status");
        System.out.println(status);
        if("404".equals(status) || status == null){
            return new Result(false, StatusCode.LOGINERROR,"登录异常",null);
        }
        return ftpClient.uploadPic(multipartFile);
    }

    @ResponseBody
    @ApiOperation(value="ftp文件列表", notes="ftp文件列表测试")
    @PostMapping(value = "fileList")
    public Result fileList() {
        return ftpClient.fileList();
    }

    @ResponseBody
    @ApiOperation(value="下载文件", notes="文件下载测试")
    @PostMapping(value = "downFile")
    public Result downFile(@RequestParam("fileName") String fileName, @RequestParam("localPath") String localPath){
        String status = (String) request.getAttribute("status");
        System.out.println(status);
        if("404".equals(status) || status == null){
            return new Result(false, StatusCode.LOGINERROR,"登录异常",null);
        }
        return ftpClient.downFile(fileName,localPath);
    }







}
