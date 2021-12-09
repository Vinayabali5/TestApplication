package uk.ac.reigate.onlineapplications.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import uk.ac.reigate.onlineapplications.api.IDTOToEntityConverter
import uk.ac.reigate.onlineapplications.domain.Contact
import uk.ac.reigate.onlineapplications.domain.Person
import uk.ac.reigate.onlineapplications.dto.application.ContactDTO
import uk.ac.reigate.onlineapplications.repository.ContactRepository
import uk.ac.reigate.onlineapplications.service.lookup.ContactTypeService
import uk.ac.reigate.onlineapplications.service.lookup.TitleService

@Service
class ContactService implements IDTOToEntityConverter<ContactDTO, Contact> {

    @Autowired
    ContactRepository contactRepository

    @Autowired
    ContactTypeService contactTypeService

    @Autowired
    TitleService titleService

    @Autowired
    AddressService addressService

    /**
     * Default NoArgs constructor
     */
    ContactService() {}

    /**
     * Autowired Constructor
     *
     * @param contactRepository
     */
    ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Find an individual contact using the contacts ID fields
     *
     * @param id the ID fields to search for
     * @return the Contact object that matches the ID supplied, or null if not found
     */
    Contact findById(Integer id) {
        return contactRepository.findById(id).orElse(null);
    }

    /**
     * Find all contacts
     *
     * @return a SearchResult set with the list of Contacts
     */
    List<Contact> findAll() {
        return contactRepository.findAll();
    }

    /**
     * This service method is used to save a complete Contact object in the database
     *
     * @param contact the new Contact object to be saved
     * @return the saved version of the Contact object
     */
    public Contact save(Contact contact) {
        return contactRepository.save(contact)
    }

    /**
     * Saves a list of Contact objects to the database
     *
     * @param contacts a list of Contacts to be saved to the database
     * @return the list of save Contact objects
     */
    @Transactional
    public List<Contact> saveContacts(List<Contact> contacts) {
        return contacts.collect { contact -> save(contact) };
    }

    /**
     * This method is used to convert a ContactDTO object into a Contact entity.
     */
    public Contact convertToEntity(ContactDTO dto) {
        Contact contact = new Contact()
        if (dto.id != null) {
            contact = findById(dto.id)
        }
        contact.type = dto.contactTypeId != null ? contactTypeService.findById(dto.contactTypeId) : null
		contact.contactable = dto.contactable
		contact.preferred = dto.preferred
        contact.contact = new Person()
        contact.contact.title = dto.titleId != null ? titleService.findById(dto.titleId) : null
        contact.contact.legalFirstName = dto.firstName
        contact.contact.legalSurname = dto.surname
        contact.contact.home = dto.home
        contact.contact.mobile = dto.mobile
        contact.contact.email = dto.email
        if (dto.address != null ) {
            contact.contact.address = addressService.convertToEntity(dto.address)
            contact.alternativeAddress = !dto.sameAddress
        }
        return contact
    }

    public List<Contact> convertListToEntities(List<ContactDTO> contacts) {
        return contacts.collect { ContactDTO contact ->
            convertToEntity(contact)
        }
    }
}
