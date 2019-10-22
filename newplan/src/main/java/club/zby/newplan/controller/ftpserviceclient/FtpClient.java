package club.zby.newplan.controller.ftpserviceclient;

import club.zby.newplan.Interceptor.FeignInterceptor;
import club.zby.newplan.controller.financeclient.FinanceClientImp;
import club.zby.newplan.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/22 18:06
 */
@Component
@FeignClient(name = "ftp",fallback = FtpClientImp.class,configuration = {FeignInterceptor.class})//uereka中的注册服务名，configuration是feign的拦截器，这里重写了拦截规则
public interface FtpClient {

    @PostMapping(value = "FtpServer/upload")
    Result uploadPic(MultipartFile multipartFile);

    @PostMapping(value = "FtpServer/fileList")
    Result fileList();

    @PostMapping(value = "FtpServer/downFile")
    Result downFile(@RequestParam("fileName") String fileName, @RequestParam("localPath") String localPath);


}
