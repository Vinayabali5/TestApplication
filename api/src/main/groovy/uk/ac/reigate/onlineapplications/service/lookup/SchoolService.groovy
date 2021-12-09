package uk.ac.reigate.onlineapplications.service.lookup

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import uk.ac.reigate.onlineapplications.domain.lookup.School
import uk.ac.reigate.onlineapplications.repository.lookup.SchoolRepository


@Service
class SchoolService {
    
    @Autowired
    SchoolRepository schoolRepository
    
    /**
     * Default NoArgs constructor
     */
    SchoolService() {}
    
    /**
     * Autowired Constructor
     *
     * @param schoolRepository
     */
    SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }
    
    /**
     * Find an individual school using the schools ID fields
     *
     * @param id the ID fields to search for
     * @return the School object that matches the ID supplied, or null if not found
     */
    @Transactional(readOnly = true)
    School findById(Integer id) {
        return schoolRepository.findById(id).orElse(null);
    }
    
    /**
     * Find all schools
     *
     * @return a SearchResult set with the list of Schools
     */
    @Transactional(readOnly = true)
    List<School> findAll() {
        return schoolRepository.findAll();
    }

    /**
     * This service method is used to save a complete School object in the database
     *
     * @param school the new School object to be saved
     * @return the saved version of the School object
     */
    @Transactional
    public School save(School school) {
        return schoolRepository.save(school)
    }
    
    /**
     * Saves a list of School objects to the database
     *
     * @param schools a list of Schools to be saved to the database
     * @return the list of save School objects
     */
    @Transactional
    public List<School> saveSchools(List<School> schools) {
        return schools.collect { school -> save( school ) };
    }
    
}
