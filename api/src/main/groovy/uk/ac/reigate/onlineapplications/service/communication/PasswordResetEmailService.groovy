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
class PasswordResetEmailService {

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
     * This method is used to find users with no password and then sending the password reset email.
     * 
     * This is a @Scheduled task that is set to run every 1 minutes.
     */
    @Scheduled(fixedDelayString = "PT1M")
    void checkPasswordResetEmails() {
        if (this.DEBUG) LOGGER.info("II Running the Check Password Reset routine.")
        List<User> users = userService.usersForPasswordReset()
        if (users.empty) { 
            if (this.DEBUG) LOGGER.info("II - No emails to send") 
        }
        users.each { user ->
            LOGGER.info("II - Sending password reset email to: " + user.username)
            sendConfirmationEmail(user)
        }
    }
    
    /**
     * This method is used to send the password reset email to the end user.
     *
     * @param user a User object to use for the email.
     */
    void sendConfirmationEmail(User user) {
        // Set up subject and message details
        String subject = "Reigate College Apply - Password Reset"
        String message = "<p>You have requested a password reset for your account on Reigate College Apply. To proceed please click following lick to proceed with the password reset.</p><p><a href=\"%s\">Reset Password</a></p>"

        // Set up Base URL
        String confirmUrl = this.uiUrl
        if (!confirmUrl.endsWith('/')) confirmUrl += '/'
        confirmUrl += 'reset-password/'
        // Get user confirmation code
        String confirmCode = user?.confirmationCode
        // Create user password reset link
        String fullUrl = confirmUrl + confirmCode
        
        try {
            // Send Email Message
            emailService.sendMessage(user.username, subject, String.format(message, fullUrl))
    
            // Update user details to add a confirmation sent date
            user.resetPasswordFlag = ForgottenPasswordProcessFlag.SENT
            userService.save(user)
        } catch (MailException ex) {
            throw ex
        }
    }

}
