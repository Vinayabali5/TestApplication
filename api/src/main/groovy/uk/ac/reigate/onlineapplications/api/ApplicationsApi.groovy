package uk.ac.reigate.onlineapplications.api

import java.security.Principal

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

import uk.ac.reigate.exceptions.InvalidOperationException
import uk.ac.reigate.exceptions.NotFoundException
import uk.ac.reigate.onlineapplications.domain.Application
import uk.ac.reigate.onlineapplications.domain.Debug
import uk.ac.reigate.onlineapplications.domain.User
import uk.ac.reigate.onlineapplications.dto.application.ApplicationDTO
import uk.ac.reigate.onlineapplications.service.ApplicationService
import uk.ac.reigate.onlineapplications.service.DebugService
import uk.ac.reigate.onlineapplications.service.security.UserService

@Controller
class ApplicationsApi {

    @Autowired
    ApplicationService applicationService

    @Autowired
    UserService userService
	
	@Autowired
	DebugService debugService

    @CrossOrigin("*")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/applications")
    ResponseEntity<ApplicationDTO> createApplication(Principal principal, @RequestBody(required = true) ApplicationDTO applicationForm) {
        // Verify that data sent does not have an ID
        if (applicationForm.id != null) throw new InvalidOperationException("You cannot create a new application if an ID fields has been supplied. Use PUT /applications for updating an appliction.")
        // Create and save application
        Application application = applicationService.createApplication(applicationForm)
        // Link Application to User
        User user = userService.findByUsername(principal.name)
        user.application = application
        user.receiptSent = null
        userService.save(user)
        // Send Response
        return new ResponseEntity<ApplicationDTO>(new ApplicationDTO(application), HttpStatus.CREATED)
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/applications")
    ResponseEntity<ApplicationDTO> getApplication(Principal principal) {
        User user = userService.findByUsername(principal.name)
        if (user.application == null) {
            throw new NotFoundException("The current user has not completed an application form.")
        }
        return new ResponseEntity<ApplicationDTO>(new ApplicationDTO(user.application), HttpStatus.OK)
    }
	
	/**
	 * This api endpoint is used to capture the data of the unsaved application.
	 * @param debugData
	 * @return
	 */
	@PostMapping("/applications/debug")
	ResponseEntity<?> createDebug(@RequestBody(required = true) String debugData) {	
			if(debugData == null) throw new InvalidOperationException("You cannot create a debug report with the null object")
				debugService.create(debugData)
			return new ResponseEntity<?>(HttpStatus.CREATED)		
		}
}
