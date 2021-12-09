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
import uk.ac.reigate.onlineapplications.domain.lookup.LegalSex
import uk.ac.reigate.onlineapplications.dto.lookup.LegalSexDTO
import uk.ac.reigate.onlineapplications.service.lookup.LegalSexService


/**
 * This class is used to set up a collection of LegalSex HTTP end-points for the API. 
 * 
 * @author Michael Horgan
 *
 */
@Controller
public class LegalSexesApi {
    
    private static final Logger LOGGER = Logger.getLogger(LegalSexesApi.class.getName());
    
    @Autowired
    private final LegalSexService legalSexService;
    
    /**
     * Default NoArgs constructor
     */
    LegalSexesApi() {}
    
    /**
     * Autowired constructor
     */
    LegalSexesApi(LegalSexService legalSexService) {
        this.legalSexService = legalSexService;
    }
    
    /**
     * This method is used to retrieve all the LegalSex objects and send to the user as LegalSexDTO objects.
     *
     * @return A ResponseEntity with the corresponding list of LegalSexDto objects
     * @throws NotFoundException if nothing is found then the the NotFoundException is thrown.
     */
	@CrossOrigin("*")
    @RequestMapping(value = "/legal-sexes", produces = ["application/json"], method = RequestMethod.GET)
    public ResponseEntity<List<LegalSexDTO>> getAll() throws NotFoundException {
        LOGGER.info("** LegalSexsApi - getAll");
        List<LegalSex> legalSexes = legalSexService.findAll();
        return new ResponseEntity<List<LegalSexDTO>>(LegalSexDTO.mapFromList(legalSexes), HttpStatus.OK);
    }
}
