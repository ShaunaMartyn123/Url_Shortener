package com.example.url_shortener.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//Sets up CORS - Cross-Origin Resource Sharing - It allows
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Add CORS mapping for all paths
                .allowedOrigins("http://localhost:3000")// Allow requests from a specific origin (localhost:3000)
                .allowedMethods("*");// Allow all HTTP methods (GET, POST, PUT, DELETE) for specified origin (localhost:3000)
    }
}