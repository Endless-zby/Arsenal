package com.zby.controller;

import com.zby.client.client;
import com.zby.entity.Result;

import com.zby.entity.User;
import com.zby.service.UserService;

import com.zby.util.IdWorker;
import com.zby.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@RefreshScope
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userservice;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private client client;


    @GetMapping("hello")
    public String hello(){

        return "user_login";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("register")
    public Result register(@RequestBody User user){

        user.setId(idWorker.nextId() + "");
        user.setActive(false);  //新账号都为不活跃

        String register = userservice.register(user);

        if (register != null || register != ""){
            return new Result(true,20000,"注册成功",register);
        }
        return new Result(false,20001,"注册失败","error");
    }

    /**
     *
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("login")
    public Result login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){

        User login = userservice.login(user);

        if (login != null){
            HttpSession session = request.getSession();
            session.setAttribute("username",login.getUsername());
            String token = jwtUtil.creatJWT(login.getId(), login.getUsername(), String.valueOf(login.getType()));

            System.out.println(token);

            return new Result(true,20000,"登录成功",login);
        }
        return new Result(false,20001,"登录失败","error");

    }

    /**
     * 跨服务调用
     * userinfo---->armstype
     */

    @ResponseBody
    @GetMapping(value = "view")
    public ModelAndView view (HttpServletRequest request){
        return client.view(request);
    }

    @ResponseBody
    @GetMapping(value = "test")
    public String test(){
        return client.test();
    }

}
