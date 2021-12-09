package uk.ac.reigate.onlineapplications.domain

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import groovy.transform.EqualsAndHashCode
import uk.ac.reigate.onlineapplications.domain.lookup.Course

@Entity
@Table(name = "request")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "request_id"))
])
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields=true)
class CourseRequest extends BaseEntityIdentity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false, foreignKey = @ForeignKey(name = "FK_course_request__student"))
	Application application


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "FK_course_request__course"))
	Course course

    @Column(name = "request")
    String requestCode
    
}
