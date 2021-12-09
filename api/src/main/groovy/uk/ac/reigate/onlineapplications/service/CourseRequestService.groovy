package uk.ac.reigate.onlineapplications.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.exceptions.InvalidDataException
import uk.ac.reigate.onlineapplications.api.IDTOToEntityConverter
import uk.ac.reigate.onlineapplications.domain.CourseRequest
import uk.ac.reigate.onlineapplications.domain.lookup.Course
import uk.ac.reigate.onlineapplications.dto.application.CourseRequestDTO
import uk.ac.reigate.onlineapplications.repository.CourseRequestRepository
import uk.ac.reigate.onlineapplications.service.lookup.CourseService

@Service
class CourseRequestService implements IDTOToEntityConverter<CourseRequestDTO, CourseRequest> {
 
    @Autowired 
    CourseRequestRepository courseRequestRepository
    
    @Autowired
    ApplicationService applicationService
    
    @Autowired 
    CourseService courseService

    /**
     * Find an individual contact using the contacts ID fields
     *
     * @param id the ID fields to search for
     * @return the Contact object that matches the ID supplied, or null if not found
     */
    CourseRequest findById(Integer id) {
        return courseRequestRepository.findById(id).orElse(null)
    }
    
    /**
     * Find an individual contact using the contacts ID fields
     *
     * @param id the ID fields to search for
     * @return the Contact object that matches the ID supplied, or null if not found
     */
    List<CourseRequest> findByApplicationId(Integer id) {
        return courseRequestRepository.findByApplicationId(id)
    }
    
    /**
     * This method is used to convert a CourseRequestDTO object into a CourseRequest entity object. 
     */
    @Override
    public CourseRequest convertToEntity(CourseRequestDTO dto) {
        CourseRequest out = new CourseRequest() 
        if (dto.id != null) out = findById(dto.id)  
        if (dto.courseId == null) throw new InvalidDataException("The course request requires a courseId field.")
        out.course = courseService.findById(dto.courseId)
        return out
    }
    
    /**
     * This method is used to create a CourseRequestDTO object from the supplied CourseRequest object. This will use the
     * CourseRequest.request field to lookup the Course object that matches the course request. This Course object is then 
     * used to supply the additional data to the end-user. 
     * 
     * @param request a CourseRequest object to create a DTO from.
     * @return a CourseRequestDTO or null if the Course object cannot be found.
     */
    public CourseRequestDTO createDTO(CourseRequest request) {
        Course course = courseService.findByRequestCode(request.requestCode.substring(2, 4))
        if (course != null) {
            CourseRequest output = new CourseRequestDTO(
                id: request.id,
                courseId: course.id,
                _title: course.title     
            )
            return output
        }
        return null
    }
    
    /**
     * This methods is used to create a list of CourseRequestDTO objects from a supplied list of CourseRequest objects. This
     * will use the createDTO method to create each object. 
     * 
     * @param requests A List of CourseRequest objects to convert.
     * @return a List of CourseRequestDTO objects.
     */
    public List<CourseRequestDTO> createDTOList(List<CourseRequest> requests) {
        return requests.collect { CourseRequest request ->
            return this.createDTO(request)
        }

    }
    
}
