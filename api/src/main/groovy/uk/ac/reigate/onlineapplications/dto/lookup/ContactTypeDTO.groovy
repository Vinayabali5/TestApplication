package uk.ac.reigate.onlineapplications.dto.lookup;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import groovy.transform.EqualsAndHashCode;
import uk.ac.reigate.onlineapplications.domain.lookup.ContactType

/**
 *
 * JSON serializable DTO containing ContactType data
 *
 */
@JsonSerialize
@EqualsAndHashCode(includeFields=true)
public class ContactTypeDTO implements Serializable {
    
    @JsonProperty
    private Integer id;
    
    @JsonProperty
    private String code;
    
    @JsonProperty
    private String description;
    
    /**
     * Default No Args constructor
     */
    public ContactTypeDTO() {
    }
    
    /**
     * Constructor to create a ContactTypeDto object from a ContactType object
     *
     * @param contactType the ContactType object to use for construction
     */
    ContactTypeDTO(ContactType contactType) {
        this.id = contactType.id;
        this.code = contactType.code;
        this.description = contactType.description;
    }
    
    @Override
    public String toString() {
        return "ContactTypeDto [id=" + id + ", name=" + name + "]";
    }
    
    public static ContactTypeDTO mapFromEntity(ContactType contactType) {
        return new ContactTypeDTO(contactType);
    }
    
    public static List<ContactTypeDTO> mapFromList(List<ContactType> contactTypes) {
        return contactTypes.collect { contactType ->  new ContactTypeDTO(contactType) };
    }
}
