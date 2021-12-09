package uk.ac.reigate.onlineapplications.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class EmailConfiguration {
    
    @Value("\${email.debug}")
    Boolean DEBUG
    
    @Value("\${email.server}")
    String server
    
    @Value("\${email.port}")
    String port
    
    @Value("\${email.username:}")
    String username
    
    @Value("\${email.password:}")
    String password
    
    @Value("\${email.ssl_enable:false}")
    boolean sslEnable
    
    @Bean
    JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl()
        mailSender.setHost(this.server)
        mailSender.setPort(this.port.toInteger())
        mailSender.setUsername(this.username)
        mailSender.setPassword(this.password)
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        if (!this.username.equals("") || !this.password.equals("")) {
            props.put("mail.smtp.auth", "true");
        }
        if (sslEnable == true) {
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");
        } else {
            props.put("mail.smtp.starttls.enable", "false");
        }
        if (DEBUG) {
            props.put("mail.debug", "true");
        }
        
        return mailSender
    }
}
