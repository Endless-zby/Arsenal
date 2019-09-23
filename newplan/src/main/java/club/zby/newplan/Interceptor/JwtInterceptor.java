package club.zby.newplan.Interceptor;


import club.zby.newplan.config.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    //jwt
    @Autowired
    private JwtUtil jwtUtil ;

    //处理权限问题：给request请求贴角色标签 （admin或user）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截校验");
        //前缀 + token
        String authrorization = request.getHeader("Authrorization");
        System.out.println("拦截器中的token打印："+authrorization);
        //abcdsfdfs
        if (authrorization != null &&  authrorization.startsWith("Bearer ")) {
            //获取token
            String token = authrorization.substring(7);
            //解析token
            Claims claims = jwtUtil.parseJwt(token);
            if(claims != null ){
                //管理员
               if("1".equals(claims.get("roles"))   )      {
                   request.setAttribute("admin_claims" ,claims );
                   request.setAttribute("userid",claims.get("jti"));
                   System.out.println("拦截器中的info打印："+claims +"+++" + claims.get("iss"));
                //普通
               }else if( "0".equals(claims.get("roles")) ){
                   request.setAttribute("user_claims" ,claims );
                   request.setAttribute("userid",claims.get("jti"));
                   System.out.println("拦截器中的info打印："+claims +"+++" + claims.get("iss"));
               }else {
                    throw new RuntimeException("角色有误！") ;
               }
                request.setAttribute("status" ,"200" );
            }
            return true;
        }
        request.setAttribute("status" ,"404" );
        return true;//放行
    }
}
