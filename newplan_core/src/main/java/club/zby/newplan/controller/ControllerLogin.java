package club.zby.newplan.controller;

import club.zby.newplan.Entity.User;
import club.zby.newplan.config.JwtUtil;
import club.zby.newplan.result.Result;
import club.zby.newplan.service.LoginService;
import club.zby.newplan.service.QQService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @ApiOperation(value="登录", notes="输入用户名和密码")
    @PostMapping("login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println(username);
        Result result = loginService.login(username,password);
        if(!result.isFlag()){
            return result;
        }
        User userinfo = (User) result.getData();
        String token = jwtUtil.creatJWT(userinfo.getId(), userinfo.getUsername(), String.valueOf(userinfo.getType()));
        System.out.println(token);
        result.setMessage(token);
        System.out.println(result);
        return result;
    }

    /**
     * qq登录回调地址（处理回调逻辑）
     * 有：直接登录  没有：注册后直接登录
     * @param code
     * @return
     * @throws Exception
     */
    @GetMapping(value = "QQLogin")
    @ResponseBody
    public ModelAndView QQLogin(String code) throws Exception {

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
            System.out.println(token);
            result.setMessage(token);
            return new ModelAndView("view2", "result", result);
        }else {
            return new ModelAndView("login");
        }
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
