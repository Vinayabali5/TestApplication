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
import uk.ac.reigate.onlineapplications.domain.lookup.Title
import uk.ac.reigate.onlineapplications.dto.lookup.TitleDTO
import uk.ac.reigate.onlineapplications.service.lookup.TitleService

/**
 * This class is used to set up a collection of Title HTTP end-points for the API.
 *
 * @author Michael Horgan
 *
 */
@Controller
public class TitlesApi {

    private static final Logger LOGGER = Logger.getLogger(TitlesApi.class.getName());

    @Autowired
    private final TitleService titleService;

    /**
     * Default NoArgs constructor
     */
    TitlesApi() {}

    /**
     * Autowired constructor
     */
    TitlesApi(TitleService titleService) {
        this.titleService = titleService;
    }

    /**
     * This method is used to retrieve all the Title objects and send to the user as TitleDTO objects.
     *
     * @return A ResponseEntity with the corresponding list of TitleDto objects
     * @throws NotFoundException if nothing is found then the the NotFoundException is thrown.
     */
	@CrossOrigin("*")
    @RequestMapping(value = "/titles", produces = ["application/json"], method = RequestMethod.GET)
    public ResponseEntity<List<TitleDTO>> getAll() throws NotFoundException {
        LOGGER.info("** TitlesApi - getAll");
        List<Title> titles = titleService.findAll();
        return new ResponseEntity<List<TitleDTO>>(TitleDTO.mapFromList(titles), HttpStatus.OK);
    }
}
