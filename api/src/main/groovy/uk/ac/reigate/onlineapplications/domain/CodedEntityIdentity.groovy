package uk.ac.reigate.onlineapplications.domain

import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * This class is used to created a coded entity with an identity that has an id, code and description fields.
 * 
 * @author Michael Horgan
 *
 */
@MappedSuperclass
abstract class CodedEntityIdentity extends BaseEntityIdentity {

	@Column(name = "code", unique = true, nullable = false)
    public String code
    
	@Column(name = "description", nullable = false)
    public String description
    
}