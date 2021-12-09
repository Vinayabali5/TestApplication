package uk.ac.reigate.onlineapplications.service.lookup

//import static org.springframework.util.Assert

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import uk.ac.reigate.onlineapplications.domain.lookup.Title
import uk.ac.reigate.onlineapplications.repository.lookup.TitleRepository

@Service
class TitleService {
    
    @Autowired
    TitleRepository titleRepository
    
    /**
     * Default NoArgs constructor
     */
    TitleService() {}
    
    /**
     * Autowired Constructor
     *
     * @param titleRepository
     */
    TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }
    
    /**
     * Find an individual title using the titles ID fields
     *
     * @param id the ID fields to search for
     * @return the Title object that matches the ID supplied, or null if not found
     */
    @Transactional(readOnly = true)
    Title findById(Integer id) {
        return titleRepository.findById(id).orElse(null);
    }
    
    /**
     * Find a single page of Title objects
     * @return a List of Titles
     */
    @Transactional(readOnly = true)
    List<Title> findAll() {
        List<Title> titles = titleRepository.findAll();
        return titles
    }
    
}
