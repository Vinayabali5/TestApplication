package uk.ac.reigate.onlineapplications.domain.lookup

import groovy.transform.EqualsAndHashCode

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import org.hibernate.annotations.Immutable

import uk.ac.reigate.onlineapplications.domain.BaseEntityIdentity
import uk.ac.reigate.onlineapplications.domain.CodedEntityIdentity

@Entity
@Immutable
@Table(name="gender")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "gender_id"))
])
@EqualsAndHashCode(includeFields=true)
class Gender extends CodedEntityIdentity {

    /**
     * Default No Args constructor
     */
    Gender(){}

    Gender(Integer id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    Gender(String code, String description) {
        this(null, code, description);
    }

    String toString() {
        return description
    }
}
