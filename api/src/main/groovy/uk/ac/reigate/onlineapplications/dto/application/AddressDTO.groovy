package uk.ac.reigate.onlineapplications.dto.application

import com.fasterxml.jackson.annotation.JsonProperty

import uk.ac.reigate.onlineapplications.domain.Address
import uk.ac.reigate.onlineapplications.domain.ExtraSchool

class AddressDTO {
	
	@JsonProperty
	Integer id

	@JsonProperty
	String line1
	
    @JsonProperty
    String line2
    
    @JsonProperty
    String line3
    
	@JsonProperty
	String town
	
    @JsonProperty
    String county
    
    @JsonProperty
	String postcode

    /**
     * Default no-args constructor
     */
    AddressDTO() {}
    
    /**
     * This constructor is used to create a DTO object from the Address entity object.
     * 
     * @param address an Address entity
     */
    AddressDTO(Address address) {
        if (address != null) {
    		this.id = address.id
    		this.line1 = address.line1
    		this.line2 = address.line2
            this.line3 = address.line3
    		this.town = address.town
            this.county = address.county
    		this.postcode = address.postcode
        }
    }
    
	AddressDTO(ExtraSchool schoolAddress) {
		if (schoolAddress != null) {
			this.line1 = schoolAddress.line1
			this.line2 = schoolAddress.line2
			this.line3 = schoolAddress.line3
			this.town = schoolAddress.town
			this.county = schoolAddress.county
			this.postcode = schoolAddress.postcode
		}
	}
	
}
