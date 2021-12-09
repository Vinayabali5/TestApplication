package uk.ac.reigate.onlineapplications.dto.lookup;


import groovy.transform.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import uk.ac.reigate.onlineapplications.domain.lookup.Gender

/**
 *
 * JSON serializable DTO containing Gender data
 *
 */
@JsonSerialize
@EqualsAndHashCode(includeFields=true)
public class GenderDTO implements Serializable {
    
    @JsonProperty
    private Integer id;
    
    @JsonProperty
    private String code;
    
    @JsonProperty
    private String description;
    
    /**
     * Default No Args constructor
     */
    GenderDTO() {
    }
    
    /**
     * Constructor to create a GenderDTO object from a Gender object
     *
     * @param gender the Gender object to use for construction
     */
    GenderDTO(Gender gender) {
        this.id = gender.id;
        this.code = gender.code;
        this.description = gender.description;
    }
    
    @Override
    public String toString() {
        return "GenderDto [id=" + id + ", code=" + code + ", description=" + description + "]";
    }
    
    public static GenderDTO mapFromEntity(Gender gender) {
        return new GenderDTO(gender);
    }
    
    public static List<GenderDTO> mapFromList(List<Gender> genders) {
        return genders.collect { gender ->  new GenderDTO(gender) };
    }
}
