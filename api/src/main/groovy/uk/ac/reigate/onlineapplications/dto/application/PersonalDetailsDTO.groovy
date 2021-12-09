package uk.ac.reigate.onlineapplications.dto.application

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty

class PersonalDetailsDTO {

	@JsonProperty("title")
	Integer titleId
	
	@JsonProperty("legalFirstname")
	String legalFirstName
	
	@JsonProperty("legalSurname")
	String legalSurname
	
	@JsonProperty("middleNames")
	String middleNames
	
	@JsonProperty("preferredName")
	String preferredName
	
	@JsonProperty("home")
	String home
	
	@JsonProperty("mobile")
	String mobile
	
	@JsonProperty("email")
	String email
	
	@JsonProperty("address")
	AddressDTO address
	
	@JsonProperty("gender")
	Integer genderId
	
	@JsonProperty("dateOfBirth")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT")
	Date dateOfBirth
	
	@JsonProperty("nationality")
	Integer nationalityId
	

}
