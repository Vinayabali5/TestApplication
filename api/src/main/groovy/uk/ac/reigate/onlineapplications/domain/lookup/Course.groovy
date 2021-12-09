package uk.ac.reigate.onlineapplications.domain.lookup;

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import org.hibernate.annotations.Immutable

import groovy.transform.EqualsAndHashCode
import uk.ac.reigate.onlineapplications.domain.BaseEntityIdentity;

/**
 * This entity class is used to represent the available courses that applicant can pick from when applying.
 * 
 * @author Michael Horgan
 *
 */
@Entity
@Immutable
@Table(name = "course", schema = "ONLINEAPPLICATION")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "course_id"))
])
@EqualsAndHashCode(includeFields=true)
public class Course extends BaseEntityIdentity {

    /**
     * This field is the title of the Course.
     */
	@Column(columnDefinition = "varchar(500)")
	String title
	
    /**
     * This field is the summary of the Course.
     */
	@Column(columnDefinition = "varchar(max)")
	String summary
    
	/**
	 * This field is the Course's entry requirements.
	 */
	@Column(columnDefinition = "varchar(500)")
	String entryRequirements
	
    /**
     * This field is the external link to the College's website. 
     */
    @Column(columnDefinition = "varchar(500)")
    String externalLink 

    /**
     * This field is the request code for the selected course. 
     */
    @Column(columnDefinition = "varchar(20)")
    String requestCode 

}
