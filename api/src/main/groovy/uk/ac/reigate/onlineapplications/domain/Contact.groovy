package uk.ac.reigate.onlineapplications.domain

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import groovy.transform.EqualsAndHashCode
import uk.ac.reigate.onlineapplications.domain.lookup.ContactType

@Entity
@Table(name = "contact")
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields=true)
class Contact extends BaseEntityIdentity implements Serializable {
    
    /**
     * The Person for whom this is a contact for 
     */
    @OneToOne(cascade=[CascadeType.PERSIST,CascadeType.MERGE], fetch=FetchType.LAZY)
    @JoinColumn(name='person_id')
    Person person
    
    /**
     * The Person object that is the contact for the person 
     */
    @OneToOne(cascade=[CascadeType.PERSIST,CascadeType.MERGE], fetch=FetchType.EAGER)
    @JoinColumn(name='contact_id')
    Person contact
    
    /**
     * The ContactType of this particular contact
     */
    @OneToOne
    @JoinColumn(name='contact_type_id')
    ContactType type
    
    /**
     * This field is contactable status of the contact.
     */
    @Column(name = "contactable", nullable = true, columnDefinition = "bit default 1")
    Boolean contactable = true
    
    /**
     * This field is preferred status of the contact.
     */
    @Column(name = "preferred", nullable = false, columnDefinition = "bit default 0")
    Boolean preferred = false

    
    /**
     * This field is preferred status of the contact.
     */
    @Column(name = "alternative_address", nullable = false, columnDefinition = "bit default 0")
    Boolean alternativeAddress = false
    
        
    /**
     * Default NoArgs constructor.    
     */
    Contact(){}
    
    /**
     * This method is the string representation of the contact.
     */
    String toString() {
        return this.contact.legalSurname + ', ' + this.contact.legalFirstName
    }
}
