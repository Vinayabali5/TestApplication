package uk.ac.reigate.onlineapplications.api.lookup;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

import java.util.logging.Logger

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import uk.ac.reigate.exceptions.NotFoundException
import uk.ac.reigate.onlineapplications.domain.lookup.Course
import uk.ac.reigate.onlineapplications.dto.lookup.CourseDTO
import uk.ac.reigate.onlineapplications.service.lookup.CourseService

/**
 * This class is used to set up a collection of Course HTTP end-points for the API.
 *
 * @author Michael Horgan
 *
 */
@Controller
@RequestMapping(value = "/courses", produces = [ APPLICATION_JSON_VALUE ])
public class CoursesApi {

    private static final Logger LOGGER = Logger.getLogger(CoursesApi.class.getName());

    @Autowired
    private final CourseService courseService;

    /**
     * Default NoArgs constructor
     */
    CoursesApi() {}

    /**
     * Autowired constructor
     */
    CoursesApi(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * This method is used to retrieve all the Course objects and send to the user as CourseDTO objects.
     *
     * @return A ResponseEntity with the corresponding list of CourseDto objects
     * @throws NotFoundException if nothing is found then the the NotFoundException is thrown.
     */
	@CrossOrigin("*")
    @RequestMapping(value = "", produces = ["application/json"], method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getAll() throws NotFoundException {
        LOGGER.info("** CoursesApi - getAll");
        List<Course> courses = courseService.findAll();
        return new ResponseEntity<List<CourseDTO>>(CourseDTO.mapFromList(courses), HttpStatus.OK);
    }
}
