package uk.ac.reigate.onlineapplications.domain

import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * This class is used to created a coded entity that has an id, code and description fields.
 * 
 * @author Michael Horgan
 *
 */
@MappedSuperclass
abstract class CodedEntity extends BaseEntity {

	@Column(name = "code", unique = true, nullable = false)
    String code
    
    @Column(name = "description", nullable = false)
    String description
    
}
