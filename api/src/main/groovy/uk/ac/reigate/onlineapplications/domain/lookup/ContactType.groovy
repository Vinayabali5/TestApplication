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
import uk.ac.reigate.onlineapplications.domain.CodedEntityIdentity


@Entity
@Immutable
@Table(name = "contact_type")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "contact_type_id"))
])
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields=true)
class ContactType extends CodedEntityIdentity implements Serializable {

    /**
     * Default No Args constructor
     */
    ContactType(){}

    String toString() {
        return description
    }
}
