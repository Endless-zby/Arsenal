package com.zby.controller;

import com.zby.client.client;
import com.zby.entity.Result;

import com.zby.entity.User;
import com.zby.service.UserService;

import com.zby.util.IdWorker;
import com.zby.util.JwtUtil;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

//@RefreshScope注解的作用: 如果刷新了bean，那么下一次访问bean(即执行一个方法)时就会创建一个新实例。
@RefreshScope
@Controller
@RequestMapping("user")
@Api(value = "api测试接口")
public class UserController {

    @Autowired
    private UserService userservice;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private client client;

    @Value("${zby.name}")
    private String name;

    @Value("${zby.age}")
    private String age;

    @Value("${zby.sex}")
    private String sex;


    @GetMapping("hello")
    public String hello(){
        System.out.println(name);//测试config是否正常
        return "user_login";
    }
    @GetMapping("file")
    public String file(){

        return "file";
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
     * 登录
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
     * config测试方法
     * @return
     */
    @GetMapping("config")
    public ModelAndView Config(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name",name);
        map.put("age",age);
        map.put("sex",sex);
        Result result = new Result(true, 20000, "成功", map);
        return new ModelAndView("config_test","result",result);
    }



    @ResponseBody
    @ApiOperation(value="查询用户数据", notes="根据用户username查询用户数据")
    @GetMapping("apiTest")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "username", value = "用户名",
                              required = true, dataType = "String")
    })
    public Result apiTest(String username){

        User user = userservice.query(username);

        return new Result(true,20000,"查询成功",
                " | 用户ID：" +user.getId() + " | 邮箱：" +
                user.getEmail() + " | 联系电话：" +
                        user.getPhone() + " | 年龄：" +
                        user.getAge()
        );
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
