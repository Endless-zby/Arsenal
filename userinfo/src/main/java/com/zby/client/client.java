package com.zby.client;

import com.zby.client.clientImpl.clientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 赵博雅
 * @Date: 2019/7/5 10:34
 */
@Component
@FeignClient(name = "armstype",fallback = clientImpl.class)//uereka中的注册服务名，
public interface client {

    @GetMapping(value = "arsenal/view")//原方法执行路径
    ModelAndView view(HttpServletRequest request);

    @GetMapping("arsenal/test")
    String test();
}
