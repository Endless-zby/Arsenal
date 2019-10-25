package club.zby.ftp.Controller;

import club.zby.commen.Config.Result;
import club.zby.commen.Config.StatusCode;
import club.zby.ftp.Service.DbService;
import club.zby.ftp.Service.FileService;
import club.zby.ftp.Untlis.ToToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    private FileService fileService;
    @Autowired
    private ToToken toToken;
    @Autowired
    private DbService dbService;




    @ResponseBody
    @ApiOperation(value="ftp文件上传", notes="ftp文件上传测试")
    @PostMapping(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result uploadPic(@RequestPart(value = "multipartFile") MultipartFile multipartFile) {

        String token = request.getHeader("Authrorization");
        Result result = toToken.parseToken(token);
        String userid = (String) result.getData();
        if(userid != null){
            return fileService.uploadPic(multipartFile, userid);
        }
        return result;
    }

    @ResponseBody
    @ApiOperation(value="ftp文件列表", notes="ftp文件列表测试")
    @PostMapping(value = "fileList")
    public Result fileList() {
        return fileService.fileList();
    }



    @ResponseBody
    @ApiOperation(value="下载文件", notes="文件下载测试")
    @PostMapping(value = "downFile")
    public Result downFile(@RequestParam("fileName") String fileName,@RequestParam("localPath") String localPath){

        String token = request.getHeader("Authrorization");
        Result result = toToken.parseToken(token);
        String userid = (String) result.getData();
        if(userid != null){
            return fileService.downFile(fileName, localPath);
        }
        return result;

    }

    @ResponseBody
    @ApiOperation(value="删除文件", notes="文件删除测试")
    @DeleteMapping(value = "delFile")
    public Result deleteFile(@RequestParam("fileName") String fileName){

        String token = request.getHeader("Authrorization");
        Result result = toToken.parseToken(token);
        String userid = (String) result.getData();
        if(userid != null){
            //判断该文件是否属于当前登录者
            int fileNum = dbService.findIdByFileName(fileName, userid);
            if(fileNum != 0){       //该文件属于当前用户
                //删除
                Result res = fileService.deleteFile(fileName);
                //更改数据库中对应的数据的状态  tag = 0 ---> tag = 1;
                if(res.isFlag() && dbService.deleteFile(fileName) != 0){
                    return res;
                }
            }
        }
        return result;

    }
}
