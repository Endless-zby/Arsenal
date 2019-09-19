package club.zby.newplan.controller;

import club.zby.newplan.config.JwtUtil;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import club.zby.newplan.service.LoginService;
import club.zby.newplan.service.QQService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "user",produces = "application/json;charset=utf-8")
public class ControllerLogin {

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private QQService qqService;

    /**
     * Login账号的主逻辑
     * @param user
     * @param response
     * @return
     */
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
        System.out.println(token);
        response.setHeader("Authrorization","Bearer "+token);
        result.setData(token);
        return result;
    }

    /**
     * qq登录回调地址（处理回调逻辑，）
     * 有：直接登录  没有：注册后直接登录
     * @param code
     * @return
     * @throws Exception
     */
    @GetMapping(value = "QQLogin")
    @ResponseBody
    public ModelAndView QQLogin(String code,HttpServletResponse response) throws Exception {

        System.out.println(1);
        if (code == null) {
            return new ModelAndView("error");
        }
        //获取tocket
        Map<String, Object> qqProperties = qqService.getToken(code);
        //获取openId(每个用户的openId都是唯一不变的)
        String openId = qqService.getOpenId(qqProperties);
        //根据qqopenid查找是否存在该用户
        Result result = loginService.checkOpenId(openId,qqProperties);
        if(result.isFlag()) {
            User user = (User)result.getData();
            String token = jwtUtil.creatJWT(user.getId(), user.getUsername(), String.valueOf(user.getType()));
            response.setHeader("Authrorization","Bearer "+token);
            System.out.println(token);
            return new ModelAndView("view", "result", result);
        }else {
            return new ModelAndView("login");
        }
    }

    /**
     * 测试token
     * @param request
     * @return
     */
    @ResponseBody
    @ApiOperation(value="登陆后测试token", notes="查看浏览器中保存的token")
    @GetMapping(value = "test",produces = "application/json;charset=utf-8")
    public Result test(HttpServletRequest request){
        String bearer_ = request.getHeader("Authrorization");
        System.out.println("获取到的token：" + bearer_);
        return new Result(true,StatusCode.OK,"test",bearer_);
    }

    /**
     * 页面跳转后的预加载
     * @param response
     */
    @GetMapping(value = "getCode")
    public void getCode(HttpServletResponse response) {
        //三方登录状态默认为false
        String code = qqService.getCode();
        try {
            PrintWriter writer = response.getWriter();
            writer.println(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转index
     * @return
     */
    @GetMapping(value = "index")
    public String index(){
        return "index";
    }

    /**
     * 跳转login
     * @return
     */
    @GetMapping(value = "login")
    public String login(){
        return "login";
    }

    /**
     * 跳转忘记密码
     * @return
     */
    @GetMapping(value = "newPassword")
    public String newPassword(){
        return "newPassword";
    }


}
