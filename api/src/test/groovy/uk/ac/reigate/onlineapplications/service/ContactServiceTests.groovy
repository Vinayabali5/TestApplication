package uk.ac.reigate.onlineapplications.service

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

import uk.ac.reigate.onlineapplications.domain.Contact
import uk.ac.reigate.onlineapplications.domain.Person
import uk.ac.reigate.onlineapplications.domain.lookup.ContactType
import uk.ac.reigate.onlineapplications.domain.lookup.Title
import uk.ac.reigate.onlineapplications.dto.application.ContactDTO
import uk.ac.reigate.onlineapplications.repository.ContactRepository
import uk.ac.reigate.onlineapplications.service.lookup.ContactTypeService
import uk.ac.reigate.onlineapplications.service.lookup.TitleService

@ActiveProfiles("test")
@SpringBootTest
class ContactServiceTests {

	@MockBean
	ContactRepository contactRepository

	@MockBean
	ContactTypeService contactTypeService

	@MockBean
	TitleService titleService

	@MockBean
	AddressService addressService

	@Autowired
	ContactService contactService

    // Sample Data
    Title sampleTitle = new Title(id:1, code: 'Mr', description: 'Mr')
    ContactType sampleContactType = new ContactType(id:1, code: 'F', description: 'Father')
    Contact sampleContact = new Contact(id: 1, type: sampleContactType, contact: new Person(title: sampleTitle, legalFirstName: 'John', legalSurname: 'Doe'))
    Contact sampleContact2 = new Contact(id: 2, type: sampleContactType, contact: new Person(title: sampleTitle, legalFirstName: 'James', legalSurname: 'Dean'))
    
    @BeforeEach
	public void setup() {
        when(contactRepository.findById(1)).thenReturn(new Optional(sampleContact))
        when(contactRepository.findAll()).thenReturn(new ArrayList([sampleContact, sampleContact2]))
	}

	ContactDTO sampleDTO() {
		return new ContactDTO(
				contactTypeId: 1,
				titleId: 1,
				firstName: 'John',
				surname: 'Doe',
				home: '01737221118',
				email: 'test@reigate.ac.uk'
				)
	}
    
    @Test 
    public void testFindById() {
        Contact entity = contactService.findById(sampleContact.id)
        
        verify(contactRepository, times(1)).findById(sampleContact.id)
        
        assertEquals(entity.type, sampleContact.type)
        assertEquals(entity.contact.title, sampleContact.contact.title)
        assertEquals(entity.contact.legalFirstName, sampleContact.contact.legalFirstName)
        assertEquals(entity.contact.legalSurname, sampleContact.contact.legalSurname)
        assertEquals(entity.contact.home, sampleContact.contact.home)
        assertEquals(entity.contact.email, sampleContact.contact.email)
    }

    @Test 
    public void testFindById_invalidId() {
        Contact entity = contactService.findById(999)
        
        verify(contactRepository, times(1)).findById(999)

        assertEquals(entity, null)
    }

    @Test 
    public void testFindAll() {
        List<Contact> list = contactService.findAll()
        
        verify(contactRepository, times(1)).findAll()
        
        assertEquals(list.empty, false)
        assertEquals(list[0].contact.title, sampleContact.contact.title)
        assertEquals(list[0].contact.legalFirstName, sampleContact.contact.legalFirstName)
        assertEquals(list[0].contact.legalSurname, sampleContact.contact.legalSurname)
        assertEquals(list[0].contact.home, sampleContact.contact.home)
        assertEquals(list[0].contact.email, sampleContact.contact.email)
    }

}
