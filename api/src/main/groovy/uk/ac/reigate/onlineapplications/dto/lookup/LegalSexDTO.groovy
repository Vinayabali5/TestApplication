package uk.ac.reigate.onlineapplications.dto.lookup;


import groovy.transform.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import uk.ac.reigate.onlineapplications.domain.lookup.LegalSex

/**
 *
 * JSON serializable DTO containing LegalSex data
 *
 */
@JsonSerialize
@EqualsAndHashCode(includeFields=true)
public class LegalSexDTO implements Serializable {
    
    @JsonProperty
    private Integer id;
    
    @JsonProperty
    private String code;
    
    @JsonProperty
    private String description;
    
    /**
     * Default No Args constructor
     */
    LegalSexDTO() {
    }
    
    /**
     * Constructor to create a LegalSexDTO object from a LegalSex object
     *
     * @param legalSex the LegalSex object to use for construction
     */
    LegalSexDTO(LegalSex legalSex) {
        this.id = legalSex.id;
        this.code = legalSex.code;
        this.description = legalSex.description;
    }
    
    @Override
    public String toString() {
        return "LegalSexDto [id=" + id + ", code=" + code + ", description=" + description + "]";
    }
    
    public static LegalSexDTO mapFromEntity(LegalSex legalSex) {
        return new LegalSexDTO(legalSex);
    }
    
    public static List<LegalSexDTO> mapFromList(List<LegalSex> legalSexes) {
        return legalSexes.collect { legalSex ->  new LegalSexDTO(legalSex) };
    }
}
