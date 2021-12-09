package uk.ac.reigate.onlineapplications.dto.lookup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import groovy.transform.EqualsAndHashCode;
import uk.ac.reigate.onlineapplications.domain.lookup.Course

/**
 *
 * JSON serializable DTO containing Course data
 *
 */
//@ApiModel
@JsonSerialize
@EqualsAndHashCode(includeFields=true)
public class CourseDTO implements Serializable {
    
    @JsonProperty
    Integer id
    
    @JsonProperty
    String title
    
    @JsonProperty
    String summary
    
	@JsonProperty
    String entryRequirements
	
	@JsonProperty
	String externalLink
		
    /**
     * Default No Args constructor
     */
    public CourseDTO() {
    }
    
    /**
     * Constructor to create a CourseDto object from a Course object
     *
     * @param course the Course object to use for construction
     */
    CourseDTO(Course course) {
        this.id = course?.id
        this.title = course?.title
        this.summary = course?.summary
		this.entryRequirements = course?.entryRequirements
        this.externalLink = course?.externalLink
    }
    
    @Override
	public String toString() {
		return "CourseDTO [id=" + id + ", title=" + title + ", summary=" + summary + ", entryRequirements="
				+ entryRequirements + ", externalLink=" + externalLink + "]";
	}

	public static CourseDTO mapFromEntity(Course course) {
        return new CourseDTO(course);
    }
    
    public static List<CourseDTO> mapFromList(List<Course> courses) {
        List<CourseDTO> output = courses.collect { course ->  new CourseDTO(course) };
        return output
    }
}
