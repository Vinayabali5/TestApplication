package uk.ac.reigate.onlineapplications.domain.lookup

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Immutable

import groovy.transform.EqualsAndHashCode
import uk.ac.reigate.onlineapplications.domain.BaseEntityIdentity

@Entity
@Immutable
@Table(name = "school", schema = "ONLINEAPPLICATION")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "school_id"))
])
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields=true)
class School extends BaseEntityIdentity {
    
    @Column(name = "name", nullable = false)
    String name
    
    @Column(name = "urn", unique = true, nullable = false, columnDefinition = "VARCHAR(25)")
    String urn
    
    @Column(name = "line1")
    String line1

    @Column(name = "line2")
    String line2
    
    @Column(name = "line3")
    String line3
    
    @Column(name = "postcode")
    String postcode
    
    @Column(name = "partner_school", columnDefinition = "bit default (0)")
    Boolean partner
    
    /**
     * Default No Args constructor
     */
    School(){}
    
    School(Integer id, String name, Integer ukprn){
        this.id = id;
        this.name = name;
        this.urn = urn;
    }
    
    School(String name, Integer urn){
        this(null, name, urn)
    }
    
    public String toString(){
        return name;
    }
}
