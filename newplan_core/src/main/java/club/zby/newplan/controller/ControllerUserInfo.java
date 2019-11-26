package club.zby.newplan.controller;

import club.zby.commen.Config.MyLogger;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import club.zby.newplan.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/22 16:31
 */
@Controller
@RequestMapping(value = "userinfo")
public class ControllerUserInfo {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${sms.time}")
    private int time;

    /**
     * 发送验证码
     * @param phone
     */
    @ResponseBody
    @ApiOperation(value="更改绑定手机号码的校验以及短信发送", notes="更改绑定手机号码的校验以及短信发送")
    @GetMapping("checkNewPhone/{phone}")
    public Result checkNewPhone(@PathVariable("phone") String phone){
        if(userService.existsByPhone(phone)){
            return new Result(false, StatusCode.PHONEERROR,"该号码已被注册，请更换号码",phone);
        }
        String smsCode = ((int)(Math.random()*9000)+1000) + "";
        MyLogger.log("手机号：" + phone + "-----------" + "验证码：" + smsCode);
        redisTemplate.opsForValue().set("sms_" + phone,smsCode, time, TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("smscode",smsCode);
        rabbitTemplate.convertAndSend("sms",map);
        return new Result(true,StatusCode.OK,"发送成功,有效期十分钟",phone);
    }



    /**
     * 个人页面
     * @return
     */
    @ResponseBody
    @ApiOperation(value="个人页面", notes="个人信息展示完善")
    @GetMapping("selfinfo")
    public Result UserInfo(){
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false, StatusCode.LOGINERROR,"请先登录后操作",null);
        }
        String userid = (String)request.getAttribute("userid");
        User user = userService.UserInfo(userid);
        return new Result(true,StatusCode.OK,"success",user);
    }

    /**
     * 更新个人信息（未修改绑定的手机号）
     * @param user
     * @return
     */
    @ResponseBody
    @ApiOperation(value="更新个人信息", notes="更新个人信息")
    @PostMapping("updatainfo")
    public Result UpDataInfo(@RequestBody User user){
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false, StatusCode.LOGINERROR,"请先登录后操作",null);
        }
        String userid = (String)request.getAttribute("userid");
        if(userService.existsById(userid)){
            user.setUpdatetime(new Date());
            user.setId(userid);
            int context = userService.UpDataInfo(user);
            if(context == 1){
                return new Result(true,StatusCode.OK,"修改成功",userid);
            }
        }
        return new Result(false,StatusCode.ERROR,"获取用户信息失败",userid);
    }

    /**
     * 更新个人信息（修改绑定的手机号）
     * @param user
     * @param sms
     * @return
     */
    @ResponseBody
    @ApiOperation(value="更新个人信息", notes="更新个人信息")
    @PostMapping("updatainfo/{sms}")
    public Result UpDataInfoSms(@RequestBody User user,@PathVariable("sms") String sms){
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false, StatusCode.LOGINERROR,"请先登录后操作",null);
        }
        String userid = (String)request.getAttribute("userid");
        String phonesms  = (String)redisTemplate.opsForValue().get("sms_" + user.getPhone());
        if(phonesms.equals(sms) && userService.existsById(userid)){
            user.setUpdatetime(new Date());
            user.setUsername(user.getPhone());
            user.setId(userid);
            int context = userService.updateUserInfoById(user);
            if(context == 1){
                return new Result(true,StatusCode.OK,"success",userid);
            }
        }
        return new Result(false,StatusCode.ERROR,"验证码不正确或已失效",user.getPhone());
    }
}
