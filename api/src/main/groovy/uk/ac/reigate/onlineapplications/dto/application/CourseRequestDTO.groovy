package uk.ac.reigate.onlineapplications.dto.application

import com.fasterxml.jackson.annotation.JsonProperty

import uk.ac.reigate.onlineapplications.domain.CourseRequest
import uk.ac.reigate.onlineapplications.domain.lookup.Course

/**
 * This DTO is used to represent a course request. 
 * 
 * @author Michael Horgan
 *
 */
class CourseRequestDTO {
    
    @JsonProperty(value = "id")
    Integer id

    //@JsonProperty(value = "applicationId")
    //Integer applicationId

    @JsonProperty(value = "courseId")
    Integer courseId
    
    /**
     * This fields is a read-only field used to allow the display title to be sent to the client.
     */
    @JsonProperty(value = "title")
    String _title
    
    /**
     * Default no-args constructor
     */
    CourseRequestDTO() {}
    
    /**
     * This constructor is used to convert a Course entity to a BasicCourseDTO object.
     * 
     * @param course a Course entity object.
     */
    CourseRequestDTO(CourseRequest request) {
        this.id = request.id
        //this.applicationId = request?.application.id
        if (request.course != null) {
            this.courseId = request?.course.id
            this._title = request?.course.title
        }
    }
    
    /**
     * This method is used to convert a List of CourseRequest entity objects to a List of BasicCourseDTO objects.
     * 
     * @param requests a List of CourseRequest entity objects.
     * @return a List of BasicCourseDTO objects.
     */
    public static List<CourseRequestDTO> mapFromList(List<CourseRequest> requests) {
        return requests.collect { CourseRequest request ->
            new CourseRequestDTO(request)
        }
    }
}
