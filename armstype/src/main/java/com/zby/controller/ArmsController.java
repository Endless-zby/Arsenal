package com.zby.controller;

import com.zby.entity.Result;
import com.zby.entity.Arms;
import com.zby.service.ArmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

//@RefreshScope
@Controller
@RequestMapping("arsenal")
public class ArmsController {

    @Autowired
    private ArmsService armsservice;

    /**
     * 武器库分类展示（分类方式：用途）
     * @return
     */
    @GetMapping("view")
    public ModelAndView view(HttpServletRequest request){
        HttpSession session= request.getSession();
        List<Arms> initialization = armsservice.Initialization();

        HashMap<String, Object> stringListHashMap = new HashMap<>();

        stringListHashMap.put("type",initialization);
        stringListHashMap.put("username",session.getAttribute("username"));

        Result result = new Result(true, 20000, "成功", stringListHashMap);

        return new ModelAndView("show_arsenal","result",result);
    }


    @ResponseBody
    @GetMapping("test")
    public String test(){

        return "12345";
    }
}
