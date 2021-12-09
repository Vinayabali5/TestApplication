package uk.ac.reigate.onlineapplications.service.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import uk.ac.reigate.exceptions.InvalidDataException
import uk.ac.reigate.exceptions.UserExistsException
import uk.ac.reigate.exceptions.UserNotFoundException
import uk.ac.reigate.onlineapplications.domain.User
import uk.ac.reigate.onlineapplications.dto.security.UsernamePasswordDTO

@Service
class RegistrationService {

    @Autowired
    UserService userService

    @Autowired 
    PasswordEncoder passwordEncoder
    
    /**
     * This method is used to create a new User account object based on the RegistrationDTO object supplied.
     *
     * @param registrationDto a RegistrationDTO object.
     * @return a new User based on the RegistrationDTO object.
     */
    User createUser(UsernamePasswordDTO registrationDto) {
        if (registrationDto.password == null || registrationDto.confirmPassword == null) {
            throw new InvalidDataException("The passwords supplied must not be null, or blank.")
        }
        if (registrationDto.password == registrationDto.confirmPassword) {
            if (userService.findByUsername(registrationDto.username) != null) {
                throw new UserExistsException("A user already exists with the same username/email address.")
            }
            User user = new User(registrationDto.username, passwordEncoder.encode(registrationDto.password))
            user = userService.save(user)
            return user
        } else {
            throw new InvalidDataException("The passwords supplied do no match so cannot create new user account.")
        }
    }

    /**
     * This method is used to confirm a User accounts creation 
     * @param code
     */
    User confirmUser(String code) {
        if (code == null) {
            throw new InvalidDataException("A confirmation code is required to confirmation the user account.")
        }
        User user = userService.findByConfirmationCode(code)
        if (user == null) {
            throw new UserNotFoundException("The user confirmation code specified cannot be found in the database.")
        }
        user.confirmed = true
        return userService.save(user)
    }
    
    /**
     * This method is used to reset the confirmation sent date on a users record. This will
     * then trigger a fresh confirmation email to be sent to the user.
     * 
     * @param username the username of the user to resend for. 
     */
    void resendConfirmation(String username) {
        if (username == null) {
            throw new InvalidDataException("A username is required to resend the confirmation email.")
        }
        User user = userService.findByUsername(username)
        if (user == null) {
            throw new UserNotFoundException("The username specified cannot be found in the database. Please register for an account to proceed.")
        }
        user.confirmationSent = null
        userService.save(user)
    }
}
