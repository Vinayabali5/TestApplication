package uk.ac.reigate.onlineapplications.domain

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption

import groovy.transform.EqualsAndHashCode
import uk.ac.reigate.onlineapplications.domain.lookup.School

/**
 * This data entity is used to store the application data. 
 * 
 * @author Michael Horgan
 *
 */
@Entity
@Table(name="student")
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields = true)
class Application implements Serializable {
    
    @Id
    @GenericGenerator(name = 'StudentIdGenerator', strategy = 'uk.ac.reigate.onlineapplications.utils.StudentIdGenerator' )
    @GeneratedValue(generator = 'StudentIdGenerator' )
    @Column(name = 'student_id')
    Integer id
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = 'person_id', foreignKey = @ForeignKey(name = "FK_student__person"))
    Person person
    
    @Column(name = "resident", columnDefinition = "bit default 1")
    Boolean resident
    
    @Column(name = 'sibling', columnDefinition = "bit default 0")
    Boolean sibling
    
    @Column(name = 'sibling_adm_no')
    String siblingAdmNo
    
    @Column(name = 'sibling_name')
    String siblingName
    
    @Column(name = 'sibling_year')
    Integer siblingYear
        
	/**
	 * This field is the gender for the applicant.
	 */
	@ManyToOne
	@JoinColumn(name = 'school_id', foreignKey = @ForeignKey(name = "FK_student__school"))
	School school

	/**
	 * This field is the date the applicant will complete at their current/previous school.
	 */
	@Column(name = "school_end_date", columnDefinition = "date")
	Date schoolEndDate
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = 'student_id')
    ExtraSchool extraSchool

    /**
     * This relationship is collection of contacts for the applicant.
     */
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<CourseRequest> courses


    @Column(name = 'academic_year_id')
    Integer year = 22

    @Column(name = 'received')
    Date received = new Date()
    
	/**
	 * Default NoArgs constructor.
	 */
	Application(){}

	/**
	 * This method is the string representation of the application.
	 */
	String toString(){
		return "Application: " + this.id;
	}
}
