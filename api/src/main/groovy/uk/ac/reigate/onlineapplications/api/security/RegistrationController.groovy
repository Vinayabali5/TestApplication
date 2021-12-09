package uk.ac.reigate.onlineapplications.api.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import uk.ac.reigate.onlineapplications.dto.StatusDTO
import uk.ac.reigate.onlineapplications.dto.security.UsernamePasswordDTO
import uk.ac.reigate.onlineapplications.dto.security.UsernameDTO
import uk.ac.reigate.onlineapplications.service.security.RegistrationService
import uk.ac.reigate.onlineapplications.service.security.UserService

@RestController
class RegistrationController {
    
    @Autowired
    RegistrationService registrationService  
    
    @Autowired 
    UserService userService
    
    @CrossOrigin("*")
    @PostMapping("/register")
    public void registerUser(@RequestBody UsernamePasswordDTO registration) {
        registrationService.createUser(registration)
    }
    
    @CrossOrigin("*")
    @GetMapping("/confirm/{code}")
    public ResponseEntity<?> confirmUser(@PathVariable String code) {
        registrationService.confirmUser(code)
        StatusDTO output = new StatusDTO("Confirmation Successful", "/confirm/${code}")
        return ResponseEntity.ok().body(output)
    }
    
    @CrossOrigin("*")
    @PostMapping("/resend")
    public void resendRegistration(@RequestBody UsernameDTO resend) {
        registrationService.resendConfirmation(resend.username)
    }
}
