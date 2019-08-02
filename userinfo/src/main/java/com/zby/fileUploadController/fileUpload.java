package com.zby.fileUploadController;

import com.zby.entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
@RequestMapping("/FileController")
public class fileUpload {

    @Value("${file.url}")
    private String url;


    //上传报表
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public void uploadDash(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam("file") MultipartFile  uploadFiles) throws IOException {

        if (!uploadFiles.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(uploadFiles.getOriginalFilename())));
                out.write(uploadFiles.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Result result = new Result(false, 20001, "上传失败", uploadFiles.getName());
                PrintWriter out = response.getWriter();
                out.print(result);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();

            }

            Result result = new Result(true, 20000, "上传成功", uploadFiles.getName());
            PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
            out.close();

        } else {

        }


    }
}

