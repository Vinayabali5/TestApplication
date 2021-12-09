package uk.ac.reigate.onlineapplications.dto.application

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty

import uk.ac.reigate.onlineapplications.domain.Application

class ApplicationDTO {

    @JsonProperty
    Integer id

    @JsonProperty("legalFirstName")
    String legalFirstName

    @JsonProperty("middleNames")
    String middleNames

    @JsonProperty("legalSurname")
    String legalSurname

    @JsonProperty("preferredName")
    String preferredName

    @JsonProperty("previousSurname")
    String previousSurname

    @JsonProperty("dateOfBirth")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Europe/London")
    Date dateOfBirth

    @JsonProperty("legalSex")
    Integer legalSexId

    @JsonProperty("gender")
    Integer genderId

    @JsonProperty("home")
    String home

    @JsonProperty("mobile")
    String mobile

    @JsonProperty("email")
    String email

    @JsonProperty
    Boolean resident

    @JsonProperty
    Boolean sibling

    @JsonProperty
    String siblingAdmNo

    @JsonProperty
    String siblingName

    @JsonProperty
    Integer siblingYear

    @JsonProperty("address")
    AddressDTO address

    @JsonProperty
    List<ContactDTO> contacts

    @JsonProperty("school")
    Integer schoolId

    @JsonProperty("_schoolDescription")
    String schoolDescription

    @JsonProperty("endDate")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Europe/London")
    Date schoolEndDate

    @JsonProperty
    Boolean schoolNotListed

    @JsonProperty
    String schoolName

    @JsonProperty
    AddressDTO schoolAddress

    @JsonProperty("courses")
    CourseRequestDTO[] courses

    @JsonProperty("_legalSexDescription")
    String legalSexDescription

    @JsonProperty("_genderDescription")
    String genderDescription

    @JsonProperty
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Europe/London")
    Date received

    /**
     * Default no-args constructor
     */
    ApplicationDTO() {}

    ApplicationDTO(Application app) {
        this.id = app.id

        this.legalFirstName = app?.person.legalFirstName
        this.legalSurname = app?.person.legalSurname
        this.middleNames = app?.person.middleNames
        this.preferredName = app?.person.preferredName
        this.previousSurname = app?.person.previousSurname
        this.home = app?.person.home
        this.mobile = app?.person.mobile
        this.email = app?.person.email
        this.resident = app?.resident

        this.sibling = app?.sibling
        this.siblingAdmNo = app.siblingAdmNo
        this.siblingName = app.siblingName
        this.siblingYear = app.siblingYear

        this.address = new AddressDTO(app?.person.address)
        this.legalSexId = app?.person.legalSex.id
        this.legalSexDescription = app?.person.legalSex.description
        this.genderId = app?.person.gender.id
        this.genderDescription = app?.person.gender.description
        this.dateOfBirth = app?.person.dateOfBirth

        this.contacts = ContactDTO.mapFromList(app?.person.contacts)

        this.schoolId = app?.school?.id
        this.schoolDescription = app?.school?.name
        this.schoolEndDate = app.schoolEndDate

        if (app.extraSchool != null) {
            this.schoolNotListed = true
            this.schoolName = app.extraSchool.schoolName
			this.schoolAddress = new AddressDTO(app.extraSchool)
        } else {
            this.schoolNotListed = false
        }

        this.courses = CourseRequestDTO.mapFromList(app.courses)

        this.received = app.received
    }
}
