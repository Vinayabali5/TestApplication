package uk.ac.reigate.onlineapplications.dto.application

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty

class SchoolDetailsDTO {
	
	/** School Details Section */
	@JsonProperty("schoolId")
	Integer schoolId
	
	@JsonProperty("startDate")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT")
	Date schoolStartDate
	
	@JsonProperty("endDate")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT")
	Date schoolEndDate

}
