package club.zby.newplan.controller.ftpserviceclient;

import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/22 18:06
 */
@Component
public class FtpClientImp implements FtpClient {

   @Override
    public Result uploadPic(MultipartFile multipartFile){
        return new Result(false, StatusCode.RESERROR, "网络开小差了，请重试", null);
    }

    @Override
    public Result getProgress() {
        return new Result(false, StatusCode.RESERROR, "网络开小差了，请重试", null);
    }

    @Override
    public Result fileList(){
        return new Result(false, StatusCode.RESERROR, "网络开小差了，请重试", null);
    }

    @Override
    public Result basefileList(Integer page) {
        return new Result(false, StatusCode.RESERROR, "网络开小差了，请重试", null);
    }

    @Override
    public Result downFile(@RequestParam("fileId") String fileId, @RequestParam("localPath") String localPath){
        return new Result(false, StatusCode.RESERROR, "网络开小差了，请重试", null);
    }

    @Override
    public Result deleteFile(String fileId) {
        return new Result(false, StatusCode.RESERROR, "网络开小差了，请重试", null);
    }


}
