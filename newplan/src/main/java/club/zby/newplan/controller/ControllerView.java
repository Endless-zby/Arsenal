package club.zby.newplan.controller;

import club.zby.newplan.Entity.User;
import club.zby.newplan.config.JwtUtil;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import club.zby.newplan.service.LoginService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "pages")
public class ControllerView {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private LoginService loginService;

    /**
     * 主页
     * @param userid
     * @return
     */
    @GetMapping(value = "mainview/{userid}")
    public ModelAndView viewIndex(@PathVariable("userid") String userid){
        User user = loginService.userInfo(userid);
        return new ModelAndView("view2", "result", new Result(true,StatusCode.OK,"查询成功",user));
    }

    /**
     * 测试主页2
     * @param request
     * @return
     */
    @GetMapping(value = "view2")
    public String view2(HttpServletRequest request){
        return "view2";
    }

    /**
     * 发快递
     * 权限：所有人都可以使用该功能，不需要校验权限
     * @param request
     * @return
     */
    @GetMapping(value = "express")
    public String express(HttpServletRequest request){
        return "express";
    }

    /**
     * 查询快递
     * 目前测试阶段需要权限，用来证明用户已经登录
     * @return
     */
    @GetMapping(value = "SelectExpress")
    public String SelectExpress(){
        return "SelectExpress";
    }



    @GetMapping(value = "FinanceList")
    public String er(HttpServletRequest request){
        return "FinanceList";
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
        //abcdsfdfs
        if (bearer_ != null &&  bearer_.startsWith("Bearer ")) {
            //获取token
            String token = bearer_.substring(7);
            //解析token

            Claims claims = jwtUtil.parseJwt(token);
            if(claims != null ){
                //管理员
                if("1".equals(claims.get("roles"))   )      {
                    return new Result(true, StatusCode.OK,"admin_claims",bearer_);
                    //普通
                }else if( "0".equals(claims.get("roles")) ){
                    return new Result(true,StatusCode.OK,"user_claims",bearer_);
                }else
                {
                    throw new RuntimeException("角色有误！") ;
                }
            }
        }
        return new Result(true,StatusCode.OK,"没登陆啊",bearer_);
    }

}
