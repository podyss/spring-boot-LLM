package cn.edu.ncut.cs.springboot.llm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.CacheControl.maxAge;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//允许跨域访问的路径
                .allowedOriginPatterns("*")//允许跨域访问的源
//                .allowedOrigins("http://localhost:5173")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("*")//允许请求方法
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE", "OPTIONS");//允许请求方法
                .maxAge(3600);
    }
}