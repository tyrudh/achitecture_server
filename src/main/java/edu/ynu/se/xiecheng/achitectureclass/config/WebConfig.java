package edu.ynu.se.xiecheng.achitectureclass.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/shopImg/**")
                .addResourceLocations("file:///E:/upload/shopImg/");
        registry.addResourceHandler("/foodImg/**")
                .addResourceLocations("file:///E:/upload/foodImg/");
    }
}
