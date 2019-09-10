package club.zby.newplan.interceptor;


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
        System.out.println("拦截器...");
        //前缀 + token
        String authrorization = request.getHeader("Authrorization");
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
                //普通
               }else if( "0".equals(claims.get("roles")) ){
                   request.setAttribute("user_claims" ,claims );
               }else
               {
                    throw new RuntimeException("角色有误！") ;
               }
            }
            return true ;
        }

        return true ;//放行
    }
}
