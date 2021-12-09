package uk.ac.reigate.onlineapplications.repository

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.onlineapplications.domain.User

interface UserRepository extends CrudRepository<User, Integer> {
    
    /**
     * This methods is used to search for a User object based on the username provided.
     * 
     * @param username the username string to search for.
     * @return a User with a matching username.
     */
    User findByUsername(String username)
    
    /**
     * This methods is used to search for a User object based on the confirmation code provided.
     * 
     * @param code the user confirmation code to search for.
     * @return a User with a matching confirmation code.
     */
    User findByConfirmationCode(String code)
    
    /**
     * This method is used to retrieve all User objects where the confirmation sent date is set to NULL. 
     * 
     * @return a List of User objects.
     */
    List<User> findByConfirmationSentIsNull() 
    /**
     * This method is used to retrieve all User objects where the confirmation sent date is set to NULL. 
     * 
     * @return a List of User objects.
     */
    List<User> findByResetPasswordFlag(Integer flag)
}
