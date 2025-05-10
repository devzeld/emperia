package me.zeld.emperia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigures() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow all endpoints
                        .allowedOrigins("*")     // Allow all origins (⚠️ Not secure!)
                        .allowedMethods("*")     // Allow all methods (GET, POST, etc.)
                        .allowedHeaders("*");    // Allow all headers
            }
        };
    }
}

@SpringBootApplication
public class EmperiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmperiaApplication.class, args);
    }

}
