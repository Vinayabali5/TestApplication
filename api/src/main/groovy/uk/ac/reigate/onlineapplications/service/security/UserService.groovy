package uk.ac.reigate.onlineapplications.service.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.onlineapplications.domain.User
import uk.ac.reigate.onlineapplications.repository.UserRepository

@Service
class UserService {
    
    @Autowired
    UserRepository userRepository
    
    /**
     * This method is used to retrieve a User by the supplied username.
     * 
     * @param username a username to search for.
     * @return a User object that matches the username supplied. 
     */
    User findByUsername(String username) {
        return userRepository.findByUsername(username)
    }

    /**
     * This method is used to retrieve a User by the supplied confirmation code.
     * 
     * @param code a confirmation code to search for.
     * @return a User object that matches the confirmation code supplied. 
     */
    User findByConfirmationCode(String code) {
        return userRepository.findByConfirmationCode(code) 
    }
        
    /**
     * This method is used to save a User object to the the database. 
     * 
     * @param user a User object to save.
     * @return the saved User object.
     */
    User save(User user) {
        return userRepository.save(user)
    }
    
    /**
     * This method is used to retrieve all User objects where there has been no confirmation email 
     * successfully sent to.
     *  
     * @return a List of User objects.
     */
    List<User> usersWithNoConfirmationEmail() {
        return userRepository.findByConfirmationSentIsNull()
    }
    
    /**
     * This method is used to retrieve all User objects where a password reset has been requested. 
     *   
     * @return a List of User objects.
     */
    List<User> usersForPasswordReset() {
        return userRepository.findByResetPasswordFlag(1)
    }
    
    
}
