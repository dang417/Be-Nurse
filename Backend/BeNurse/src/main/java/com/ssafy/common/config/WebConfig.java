package com.ssafy.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("v2/api-docs")
		.allowedOrigins("http://k9e105.p.ssafy.io:9000")
		.allowedMethods("GET", "POST", "PUT", "DELETE");
	}
}
