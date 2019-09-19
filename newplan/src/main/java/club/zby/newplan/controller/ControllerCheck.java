package club.zby.newplan.controller;

import club.zby.newplan.service.CheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Api(value = "checkmethod")
@Controller
@RequestMapping(value = "check")
public class ControllerCheck {

    @Autowired
    private CheckService checkService;

    /**
     * 邮箱验证
     * @param id
     * @return
     */

    @GetMapping("email/{id}")
    public String emailClack(@PathVariable("id") String id){
        boolean status = checkService.emailClack(id);
        if(status){
        return "registerSuccess";
        }
        return "registerError";
    }

    /**
     * 手机验证
     * @param phone
     */
    @ResponseBody
    @ApiOperation(value="短信验证", notes="发送短信，redis缓存，默认过期时间为五分钟")
    @GetMapping("sms/{phone}")
    public void smsCheck(@PathVariable("phone") String phone, HttpServletResponse response){
        PrintWriter writer = null;
        try {
            checkService.smsService(phone);
            writer = response.getWriter();
            writer.print(true);
        } catch (IOException e) {
            e.printStackTrace();
            writer.print(false);
        }

    }

    @ResponseBody
    @ApiOperation(value="用户名重复校验", notes="用户名输入框取消焦点后立即校验是否合法可用")
    @GetMapping("username/{username}")
    public void username(@PathVariable("username") String username, HttpServletResponse response) throws IOException {
        boolean onclock = checkService.onclock(username);
        System.out.println(onclock);
        PrintWriter writer = response.getWriter();
        writer.print(onclock);
    }
}


