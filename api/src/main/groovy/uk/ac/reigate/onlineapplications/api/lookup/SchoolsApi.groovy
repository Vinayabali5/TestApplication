package uk.ac.reigate.onlineapplications.api.lookup;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

import java.util.logging.Logger

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import uk.ac.reigate.exceptions.NotFoundException
import uk.ac.reigate.onlineapplications.domain.lookup.School
import uk.ac.reigate.onlineapplications.dto.lookup.SchoolDTO
import uk.ac.reigate.onlineapplications.service.lookup.SchoolService


/**
 * This class is used to set up a collection of Schools HTTP end-points for the API. 
 * 
 * @author Michael Horgan
 *
 */
@Controller
public class SchoolsApi {
    
    private static final Logger LOGGER = Logger.getLogger(SchoolsApi.class.getName());
    
    @Autowired
    private final SchoolService schoolService;
    
    /**
     * Default NoArgs constructor
     */
    SchoolsApi() {}
    
    /**
     * Autowired constructor
     */
    SchoolsApi(SchoolService schoolService) {
        this.schoolService = schoolService;
    }
    
    /**
     * This method is used to retrieve all the School objects and send to the user as SchoolDTO objects.
     *
     * @return A ResponseEntity wrapping the corresponding list of SchoolDto objects
     * @throws NotFoundException if nothing is found then the the NotFoundException is thrown.
     */
    @CrossOrigin("*")
    @RequestMapping(value = "/schools", produces = ["application/json"], method = RequestMethod.GET)
    public ResponseEntity<List<SchoolDTO>> getAll() throws NotFoundException {
        LOGGER.info("** SchoolsApi - getAll");
        List<School> schools = schoolService.findAll();
        return new ResponseEntity<List<SchoolDTO>>(SchoolDTO.mapFromList(schools), HttpStatus.OK);
    }
}
