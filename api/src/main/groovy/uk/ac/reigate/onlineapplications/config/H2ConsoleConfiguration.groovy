package uk.ac.reigate.onlineapplications.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Profile("dev")
@Order(value = 1000)
@Configuration
class H2ConsoleConfiguration extends WebSecurityConfigurerAdapter {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass())
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Setting up access to the H2 Console
        LOGGER.info("II Enabling H2 Console Security")
        httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll()
        httpSecurity.headers().frameOptions().sameOrigin()
    }
}
