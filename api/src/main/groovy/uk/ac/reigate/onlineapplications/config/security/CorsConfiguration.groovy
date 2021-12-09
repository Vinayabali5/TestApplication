package uk.ac.reigate.onlineapplications.config.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration implements WebMvcConfigurer {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass())
    
    public void addCorsMappings(CorsRegistry registry) {
        LOGGER.info("II Setting CORS Mappings");
        registry.addMapping("**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedOrigins("*")
    }
}
