package uk.ac.reigate.onlineapplications.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import uk.ac.reigate.exceptions.InvalidDataException
import uk.ac.reigate.onlineapplications.domain.Application
import uk.ac.reigate.onlineapplications.domain.Contact
import uk.ac.reigate.onlineapplications.domain.CourseRequest
import uk.ac.reigate.onlineapplications.domain.ExtraSchool
import uk.ac.reigate.onlineapplications.domain.Person
import uk.ac.reigate.onlineapplications.domain.lookup.Course
import uk.ac.reigate.onlineapplications.domain.lookup.School
import uk.ac.reigate.onlineapplications.dto.application.ApplicationDTO
import uk.ac.reigate.onlineapplications.dto.application.ContactDTO
import uk.ac.reigate.onlineapplications.dto.application.CourseRequestDTO
import uk.ac.reigate.onlineapplications.repository.ApplicationRepository
import uk.ac.reigate.onlineapplications.service.lookup.CourseService
import uk.ac.reigate.onlineapplications.service.lookup.GenderService
import uk.ac.reigate.onlineapplications.service.lookup.LegalSexService
import uk.ac.reigate.onlineapplications.service.lookup.SchoolService
import uk.ac.reigate.onlineapplications.service.lookup.TitleService

/**
 * This service is used to process the Applcation data.
 */
@Service
class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository

    @Autowired
    TitleService titleService

    @Autowired
    LegalSexService legalSexService

    @Autowired
    GenderService genderService

    @Autowired
    AddressService addressService

    @Autowired
    ContactService contactService

    @Autowired
    SchoolService schoolService

    @Autowired
    CourseService courseService

    @Autowired
    CourseRequestService courseRequestService

    /**
     * Default NoArgs constructor
     */
    ApplicationService( ) { }

    /**
     * Find an individual application using the applications ID fields
     *
     * @param id the ID fields to search for
     * @return the Application object that matches the ID supplied, or null if not found
     */
    @Transactional(readOnly = true)
    Application findById(Integer id) {
        return applicationRepository.findById(id).orElse(null)
    }

    /**
     * This service method is used to save a complete Application object in the database
     *
     * @param application the new Application object to be saved
     * @return the saved version of the Application object
     */
    @Transactional
    Application save(Application application) {
        return applicationRepository.save(application)
    }

    /**
     * Saves a list of Application objects to the database
     *
     * @param applications a list of Applications to be saved to the database
     * @return the list of save Application objects
     */
    @Transactional
    List<Application> saveApplications(List<Application> applications) {
        return applications.collect { application -> save( application ) };
    }

    /**
     * This method is used to convert an ApplicationDTO object to an Application entity.
     */
    Application createApplication(ApplicationDTO dto) {
        Application app = new Application()
        if (dto.id != null) {
            app = findById(dto.id)
        }
        app.received = new Date()

        // Validate the input data
        String validationMessage = ''
        if (dto.legalFirstName == null) { validationMessage += 'The legal first name cannot be set to null. ' }
        if (dto.legalSurname == null) { validationMessage += 'The legal surname cannot be set to null. ' }
        if (dto.dateOfBirth == null) { validationMessage += 'The date of birth field cannot be set to null. ' }
        if (validationMessage != '') { throw new InvalidDataException(validationMessage) }

        // Set application data
        // Personal Details
        app.person = new Person()
        app.person.legalFirstName = dto.legalFirstName
        app.person.legalSurname = dto.legalSurname
        app.person.middleNames = dto.middleNames
        app.person.preferredName = dto.preferredName
        app.person.dateOfBirth = dto.dateOfBirth
        app.person.legalSex = dto.legalSexId != null ? legalSexService.findById(dto.legalSexId) : null
        app.person.gender = dto.genderId != null ? genderService.findById(dto.genderId) : null
        app.person.home = dto.home
        app.person.mobile = dto.mobile
        app.person.email = dto.email
        app.resident = dto.resident

        // Sibling Details
        app.sibling = dto.sibling
        if (dto.sibling) {
            app.siblingAdmNo = dto.siblingAdmNo
            app.siblingName = dto.siblingName
            app.siblingYear = dto.siblingYear
        }

        // Address Details
        app.person.address = addressService.convertToEntity(dto.address)

        // Contact Details
        app.person.contacts = dto.contacts.collect { ContactDTO contact ->
            Contact out = contactService.convertToEntity(contact)
            out.person = app.person
            return out
        }

        // School Details
        app.schoolEndDate = dto.schoolEndDate

        // Courses
        app.courses = dto.courses.collect { CourseRequestDTO bc ->
            CourseRequest request = new CourseRequest()
            Course course = courseService.findById(bc.courseId)
            if (course != null) {
                request.application = app
                request.course = course
                request.requestCode = course.requestCode
                return request
            }
            return null
        }

		if (dto.schoolNotListed == false) {
			app.school = dto.schoolId != null ? schoolService.findById(dto.schoolId) : null
		} else {
			// Set to a DUMMY school where the school details are not in the system
			School dummySchool = schoolService.findById(1)
			if (dummySchool != null) {
				app.school = dummySchool
			} else {
				app.school = null
			}
		}
		
        // Performing Save to get an ID
        app = save(app)

        if (app.id != null && dto.schoolName != null) {
            // Adding extra school data
            app.extraSchool = new ExtraSchool(
                    studentId : app.id,
                    schoolName: dto.schoolName,
                    line1: dto.schoolAddress.line1,
                    line2: dto.schoolAddress.line2,
                    line3: dto.schoolAddress.line3,
                    town: dto.schoolAddress.town,
                    county: dto.schoolAddress.county,
                    postcode: dto.schoolAddress.postcode,
                    )
			app = save(app)
        }

        // Performing final save
        return app
    }

}
