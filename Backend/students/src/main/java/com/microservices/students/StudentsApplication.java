package com.microservices.students;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class StudentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
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
