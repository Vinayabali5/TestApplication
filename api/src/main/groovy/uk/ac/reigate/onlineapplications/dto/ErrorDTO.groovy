package uk.ac.reigate.onlineapplications.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ErrorDTO extends StatusDTO {
    
    /**
     * This field is used to provide a more in depth description of the error message sent to the client.
     */
    @JsonProperty
    String error
    
    /**
     * This is the default constructor for the ErrorDTO object.
     * 
     * @param error the summary error message.
     * @param message the longer description of the error.
     */
    ErrorDTO(String error, String message, String path) {
        super(message, path)
        this.error = error
    } 
    
}
