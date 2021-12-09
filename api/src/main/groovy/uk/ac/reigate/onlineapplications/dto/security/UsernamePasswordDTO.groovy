package uk.ac.reigate.onlineapplications.dto.security

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This DTO class is used allow the end user to supply the required username and password data to the system.
 *  
 * @author Michael Horgan
 *
 */
class UsernamePasswordDTO extends UsernameDTO {
    
    /**
     * This fields stores the users desired password.
     */
    @JsonProperty
    String password
    
    /**
     * This fields stores confirmation of the users desired password.
     */
    @JsonProperty
    String confirmPassword
}
