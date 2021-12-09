package uk.ac.reigate.onlineapplications.dto.security

import com.fasterxml.jackson.annotation.JsonProperty

class UsernamePasswordCodeDTO extends UsernamePasswordDTO {
    /**
     * This fields stores the users code field for validation.
     */
    @JsonProperty
    String code
}
