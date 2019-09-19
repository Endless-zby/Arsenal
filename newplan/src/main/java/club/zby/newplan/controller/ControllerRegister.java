package club.zby.newplan.controller;


import club.zby.newplan.EmailTemplate.EmailTemplate;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.service.QQService;
import club.zby.newplan.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Api(value = "测试注册环节，生成验证码，发送，确认，完成")
@Controller
@RequestMapping(value = "user")
public class ControllerRegister {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private QQService qqService;
    @Autowired
    private final static Logger logger = LoggerFactory.getLogger(EmailTemplate.class);


    @ResponseBody
    @ApiOperation(value="注册数据", notes="验证邮箱或者电话")
    @PostMapping(value = "register/{smsCode}",produces = "application/json;charset=utf-8")
    public Result register(@RequestBody User user, @PathVariable("smsCode") String smsCode, HttpServletResponse response){
        logger.debug("注册开始");
        Result register = registerService.register(user, smsCode);
        if(register.isFlag()){
            return register;
        }else {
            return new Result(false,20001,"失败",null);
        }
    }

    /**
     * 跳转注册
     * @return
     */
    @GetMapping(value = "reg")
    public String reg(){
        return "register";
    }
}
