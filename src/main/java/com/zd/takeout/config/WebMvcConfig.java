package com.zd.takeout.config;

import com.zd.takeout.common.JacksonObjectMapper;
import com.zd.takeout.controller.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 设置静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("Start Static Resources Mapping...");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(new LoginInterceptor());
        interceptor.addPathPatterns("/**");
        interceptor.excludePathPatterns(
                "/",
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/error",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        );
    }

    /**
     * 货站mvc消息转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("消息转换器启动");
        //创建消息转换器
        MappingJackson2HttpMessageConverter httpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置mvc转换器，底层使用jackson将java转换为json对象
        httpMessageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面转换器添加到mvc框架的转换器容器中
        converters.add(0,httpMessageConverter);
        //并且设置级别为最高



    }
}
