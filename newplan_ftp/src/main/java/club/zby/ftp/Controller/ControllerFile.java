package club.zby.ftp.Controller;

import club.zby.commen.Config.Result;
import club.zby.commen.Config.StatusCode;
import club.zby.ftp.Entity.FileInfo;
import club.zby.ftp.Entity.Progress;
import club.zby.ftp.Service.DbService;
import club.zby.ftp.Service.FileService;
import club.zby.ftp.Untlis.ToToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/22 14:01
 */
@Api(value = "FTP")
@Controller
@RequestMapping(value = "FtpServer")
public class ControllerFile {
    private static final long serialVersionUID = -6783491256532300522L;

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

//        String token = request.getHeader("Authrorization");
//        Result result = toToken.parseToken(token);
//        String userid = (String) result.getData();
//        if(userid != null){
//            return fileService.uploadPic(multipartFile, userid);
//        }
//        return result;
        return fileService.uploadPic(multipartFile, "66341505371082752");
    }


    @CrossOrigin(origins = "*")
    @ResponseBody
    @ApiOperation(value="ftp文件上传--上传进度", notes="ftp文件上传测试--上传进度测试")
    @GetMapping(value = "size")
    public Result getProgress(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        Object percent = session.getAttribute("upload_percent");
//        System.out.println("输出" + (Integer) percent);
//        Integer i = null != percent ? (Integer) percent : 0;
//        System.out.println(i);
//        return new Result(true,StatusCode.OK,"测试",i);


        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Object is_begin = session.getAttribute("UPLOAD_PERCENTAGE");
        if (is_begin == null)
            return new Result(false,StatusCode.ERROR,"测试",null);
        if ("0".equals(is_begin.toString()))
            return new Result(false,StatusCode.ERROR,"测试",null);

        Object upload_percentage = session.getAttribute("UPLOAD_PERCENTAGE");
        System.out.println("进度：" + upload_percentage.toString());
        return new Result(true,StatusCode.OK,"测试",upload_percentage.toString());
    }


    @ResponseBody
    @ApiOperation(value="ftp文件列表", notes="ftp文件列表测试")
    @PostMapping(value = "fileList")
    public Result fileList() {
        return fileService.fileList();
    }


    @ResponseBody
    @ApiOperation(value="数据库文件列表", notes="数据库文件列表测试")
    @GetMapping(value = "basefileList/{page}")
    public Result basefileList(@PathVariable("page") Integer page) {
        Page<FileInfo> fileInfos = dbService.basefileList(page);
        return new Result(true,StatusCode.OK,"成功",fileInfos);
    }


    @ResponseBody
    @ApiOperation(value="下载文件", notes="文件下载测试")
    @GetMapping(value = "downFile")
    public Result downFile(@RequestParam("fileName") String fileName,@RequestParam("localPath") String localPath){

        return fileService.downFile(fileName, localPath);

    }

    @ResponseBody
    @ApiOperation(value="删除文件", notes="文件删除测试")
    @GetMapping(value = "delFile")
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
