package uk.ac.reigate.onlineapplications.service.lookup

import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.onlineapplications.domain.lookup.LegalSex
import uk.ac.reigate.onlineapplications.repository.lookup.LegalSexRepository

@Service
class LegalSexService {
    
    @Autowired
    LegalSexRepository legalSexRepository
    
    /**
     * Default NoArgs constructor
     */
    LegalSexService() {}
    
    /**
     * Autowired Constructor
     *
     * @param legalSexRepository
     */
    LegalSexService(LegalSexRepository legalSexRepository) {
        this.legalSexRepository = legalSexRepository;
    }
    
    /**
     * Find an individual legalSex using the legalSexes ID fields
     *
     * @param id the ID fields to search for
     * @return the LegalSex object that matches the ID supplied, or null if not found
     */
    LegalSex findById(Integer id) {
        return legalSexRepository.findById(id).orElse(null);
    }
    
    /**
     * Find all legalSexes
     *
     * @return a SearchResult set with the list of LegalSexs
     */
    List<LegalSex> findAll() {
        return legalSexRepository.findAll();
    }
    
    /**
     * This service method is used to save a complete LegalSex object in the database
     *
     * @param legalSex the new LegalSex object to be saved
     * @return the saved version of the LegalSex object
     */
    public LegalSex save(LegalSex legalSex) {
        return legalSexRepository.save(legalSex)
    }
    
    /**
     * Saves a list of LegalSex objects to the database
     *
     * @param legalSexes a list of LegalSexs to be saved to the database
     * @return the list of save LegalSex objects
     */
    @Transactional
    public List<LegalSex> saveList(List<LegalSex> legalSexes) {
        return legalSexes.collect { legalSex -> save(legalSex) };
    }
}
