package com.zby.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//配置拦截器
@Component
@Configuration
public class JwtConfiguration extends WebMvcConfigurationSupport {


//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        /**
//         * addInterceptor ：添加拦截方法
//         * addPathPatterns ：添加拦截请求路径（/** ：拦截一切请求）
//         * excludePathPatterns ：加入白名单（此请求不拦截）
//         * */
//        //加载jwtInterceptor拦截规则
//        registry.addInterceptor(null).addPathPatterns("/**")
//                .excludePathPatterns("/user/login/**","/arsenal/**","/user/index/**"
//                        ,"/user/config/**","/swagger-ui.html/**","**/webjars/**","/file.html/**");//因为login之后才会生成token
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }


    /**
     * 以下WebMvcConfigurerAdapter 比较常用的重写接口
      */
//    /** 解决跨域问题 **/
//    public void addCorsMappings(CorsRegistry registry) ;
//    /** 添加拦截器 **/
//    void addInterceptors(InterceptorRegistry registry);
//    /** 这里配置视图解析器 **/
//    /** 视图跳转控制器 **/
//    void addViewControllers(ViewControllerRegistry registry);
//    void configureViewResolvers(ViewResolverRegistry registry);
//    /** 配置内容裁决的一些选项 **/
//    void configureContentNegotiation(ContentNegotiationConfigurer configurer);
//    /** 视图跳转控制器 **/
//    void addViewControllers(ViewControllerRegistry registry);
//    /** 静态资源处理 **/
//    void addResourceHandlers(ResourceHandlerRegistry registry);
//    /** 默认静态资源处理器 **/
//    void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);

}
