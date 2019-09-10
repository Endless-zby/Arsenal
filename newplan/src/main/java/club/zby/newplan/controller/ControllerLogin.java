package club.zby.newplan.controller;

import club.zby.newplan.config.JwtUtil;
import club.zby.newplan.entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import club.zby.newplan.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;

@Controller
@RequestMapping(value = "user",produces = "application/json;charset=utf-8")
public class ControllerLogin {

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtil jwtUtil;


    @ResponseBody
    @ApiOperation(value="登录", notes="输入用户名和密码")
    @PostMapping(value = "login",produces = "application/json;charset=utf-8")
    public Result login(@RequestBody User user, HttpServletResponse response){
        Result result = loginService.login(user);
        if(!result.isFlag()){
            return result;
        }
        User userinfo = (User) result.getData();

        String token = jwtUtil.creatJWT(userinfo.getId(), userinfo.getUsername(), String.valueOf(userinfo.getType()));
        HashMap map = new HashMap();
        map.put("token",token);
        map.put("user",userinfo);
        response.setHeader("Authrorization","Bearer "+token);
        return new Result(true,StatusCode.OK,"登录成功",map);
    }

    @ResponseBody
    @ApiOperation(value="登陆后测试token", notes="查看浏览器中保存的token")
    @GetMapping(value = "test",produces = "application/json;charset=utf-8")
    public Result test(HttpServletRequest request){
        String bearer_ = request.getHeader("Authrorization");
        String contextPath = request.getContextPath();
        return new Result(true,StatusCode.OK,"test",contextPath);
    }


}
