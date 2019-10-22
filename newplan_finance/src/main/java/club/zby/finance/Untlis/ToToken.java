package club.zby.finance.Untlis;

import club.zby.finance.Config.JwtUtil;
import club.zby.finance.Config.Result;
import club.zby.finance.Config.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/22 9:29
 */
@Component
public class ToToken {

    @Autowired
    private JwtUtil jwtUtil;

    public Result parseToken(String token){

        if (token != null && token.startsWith("Bearer ")) {
            System.out.println("取出转发的heard：" + token);
            Claims claims = jwtUtil.parseJwt(token.substring(7));
            if (claims != null) {
                //管理员
                if ("1".equals(claims.get("roles")) || "0".equals(claims.get("roles"))) {
                    String userid = (String) claims.get("jti");
                    System.out.println("userid：" + userid);
                    return new Result(true, StatusCode.OK, "userid获取成功", userid);
                } else {
                    return new Result(false, StatusCode.REMOTEERROR, "权限校验失败！", null);
                }
            } else {
                return new Result(false, StatusCode.LOGINERROR, "身份失效，请重新登录！", null);
            }
        }else {
            return new Result(false, StatusCode.ERROR, "Finance服务未连接，请联系管理员！", null);
        }


    }

}
