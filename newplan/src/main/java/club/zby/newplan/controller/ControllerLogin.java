package club.zby.newplan.controller;

import club.zby.newplan.Entity.Constants;
import club.zby.newplan.Entity.QQUserInfo;
import club.zby.newplan.Untlis.URLEncodeUtil;
import club.zby.newplan.config.JwtUtil;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import club.zby.newplan.service.LoginService;
import club.zby.newplan.service.QQService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private Constants constants;
    @Autowired
    private QQService qqService;

    /**
     * Login的主逻辑
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
        HashMap map = new HashMap();
        map.put("token",token);
        map.put("user",userinfo);
        response.setHeader("Authrorization","Bearer "+token);
        return new Result(true,StatusCode.OK,"登录成功",map);
    }

    /**
     * qq登录回调地址（处理回调逻辑，）
     * @param code
     * @return
     * @throws Exception
     */
    @GetMapping(value = "QQLogin")
    @ResponseBody
    public ModelAndView QQeLogin(String code, Model model) throws Exception {

        System.out.println(1);
        if (code == null) {
            return new ModelAndView("error");
        }
        //获取tocket
        System.out.println(2);
        Map<String, Object> qqProperties = qqService.getToken(code);
        //获取openId(每个用户的openId都是唯一不变的)
        String openId = qqService.getOpenId(qqProperties);
        //根据qqopenid查找是否存在该用户
        Result result = loginService.checkOpenId(openId,qqProperties);
        if(result.isFlag()){
            return new ModelAndView("view","data",result.getData());
        }else if((!result.isFlag()) && result.getData() == null) {
            return new ModelAndView("error");
        }else {
            return new ModelAndView("register","id",((User)result.getData()).getId());
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
        String contextPath = request.getContextPath();
        return new Result(true,StatusCode.OK,"test",contextPath);
    }

    /**
     * 跳转index
     * @param model
     * @return
     */
    @GetMapping(value = "index")
    public String index(Model model){
        // 拼接url
        model.addAttribute("url", qqService.getCode());
        return "index";
    }

    /**
     * 跳转登录
     * @param model
     * @return
     */
    @GetMapping(value = "login")
    public String login(Model model){
        // 拼接url
        model.addAttribute("url", qqService.getCode());
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
