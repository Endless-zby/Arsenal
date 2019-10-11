package club.zby.newplan.Interceptor;

import org.springframework.context.annotation.Configuration;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/11 18:03
 */
@Configuration
public class RequestInterceptor extends RequestInterceptor {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Override
//    public void apply(RequestTemplate template) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                String values = request.getHeader(name);
//                template.header(name, values);
//
//            }
//            logger.info("feign interceptor header:{}",template);
//        }
//               /* Enumeration<String> bodyNames = request.getParameterNames();
//                StringBuffer body =new StringBuffer();
//                if (bodyNames != null) {
//                    while (bodyNames.hasMoreElements()) {
//                        String name = bodyNames.nextElement();
//                        String values = request.getParameter(name);
//                        body.append(name).append("=").append(values).append("&");
//                    }
//                }
//                if(body.length()!=0) {
//                    body.deleteCharAt(body.length()-1);
//                    template.body(body.toString());
//                    //logger.info("feign interceptor body:{}",body.toString());
//                }*/
//    }
//}

}
