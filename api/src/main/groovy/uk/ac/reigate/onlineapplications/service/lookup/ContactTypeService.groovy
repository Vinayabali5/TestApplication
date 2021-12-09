package uk.ac.reigate.onlineapplications.service.lookup

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.ValidationUtils

import uk.ac.reigate.onlineapplications.domain.lookup.ContactType
import uk.ac.reigate.onlineapplications.repository.lookup.ContactTypeRepository

@Service
class ContactTypeService {
    
    @Autowired
    ContactTypeRepository contactTypeRepository
    
    /**
     * Default NoArgs constructor
     */
    ContactTypeService() {}
    
    /**
     * Autowired Constructor
     *
     * @param contactTypeRepository
     */
    ContactTypeService(ContactTypeRepository contactTypeRepository){
        this.contactTypeRepository = contactTypeRepository;
    }
    
    /**
     * Find an individual contactType using the contactTypes ID fields
     *
     * @param id the ID fields to search for
     * @return the ContactType object that matches the ID supplied, or null if not found
     */
    ContactType findById(Integer id) {
        return contactTypeRepository.findById(id).orElse(null);
    }
    
    /**
     * Find all contactTypes
     *
     * @return a List of ContactTypes
     */
    List<ContactType> findAll() {
        return contactTypeRepository.findAll();
    }
    
}
