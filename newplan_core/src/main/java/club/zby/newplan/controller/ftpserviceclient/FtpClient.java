package club.zby.newplan.controller.ftpserviceclient;

import club.zby.newplan.Interceptor.FeignInterceptor;
import club.zby.newplan.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/22 18:06
 */
@Component
@FeignClient(name = "ftp",fallback = FtpClientImp.class,configuration = {FeignInterceptor.class})//uereka中的注册服务名，configuration是feign的拦截器，这里重写了拦截规则
public interface FtpClient {

    @PostMapping(value = "FtpServer/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result uploadPic(@RequestPart(value = "multipartFile") MultipartFile multipartFile);

    @GetMapping(value = "FtpServer/size")
    Result getProgress();

    @PostMapping(value = "FtpServer/fileList")
    Result fileList();

    @GetMapping(value = "FtpServer/basefileList/{page}")
    Result basefileList(@PathVariable("page") Integer page);

    @GetMapping(value = "FtpServer/downFile")
    Result downFile(@RequestParam("fileId") String fileId, @RequestParam("localPath") String localPath);

    @GetMapping(value = "FtpServer/delFile")
    public Result deleteFile(@RequestParam("fileId") String fileId);

}
