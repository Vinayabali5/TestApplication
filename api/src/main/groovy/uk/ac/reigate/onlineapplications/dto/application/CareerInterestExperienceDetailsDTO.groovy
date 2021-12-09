package uk.ac.reigate.onlineapplications.dto.application

import com.fasterxml.jackson.annotation.JsonProperty

class CareerInterestExperienceDetailsDTO {
	
	@JsonProperty("careerUniversityAims")
	String careerUniversityAims
	
	@JsonProperty("workExperience")
	String workExperience
	
	@JsonProperty("hobbiesInterestsOtherAchievements")
	String hobbiesInterestsOtherAchievements
	
	@JsonProperty("favoriteSubject")
	String favoriteSubject
	
	@JsonProperty("courseworkOrExams")
	String courseworkOrExams
	
	@JsonProperty("reasonsForReigateCollege")
	String reasonsForReigateCollege
}
