package uk.ac.reigate.onlineapplications.dto.application

import com.fasterxml.jackson.annotation.JsonProperty

import uk.ac.reigate.onlineapplications.domain.Contact

class ContactDTO {
	
	@JsonProperty
	Integer id
	
	@JsonProperty
	Integer contactTypeId
	
	@JsonProperty
	Integer titleId
	
	@JsonProperty
	String firstName
	
	@JsonProperty
	String surname
	
	@JsonProperty
	String home
	
	@JsonProperty
	String mobile
	
	@JsonProperty
	String email
	
	@JsonProperty
	AddressDTO address
	
    @JsonProperty
    Boolean sameAddress

	@JsonProperty
	Boolean contactable
	
	@JsonProperty
	Boolean preferred
    
    @JsonProperty(value = "_contactTypeDescription")
    String contactTypeDescription
    
    @JsonProperty(value = "_titleDescription")
    String titleDescription
    
    /**
     * Default no-args constructor
     */
    ContactDTO() {}
    
    ContactDTO(Contact contact) {
		this.id = contact.id
		this.contactTypeId = contact?.type.id
        this.contactTypeDescription = contact?.type?.description
		this.titleId = contact?.contact?.title.id
        this.titleDescription = contact?.contact?.title?.description
		this.firstName = contact?.contact.legalFirstName
		this.surname = contact?.contact.legalSurname
		this.home = contact?.contact.home
		this.mobile = contact?.contact.mobile
		this.email = contact?.contact.email
		this.address = contact?.contact.address != null ? new AddressDTO(contact?.contact.address) : null
        this.sameAddress = !contact.alternativeAddress        
		this.contactable = contact?.contactable
		this.preferred = contact?.preferred
    }
    
    public static List<ContactDTO> mapFromList(List<Contact> contacts) {
		return contacts.collect { Contact contact -> 
			new ContactDTO(contact)
		}
	}
}
