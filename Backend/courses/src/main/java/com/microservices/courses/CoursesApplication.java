package com.microservices.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Replace with your frontend domain
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("Authorization", "Content-Type");
            }
        };
    }

}