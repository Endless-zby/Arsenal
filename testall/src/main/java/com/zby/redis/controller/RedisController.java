package com.zby.redis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @Author: 赵博雅
 * @Date: 2019/8/1 16:57
 */

@Controller
@RequestMapping("redis")
@Api(value = "Redis之opsForValue")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @ResponseBody
    @ApiOperation(value="测试Set和Get", notes="新增一个字符串类型的值，key是键，value是值")
    @GetMapping(value = "testSetAndGet",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "键",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "value", value = "值",
                    required = true, dataType = "String")
    })
    public String testSet(String key, String value){
        //set
        redisTemplate.opsForValue().set(key,value);
        //get
        String val = (String) redisTemplate.opsForValue().get(key);

        return "redis中get到的值：" + val;
    }

    @ResponseBody
    @ApiOperation(value="测试append", notes="在原有的值基础上新增字符串到末尾")
    @GetMapping(value = "testappend",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "键",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "value", value = "需要拼接的字符",
                    required = true, dataType = "String")
    })
    public String testAppend(String key, String value){
        //append
        redisTemplate.opsForValue().append(key,value);
        //get
        String val = (String) redisTemplate.opsForValue().get(key);

        return key + "的值拼接后的新值：" + val;
    }

    @ResponseBody
    @ApiOperation(value="测试Get(自定义)", notes="截取key键对应值得字符串，从开始下标位置开始到结束下标的位置(包含结束下标)的字符串")
    @GetMapping(value = "testGet",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "键",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "start", value = "开始下标",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "end", value = "结束下标",
                    required = true, dataType = "Long")
    })
    public String testGet(String key, Long start, Long end){
        //get
        String val = redisTemplate.opsForValue().get(key,start,end);

        return key + "截取后的值：" + val;
    }

    @ResponseBody
    @ApiOperation(value="测试GetAndSet", notes="获取原来key键对应的值并重新赋新值。")
    @GetMapping(value = "GetAndSet",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "原来的key键",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "newValue", value = "新的值",
                    required = true, dataType = "String")
    })
    public String GetAndSet(String key, String newValue){
        //getAndSet
        redisTemplate.opsForValue().getAndSet(key,newValue);
        //get
        String val = (String) redisTemplate.opsForValue().get(key);

        return key + "修改后的新值：" + val;
    }

    @ResponseBody
    @ApiOperation(value="测试size", notes="获取指定字符串的长度")
    @GetMapping(value = "size",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "键",
                    required = true, dataType = "String")
    })
    public String size(String key){
        //size
        Long size = redisTemplate.opsForValue().size(key);

        return key + "的长度：" + size;
    }

    @ResponseBody
    @ApiOperation(value="测试Set(存在过期时间)", notes="设置变量值的过期时间(此方法的执行时间将由你输入的timeout和unit决定！建议使用TimeUnit.SECONDS)")
    @GetMapping(value = "setTime",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "键",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "value", value = "值",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeout", value = "时间",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "unit", value = "时间计量(时分秒),测试使用：TimeUnit.SECONDS",
                    required = true, dataType = "TimeUnit")
    })
    public String setTime(String key, String value, Long timeout, TimeUnit unit) throws InterruptedException {
        //set
        redisTemplate.opsForValue().set(key,value,timeout,unit);

        //立即查询
        String newvalue  = (String) redisTemplate.opsForValue().get(key);
        //等待失效
        // +1秒,防止网络延迟的问题
        Thread.sleep((timeout + 1) * 1000);
        //等待失效后查询
        String oldvalue  = (String) redisTemplate.opsForValue().get(key);

        return key + "在失效前的值：" + newvalue + "\n" + "失效后的值：" + oldvalue;

    }


    @ResponseBody
    @ApiOperation(value="测试multiSet", notes="设置map集合到redis")
    @GetMapping(value = "multiSet",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "map", value = "自定义map变量名(不可使用map1,代码变量已经占用！)",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name", value = "姓名",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "age", value = "年龄",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "sex", value = "性别",
                    required = true, dataType = "String")
    })
    public String multiSet(String map ,String name, String age, String sex) {

        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("name",name);
        map1.put("age",age);
        map1.put("sex",sex);
        redisTemplate.opsForHash().putAll(map,map1);

        return "储存完成，请在multiGet方法中使用"+map+"调用查看验证！";
    }

    @ResponseBody
    @ApiOperation(value="测试multiGet", notes="根据集合取出对应的value值")
    @GetMapping(value = "multiGet",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "map", value = "map变量名，默认使用'map'",
                    required = true, dataType = "String")
    })
    public String multiGet(String map) {

        Map entries = redisTemplate.opsForHash().entries(map);

        String name =  (String)entries.get("name");
        String age =  (String) entries.get("age");
        String sex =  (String)entries.get("sex");

        return "获取map中的数据："+"\n"+"姓名："+name+"\n"+"年龄：" + age+"\n"+"性别：" + sex;
    }

    @ResponseBody
    @ApiOperation(value="测试leftPush储存list", notes="保存list到redis")
    @GetMapping(value = "leftPush",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "list", value = "自定义list变量名(不可使用list1,代码变量已经占用！)",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name1", value = "姓名1",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name2", value = "姓名2",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name3", value = "姓名3",
                    required = true, dataType = "String")
    })
    public String leftPushSet(String list ,String name1, String name2, String name3) {

        ArrayList<String> list1 = new ArrayList<>();
        list1.add(name1);
        list1.add(name2);
        list1.add(name3);
        redisTemplate.opsForList().leftPush(list,list1);
        return "储存完成，请在leftPop方法中使用"+list+"调用查看验证！";
    }

    @ResponseBody
    @ApiOperation(value="测试leftPop", notes="根据集合取出对应的value值")
    @GetMapping(value = "leftPop",produces="text/plain;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "list", value = "list变量名，默认使用'list'",
                    required = true, dataType = "String")
    })
    public String leftPop(String list) {

        List<String> resultList1=(List<String>)redisTemplate.opsForList().leftPop(list);



        return list+"数组中的数据为：" + resultList1+"\n长度为："+resultList1.size();
    }




}
