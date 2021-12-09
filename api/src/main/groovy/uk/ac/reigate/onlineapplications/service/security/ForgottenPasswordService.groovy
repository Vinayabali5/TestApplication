package uk.ac.reigate.onlineapplications.service.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import uk.ac.reigate.exceptions.InvalidDataException
import uk.ac.reigate.exceptions.UserNotFoundException
import uk.ac.reigate.onlineapplications.domain.User
import uk.ac.reigate.onlineapplications.dto.security.UsernamePasswordCodeDTO
import uk.ac.reigate.onlineapplications.dto.security.UsernamePasswordDTO
import uk.ac.reigate.onlineapplications.service.communication.ForgottenPasswordProcessFlag

/**
 * This service is used to process the password reset for user account of the Online Application system. The process
 * for a password reset is as follows: 
 * 
 * <ol>
 * <li>Receive POST from client to initialise a password reset.</li>
 * <li>Verify User exists</li>
 * <li>Set User password field to NULL</li>
 * <li>Send emails to all Users where password is NULL (this is handled by the ForgottenPasswordEmailService)</li>
 * <li>Receive POST from client with username and new password (plus confirmation password)</li>
 * <li>Set new password for User</LI>
 * </ol>
 * 
 * @author Michael Horgan
 *
 */
@Service
class ForgottenPasswordService {
    
    @Autowired
    UserService userService
    
    @Autowired
    PasswordEncoder passwordEncoder
    
    /**
     * This method is used to initialised a password reset. This is done by setting the User account's password
     * to NULL. 
     * 
     * @param username the username of the User to initialise a password reset on.
     */
    void initialisePasswordReset(String username) {
        User user = userService.findByUsername(username)
        if (user == null) {
            return
            //throw new UserNotFoundException("A user account with the supplied username does not exist in the database. Please register for an account.")
        }
        user.resetPasswordFlag = ForgottenPasswordProcessFlag.INIT
        userService.save(user)
    }
    
    /**
     * This method is used to finalise the password reset process and actually reset the password. 
     * 
     * @param user a UsernamePasswordDTO object containing the required data for a password reset.
     */
    void resetPassword(UsernamePasswordCodeDTO passwordReset) {
        if (passwordReset == null) {
            throw new InvalidDataException("The require data for a password reset has not been supplied.")
        }
        if (passwordReset.password == null || passwordReset.confirmPassword == null) {
            throw new InvalidDataException("The new password supplied is NULL. This is not a valid password.")
        }
        if (passwordReset.password != passwordReset.confirmPassword) {
            throw new InvalidDataException("The new password supplied does not match the confirmation password. These passwords must match for a password reset to be successful.")
        }
        User user = userService.findByUsername(passwordReset.username)
        if (user == null) {
            return
            //throw new UserNotFoundException("A user account with the supplied username does not exist in the database. Please register for an account.")
        }
        if (user.resetPasswordFlag != ForgottenPasswordProcessFlag.SENT) {
            throw new InvalidDataException("Invalid password reset - flag error.")
        }
        if (user.confirmationCode != passwordReset.code) {
            throw new InvalidDataException("Invalid password reset - code error.")
        }
        user.password = passwordEncoder.encode(passwordReset.password)
        user.resetPasswordFlag = ForgottenPasswordProcessFlag.COMPLETE
        userService.save(user)
    }
    
}
