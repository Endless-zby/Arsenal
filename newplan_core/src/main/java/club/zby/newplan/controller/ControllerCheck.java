package club.zby.newplan.controller;

import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import club.zby.newplan.service.CheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public Result smsCheck(@PathVariable("phone") String phone){
        return checkService.smsService(phone);
    }

    @ResponseBody
    @ApiOperation(value="验证码验证", notes="接受验证码，在此验证")
    @GetMapping("smscheck/phone")
    public Result smsCheckOut(@RequestParam("phone") String phone, @RequestParam("phonecode") String phonecode, HttpServletRequest request){


        StringBuffer url = request.getRequestURL();
        String userid = (String)request.getAttribute("userid");

        System.out.println("用户id：" + userid);
        System.out.println("url：" + url.toString());
        if(checkService.smsCheckOut(phone, phonecode, userid)){
            return new Result(true,StatusCode.OK,"更新成功",null);
        }
        return new Result(false,StatusCode.ERROR,"更新失败",null);

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


