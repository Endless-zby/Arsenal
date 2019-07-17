package com.zby.userzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zby.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 赵博雅
 * @Date: 2019 06 21 2019/6/21 9:48
 */


@Component
public class Zuul extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;


    //通过返回值指定过滤器类型：post  、pre
//    pre: 可以在请求被路由之前调用
//    routing: 在路由请求时候被调用
//    post: 在routing和error过滤器之后被调用
//    error: 处理请求时发生错误时被调用
    public String filterType() {
        return "pre";//在处理请求之前 拦截处理
    }

    //如果有多个过滤器，当前过滤器的执行顺序  0>1>2
    public int filterOrder() {
        return 0;
    }

    //是否开启本过滤器
    public boolean shouldFilter() {
        return true;
    }

    /*
        权限校验的逻辑：
            如果已登录，则可以在Header中找到Authrorization；否则 没登录，校验失败

     */
    //注意：过滤将请求拦截后 是否放行，与返回值无关。
    //return  任意值 ，都代表 放行；
    //如果要终止请求： requestContext.setSendZuulResponse(false);
    public Object run() throws ZuulException {
        System.out.println("UserZuul过滤器...");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String authrorization = request.getHeader("Authrorization");
        System.out.println("token：" + authrorization);
        //如果有Authrorization，则实际进行校验
        // 如果是登录请求（请求地址中 包含login字段），则不需要拦截；如果不是登录请求，则进行 登录校验
        String url = request.getRequestURL().toString();
        System.out.println("目标地址：" + url);
        if (url.contains("user/hello")) {
            System.out.println("登录页面");
            return null;
        }

        //如果访问的是网关，则也不需要进行权限校验；网关的方法名就是“OPTIONS”
        if ("OPTIONS".equals(request.getMethod())) {
            System.out.println("访问的是网关");
            return null;
        }

        //试图通过网关请求真实的服务，因此需要真正的校验
        if (authrorization != null && authrorization.startsWith("Bearer ")) {
            //获取token
            String token = authrorization.substring(7);
            //解析token
            System.out.println(token);
            Claims claims = jwtUtil.parseJwt(token);
            if (claims != null) {
                //判断角色是否是User，如果是 则放行(return null)
                //用户类型    admin : 1      user : 0
                if ("0".equals(claims.get("roles"))) {
                    System.out.println("用户");
                    // request.setAttribute("user_claims" ,claims );
                    //符合user角色
                    System.out.println("符合user....");
                    //将Header信息传递到 其他具体的微服务 （header信息只能使用一次，在经过Zuul时已经被使用了。如果要传递给其他服务，必须再将它手工传递一次）
                    requestContext.addZuulRequestHeader("Authrorization",authrorization);
                    return null;
                }
                System.out.println("管理员");
            }
            //终止请求
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(444);//请求错误的状态码
            requestContext.setResponseBody("当前请求不是User权限！");//提示文字
            requestContext.getResponse().setContentType("text/html;charset=utf-8");

            return null;
        }
        return null;
    }
}

