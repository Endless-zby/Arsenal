package com.zby.client.clientImpl;

import com.zby.client.client;
import com.zby.entity.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 赵博雅
 * @Date: 2019/7/16 15:58
 */
@Component
public class clientImpl implements client {

    public ModelAndView view(HttpServletRequest request) {
        Result result = new Result(false, 20004, "请求失败，服务器无响应,熔断机制启动保护", null);
        return new ModelAndView("404","result",result);
    }

    public String test() {
        return "请求失败，服务器无响应,熔断机制启动保护";
    }
}
