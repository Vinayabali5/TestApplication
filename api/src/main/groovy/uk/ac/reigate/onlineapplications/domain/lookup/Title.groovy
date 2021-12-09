package uk.ac.reigate.onlineapplications.domain.lookup

import groovy.transform.EqualsAndHashCode

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Immutable

import uk.ac.reigate.onlineapplications.domain.BaseEntityIdentity
import uk.ac.reigate.onlineapplications.domain.CodedEntityIdentity

/**
 * <p>This class is a data object used to represent the Title for a person.</p>
 * <p>The core fields for this class are:
 * <ul>
 * <li>id</li>
 * <li>code</li>
 * <li>description</li>
 * </ul>
 * </p>
 *
 * @author Michael Horgan
 *
 */
@Entity
@Immutable
@Table(name = "title")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "title_id"))
])
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields=true)
class Title extends CodedEntityIdentity {

	@Column(name = "allow_student")
	Boolean allowStudent

    Title(){}

    Title(Integer id, String code, String description){
        this.id = id;
        this.code = code;
        this.description = description
    }

    Title(String code, String description){
        this(null, code, description)
    }

    String toString() {
        return description
    }
}
