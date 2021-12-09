package uk.ac.reigate.onlineapplications.service

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertFalse
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

import uk.ac.reigate.onlineapplications.domain.Address
import uk.ac.reigate.onlineapplications.domain.Person
import uk.ac.reigate.onlineapplications.domain.lookup.Title
import uk.ac.reigate.onlineapplications.dto.application.AddressDTO
import uk.ac.reigate.onlineapplications.repository.AddressRepository
import uk.ac.reigate.onlineapplications.service.lookup.TitleService

@ActiveProfiles("test")
@SpringBootTest
class AddressServiceTests {

	@MockBean
	AddressRepository addressRepository

	@Autowired
	AddressService addressService

	// Sample Data
    Address sampleAddress = new Address(id: 1, line1: 'Some Line', line2:'Another Line', town: 'Some Town', postcode: 'PO57CO')
    Address sampleAddress2 = new Address(id: 2, line1: 'Some Different Line', line2: 'More Different Line', town: 'Another Town', postcode: 'PD90SD')
    
    @BeforeEach
	public void setup() {
        when(addressRepository.findById(1)).thenReturn(new Optional(sampleAddress))
        when(addressRepository.findAll()).thenReturn(new ArrayList([sampleAddress, sampleAddress2]))
	}
    
    @Test 
    public void testFindById() {
        Address entity = addressService.findById(sampleAddress.id)
        
        verify(addressRepository, times(1)).findById(sampleAddress.id)
        
        assertEquals(entity.line1, sampleAddress.line1)
        assertEquals(entity.line2, sampleAddress.line2)
        assertEquals(entity.town, sampleAddress.town)
        assertEquals(entity.postcode, sampleAddress.postcode)
    }

    @Test 
    public void testFindById_invalidId() {
        Address entity = addressService.findById(999)
        
        verify(addressRepository, times(1)).findById(999)

        assertEquals(entity, null)
    }

    @Test 
    public void testFindAll() {
        List<Address> list = addressService.findAll()
        
        verify(addressRepository, times(1)).findAll()
        
        assertFalse(list.empty)
        assertEquals(list[0].line1, sampleAddress.line1)
        assertEquals(list[0].line2, sampleAddress.line2)
        assertEquals(list[0].town, sampleAddress.town)
        assertEquals(list[0].postcode, sampleAddress.postcode)
        assertEquals(list[1].line1, sampleAddress2.line1)
        assertEquals(list[1].line2, sampleAddress2.line2)
        assertEquals(list[1].town, sampleAddress2.town)
        assertEquals(list[1].postcode, sampleAddress2.postcode)
    }

}
