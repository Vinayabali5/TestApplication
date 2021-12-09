package uk.ac.reigate.onlineapplications.service.communication

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailException
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

import uk.ac.reigate.onlineapplications.domain.User
import uk.ac.reigate.onlineapplications.service.security.UserService

/**
 * This service is used to handle the Registration Emails that the system sends. This class has a scheduled task that will continually 
 * check for users that have not had their confirmation email.  
 * 
 * @author Michael Horgan
 *
 */
@Service
class RegistrationEmailService {

    private Boolean DEBUG = false
    
    /**
     * This property is used for logging messages to the standard output.     
     */
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass())
    
    /**
     * This property is used to specify the URL that will be used to redirect users to to activate their user account. This URL is the 
     * base URL for the user interface and is set in the application.yml configuration file.  
     */
    @Value("\${url.ui}")
    private final String uiUrl
        
    @Autowired
    UserService userService

    @Autowired
    EmailService emailService

    /**
     * This method is used to find users where no confirmation email has been sent and then sending the email.
     * 
     * This is a @Scheduled task that is set to run every 5 minutes.
     */
    @Scheduled(fixedDelayString = "PT1M")
    void checkConfirmationEmails() {
        if (this.DEBUG) LOGGER.info("II Running the Check Confirmation Email routine.")
        List<User> users = userService.usersWithNoConfirmationEmail()
        if (users.empty) { 
            if (this.DEBUG) LOGGER.info("No emails to send") 
        }
        users.each { user ->
            LOGGER.info("II - Sending registration confirmation email to: " + user.username)
            sendConfirmationEmail(user)
        }
    }
    
    /**
     * This method is used to send the user registration confirmation email to the end user.
     *
     * @param user a User object to use for the email.
     */
    void sendConfirmationEmail(User user) {
        // Set up subject and message details
        String subject = "Reigate College Application Registration"
        String message = "<p>Thank you for starting your application to Reigate College.  You have successfully registered an account.</p><p><a href=\"%s\">Click here to complete registration</a></p>"

        // Set up Base URL
        String confirmUrl = this.uiUrl
        if (!confirmUrl.endsWith('/')) confirmUrl += '/'
        confirmUrl += 'confirm/'
        // Get user confirmation code
        String confirmCode = user?.confirmationCode
        // Create user registration confirmation link
        String fullUrl = confirmUrl + confirmCode
        
        try {
            // Send Email Message
            emailService.sendMessage(user.username, subject, String.format(message, fullUrl))
    
            // Update user details to add a confirmation sent date
            user.confirmationSent = new Date()
            userService.save(user)
        } catch (MailException ex) {
            throw ex
        }
    }

}
