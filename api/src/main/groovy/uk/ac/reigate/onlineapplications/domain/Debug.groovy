package uk.ac.reigate.onlineapplications.domain

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert

import groovy.transform.EqualsAndHashCode

@Entity
@Table(name="debug", schema = "ONLINEAPPLICATION")
@AttributeOverrides([
	@AttributeOverride(name = "id", column = @Column(name = "debug_id"))
])
@DynamicInsert(true)
@EqualsAndHashCode(includeFields=true)
class Debug extends BaseEntityIdentity implements Serializable {
		
	@Column(name = "data")
	String data
	
}
