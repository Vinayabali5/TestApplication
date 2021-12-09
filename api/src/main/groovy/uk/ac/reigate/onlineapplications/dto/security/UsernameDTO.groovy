package uk.ac.reigate.onlineapplications.dto.security

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This DTO is used when the user needs to send their username to the API. 
 * 
 * @author Michael Horgan
 *
 */
class UsernameDTO {
    /**
     * This field stores the users email address which will be used as their username.
     */
    @JsonProperty
    String username
    
}
