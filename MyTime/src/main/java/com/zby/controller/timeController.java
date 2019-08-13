package com.zby.controller;

import com.zby.config.Result;
import com.zby.entity.Red;
import com.zby.entity.myTime;
import com.zby.entity.ourFinance;
import com.zby.service.financeService;
import com.zby.service.redService;
import com.zby.service.timeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/9 9:55
 */

@Controller
@RequestMapping("time")
public class timeController {


    @Autowired
    private timeService timeservice;
    @Autowired
    private financeService financeService;
    @Autowired
    private redService redservice;
    @Value("${page.size}")
    private int size;



    @GetMapping("index")
    public String index(){
        return "index";
    }
    @GetMapping("sleep/{page}")
    public ModelAndView sleep(@PathVariable(value = "page") Integer page){
        if (page == 0 || page == null ){
            page = 1;
        }
        Slice<myTime> splinedate = timeservice.splinedate1(page,size);

        Result result;
        if(splinedate.getContent().size() == 0){
            result = new Result(false, 20001, "查询失败", null);
        }else{
            result = new Result(true, 20000, "查询成功", splinedate.getContent());
        }
        ModelAndView modelAndView = new ModelAndView("sleep");
        modelAndView.addObject(result);
        modelAndView.addObject("indexPage",splinedate.getNumber() + 1);
        modelAndView.addObject("totalPage",((Page<myTime>) splinedate).getTotalPages());
        return modelAndView;
    }
    @GetMapping("spline")
    public String spline(){
        return "spline";
    }

    @GetMapping("showfinance")
    public String showfinance(){
        return "showfinance";
    }

    @GetMapping("savefinance/{page}")
    public ModelAndView savefinance(@PathVariable(value = "page") Integer page){
        if (page == 0 || page == null ){
            page = 1;
        }
        Slice<ourFinance> queryfinance = financeService.queryfinance1(page,size);
        Result result;
        if(queryfinance.getContent().size() == 0){
            result = new Result(false, 20001, "查询失败", null);
        }else{
            result = new Result(true, 20000, "查询成功", queryfinance.getContent());
        }
        ModelAndView modelAndView = new ModelAndView("savefinance");
        modelAndView.addObject(result);
        modelAndView.addObject("indexPage",queryfinance.getNumber() + 1);
        modelAndView.addObject("totalPage",((Page<ourFinance>) queryfinance).getTotalPages());
        return modelAndView;
    }


    /**
     * 保存睡眠时间
     * @param mytime
     * @return
     */
    @ResponseBody
    @PostMapping("submit")
    public Result register(@RequestBody myTime mytime){


        myTime savedate = timeservice.savedate(mytime);

        return new Result(true,20000,"保存成功",savedate);
    }

    /**
     * 图表表示睡眠时间
     * @return
     */
    @ResponseBody
    @PostMapping("splinedate")
    public Result register(){

        List<myTime> splinedate = timeservice.splinedate();

        System.out.println(splinedate.toString());

        return new Result(true,20000,"查询成功",splinedate);
    }

    /**
     * 月开销
     * @param ourfinance
     * @return
     */
    @ResponseBody
    @PostMapping("finance")
    public Result finance(@RequestBody ourFinance ourfinance){

        ourFinance finance = financeService.finance(ourfinance);

        return new Result(true,20000,"保存成功",finance);
    }

    /**
     * 月开销统计
     * @return
     */
    @ResponseBody
    @PostMapping("queryfinance")
    public Result queryfinance(){

        List<ourFinance> queryfinance = financeService.queryfinance();

        return new Result(true,20000,"查询成功",queryfinance);

    }

    /**
     * yuejin
     * @param red
     * @return
     */
    @ResponseBody
    @PostMapping("red")
    public Result red(@RequestBody Red red){

        Red reds = redservice.red(red);

        return new Result(true,20000,"保存成功",reds);

    }

    /**
     * yuejintime
     * @return
     */
    @ResponseBody
    @PostMapping("queryred")
    public Result queryred(){

        List<Red> queryred = redservice.queryred();

        return new Result(true,20000,"查询成功",queryred);

    }






}
