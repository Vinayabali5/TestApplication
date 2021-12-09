package uk.ac.reigate.onlineapplications.service.lookup

import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.onlineapplications.domain.lookup.Gender
import uk.ac.reigate.onlineapplications.repository.lookup.GenderRepository

@Service
class GenderService {
    
    @Autowired
    GenderRepository genderRepository
    
    /**
     * Default NoArgs constructor
     */
    GenderService() {}
    
    /**
     * Autowired Constructor
     *
     * @param genderRepository
     */
    GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }
    
    /**
     * Find an individual gender using the genders ID fields
     *
     * @param id the ID fields to search for
     * @return the Gender object that matches the ID supplied, or null if not found
     */
    Gender findById(Integer id) {
        return genderRepository.findById(id).orElse(null);
    }
    
    /**
     * Find all genders
     *
     * @return a SearchResult set with the list of Genders
     */
    List<Gender> findAll() {
        return genderRepository.findAll();
    }
    
    /**
     * This service method is used to save a complete Gender object in the database
     *
     * @param gender the new Gender object to be saved
     * @return the saved version of the Gender object
     */
    public Gender save(Gender gender) {
        return genderRepository.save(gender)
    }
    
    /**
     * Saves a list of Gender objects to the database
     *
     * @param genders a list of Genders to be saved to the database
     * @return the list of save Gender objects
     */
    @Transactional
    public List<Gender> saveList(List<Gender> genders) {
        return genders.collect { gender -> save(gender) };
    }
}
