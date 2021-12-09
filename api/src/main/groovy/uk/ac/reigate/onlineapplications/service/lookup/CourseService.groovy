package uk.ac.reigate.onlineapplications.service.lookup

//import static org.springframework.util.Assert

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import uk.ac.reigate.onlineapplications.domain.lookup.Course
import uk.ac.reigate.onlineapplications.repository.lookup.CourseRepository

@Service
class CourseService {
    
    @Autowired
    CourseRepository courseRepository
    
    /**
     * Default NoArgs constructor
     */
    CourseService() {}
    
    /**
     * Autowired Constructor
     *
     * @param courseRepository
     */
    CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    /**
     * Find an individual course using the courses ID fields
     *
     * @param id the ID fields to search for
     * @return the Course object that matches the ID supplied, or null if not found
     */
    @Transactional(readOnly = true)
    Course findById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }
    
    /**
     * Find a single page of Course objects
     * @return a List of Courses
     */
    @Transactional(readOnly = true)
    List<Course> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courses
    }
    
    
    /**
     * This method is used to retrieve a course from the supplied course request.
     * 
     * @param requestCode a request code to search for.
     * @return the Course object that matches the supplied request code. 
     */
    Course findByRequestCode(String requestCode) {
        return courseRepository.findByRequestCode(requestCode)
    }
    
}
