package uk.ac.reigate.onlineapplications.dto.application

import com.fasterxml.jackson.annotation.JsonProperty

class ContactDetailsDTO {
	
	@JsonProperty
	ContactDTO[] contacts
	
}
