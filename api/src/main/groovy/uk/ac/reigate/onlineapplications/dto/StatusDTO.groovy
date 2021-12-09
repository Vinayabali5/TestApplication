package uk.ac.reigate.onlineapplications.dto

import com.fasterxml.jackson.annotation.JsonProperty

class StatusDTO {
    
    /**
     * This field is used to provide a message about the status to be sent to the client.
     */
    @JsonProperty
    String message
    
    /**
     * This fields is used to store the path that the client requested.
     */
    @JsonProperty
    String path
    
    /**
     * This field is used to store a timestamp for the event.
     */
    @JsonProperty
    Date timestamp
    
    /**
     * This is the default constructor for the ErrorDTO object.
     *
     * @param error the summary error message.
     * @param message the longer description of the error.
     */
    StatusDTO(String message, String path) {
        this.message = message
        this.path = path
        this.timestamp = new Date()
    }
    
}
