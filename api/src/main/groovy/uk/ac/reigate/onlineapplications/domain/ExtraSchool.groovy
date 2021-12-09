package uk.ac.reigate.onlineapplications.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import groovy.transform.EqualsAndHashCode

@Entity
@Table(name = "student_extra_school", schema = "ONLINEAPPLICATION")
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields=true)
class ExtraSchool implements Serializable{

	@Id
	@JoinColumn(name = 'student_id')
	Integer studentId
	
    @Column(name = "schoolName")
    String schoolName

    @Column(name = "line1")
    String line1

    @Column(name = "line2")
    String line2

    @Column(name = "line3")
    String line3

    @Column(name = "town")
    String town

    @Column(name = "county")
    String county

    @Column(name = "postcode")
    String postcode
}

