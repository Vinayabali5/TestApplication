package uk.ac.reigate.onlineapplications.dto.lookup

import static org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

import uk.ac.reigate.onlineapplications.domain.lookup.ContactType

class ContactTypeDTOTests {

    @Test
    public void shouldConvertEntityToDTOClass() throws Exception {
        ContactType entity = new ContactType(id: 1, code: 'M', description: 'Mother')
        ContactTypeDTO dto = ContactTypeDTO.mapFromEntity(entity)
        
        assertEquals(entity.id, dto.id)
        assertEquals(entity.code, dto.code)
        assertEquals(entity.description, dto.description)
    }

}
