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
import uk.ac.reigate.onlineapplications.api.IEntityToDTOConverter
import uk.ac.reigate.onlineapplications.domain.lookup.ContactType
import uk.ac.reigate.onlineapplications.dto.lookup.ContactTypeDTO
import uk.ac.reigate.onlineapplications.service.lookup.ContactTypeService


/**
 * This class is used to set up a collection of ContactType HTTP end-points for the API. 
 * 
 * @author Michael Horgan
 *
 */
@Controller
public class ContactTypesApi {
    
    private static final Logger LOGGER = Logger.getLogger(ContactTypesApi.class.getName());
    
    @Autowired
    private final ContactTypeService contactTypeService;
	
    /**
     * Default NoArgs constructor
     */
    ContactTypesApi() {}
    
    /**
     * Autowired constructor
     */
    ContactTypesApi(ContactTypeService contactTypeService) {
        this.contactTypeService = contactTypeService;
    }
    
    /**
     * This method is used to retrieve all the ContactType objects and send to the user as ContactTypeDTO objects.
     *
     * @return A ResponseEntity with the corresponding list of ContactTypeDto objects
     * @throws NotFoundException if nothing is found then the the NotFoundException is thrown.
     */
    @CrossOrigin("*")
    @RequestMapping(value = "/contact-types", produces = ["application/json"], method = RequestMethod.GET)
    public ResponseEntity<List<ContactTypeDTO>> getAll() throws NotFoundException {
        LOGGER.info("** ContactTypesApi - contactTypesGet");
        List<ContactType> contactTypes = contactTypeService.findAll();
        return new ResponseEntity<List<ContactTypeDTO>>(ContactTypeDTO.mapFromList(contactTypes), HttpStatus.OK);
    }
	
}
