package uk.ac.reigate.onlineapplications.domain

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption

import uk.ac.reigate.onlineapplications.domain.lookup.Gender
import uk.ac.reigate.onlineapplications.domain.lookup.LegalSex
import uk.ac.reigate.onlineapplications.domain.lookup.Title

@Entity
@Table(name = "person")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "person_id"))
])
class Person extends BaseEntityIdentity {
    
    /**
     * This field is the title of the applicant.
     */
    @OneToOne
    @JoinColumn(name = 'title_id')
    Title title

    /**
     * This field is the legal first name for the applicant.
     */
    @Column(name = "first_name", nullable = false)
    String legalFirstName

    /**
     * This field is the legal surname for the applicant.
     */
    @Column(name = "legal_surname", nullable = false)
    String legalSurname

    /**
     * This field is the legal surname for the applicant.
     */
    @Column(name = "middle_names")
    String middleNames

    /**
     * This field is the preferred name for the applicant.
     */
    @Column(name = "preferred_name")
    String preferredName

    /**
     * This field is the preferred name for the applicant.
     */
    @Column(name = "previous_surname")
    String previousSurname

    /**
     * This field is the date of birth for the applicant.
     */
    @Column(name = "dob", columnDefinition = "date")
    Date dateOfBirth

    /**
     * This field is the legal sex for the applicant.
     */
    @ManyToOne
    @JoinColumn(name = 'legal_sex_id')
    LegalSex legalSex

    /**
     * This field is the gender for the applicant.
     */
    @ManyToOne
    @JoinColumn(name = 'gender_id')
    Gender gender

    /**
     * THis fields is the home telephone number for the applicant.
     */
    @Column(name = "home")
    String home

    /**
     * This field is the mobile telephone number for the applicant.
     */
    @Column(name = "mobile")
    String mobile

    /**
     * This field is the email address for the applicant.
     */
    @Column(name = "email")
    String email

    /**
     * This field is the address for the applicant.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = 'address_id')
    Address address

    /**
     * This relationship is collection of contacts for the applicant.
     */
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval=true)
//    @JoinTable(
//        joinColumns= @JoinColumn(name = "contact_id", referencedColumnName = "contact_id"),
//        inverseJoinColumns= @JoinColumn(name = "person_id", referencedColumnName="ID")
//    )
//    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Contact> contacts


}
