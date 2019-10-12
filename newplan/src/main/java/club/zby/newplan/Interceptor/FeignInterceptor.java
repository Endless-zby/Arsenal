package club.zby.newplan.Interceptor;

import feign.RequestTemplate;
import feign.template.HeaderTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import feign.RequestInterceptor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/11 18:03
 */
@Configuration
public class FeignInterceptor implements feign.RequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void apply(RequestTemplate template) {
        template.header("aaa","8888");
//        System.out.println("*****************");
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        System.out.println(0);
//        HttpServletRequest request = attributes.getRequest();
//
//        System.out.println((String) request.getAttribute("userid"));
//
//        Enumeration<String> headerNames = request.getHeaderNames();
//        System.out.println(1);
//        if (headerNames != null) {
//            System.out.println(2);
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                String values = request.getHeader(name);
//                template.header(name, values);
//            }
//            logger.info("feign interceptor header:{}",template);
//        }
//        System.out.println(3);

    }
}
