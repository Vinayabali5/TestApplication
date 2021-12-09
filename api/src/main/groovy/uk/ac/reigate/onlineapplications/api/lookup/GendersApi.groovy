package uk.ac.reigate.onlineapplications.api.lookup;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

import java.lang.annotation.Documented
import java.util.logging.Logger

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import uk.ac.reigate.exceptions.NotFoundException
import uk.ac.reigate.onlineapplications.domain.lookup.Gender
import uk.ac.reigate.onlineapplications.dto.lookup.GenderDTO
import uk.ac.reigate.onlineapplications.service.lookup.GenderService


/**
 * This class is used to set up a collection of Gender HTTP end-points for the API. 
 * 
 * @author Michael Horgan
 *
 */
@Controller
public class GendersApi {
    
    private static final Logger LOGGER = Logger.getLogger(GendersApi.class.getName());
    
    @Autowired
    private final GenderService genderService;
    
    /**
     * Default NoArgs constructor
     */
    GendersApi() {}
    
    /**
     * Autowired constructor
     */
    GendersApi(GenderService genderService) {
        this.genderService = genderService;
    }
    
    /**
     * This method is used to retrieve all the Gender objects and send to the user as GenderDTO objects.
     *
     * @return A ResponseEntity with the corresponding list of GenderDto objects
     * @throws NotFoundException if nothing is found then the the NotFoundException is thrown.
     */
	@CrossOrigin("*")
    @RequestMapping(value = "/genders", produces = ["application/json"], method = RequestMethod.GET)
    public ResponseEntity<List<GenderDTO>> getAll() throws NotFoundException {
        LOGGER.info("** GendersApi - getAll");
        List<Gender> genders = genderService.findAll();
        return new ResponseEntity<List<GenderDTO>>(GenderDTO.mapFromList(genders), HttpStatus.OK);
    }
}
