package club.zby.newplan.controller;

import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import club.zby.newplan.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * 更新个人信息
     * @param user
     * @return
     */
    @ResponseBody
    @ApiOperation(value="更新个人信息", notes="更新个人信息")
    @GetMapping("ipdatainfo")
    public Result UpDataInfo(@RequestBody User user){
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false, StatusCode.LOGINERROR,"请先登录后操作",null);
        }
        String userid = (String)request.getAttribute("userid");
        user.setId(userid);
        User users = userService.UpDataInfo(user);
        return new Result(true,StatusCode.OK,"success",users);
    }
}
