package club.zby.newplan.controller;


import club.zby.newplan.EmailTemplate.EmailTemplate;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "测试注册环节，生成验证码，发送，确认，完成")
@Controller
@RequestMapping(value = "user")
public class ControllerRegister {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private final static Logger logger = LoggerFactory.getLogger(EmailTemplate.class);


    @ResponseBody
    @ApiOperation(value="注册数据", notes="验证邮箱或者电话")
    @PostMapping(value = "register/{smsCode}",produces = "application/json;charset=utf-8")
    public Result register(@RequestBody User user,@PathVariable("smsCode") String smsCode){
        logger.debug("注册开始");
        Result register = registerService.register(user, smsCode);
        if (register.isFlag()){
            return register;
        }
        return register;
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
