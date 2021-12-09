package uk.ac.reigate.onlineapplications.domain

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * This abstract class can be extended for an Entity to automatically provide an ID field that is an identity.
 * 
 * @author Michael Horgan
 *
 */
@MappedSuperclass
abstract class BaseEntityIdentity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    
    /**
     * No Args constructor for all BaseEntity classes
     */
    BaseEntityIdentity() { }
}
