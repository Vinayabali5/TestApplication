package uk.ac.reigate.onlineapplications.api.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import uk.ac.reigate.onlineapplications.dto.StatusDTO
import uk.ac.reigate.onlineapplications.dto.security.UsernameDTO
import uk.ac.reigate.onlineapplications.dto.security.UsernamePasswordCodeDTO
import uk.ac.reigate.onlineapplications.service.security.ForgottenPasswordService

@RestController
class ForgottenPasswordController {
    
    @Autowired
    ForgottenPasswordService forgottenPasswordService 
    
    @CrossOrigin("*")
    @PostMapping("/password-reset/initialise")
    public void initialisePasswordReset(@RequestBody UsernameDTO user) {
        forgottenPasswordService.initialisePasswordReset(user.username)
    }
    
    @CrossOrigin("*")
    @PostMapping("/password-reset/finalise")
    public ResponseEntity<?> finalisePasswordReset(@RequestBody UsernamePasswordCodeDTO username) {
        forgottenPasswordService.resetPassword(username)
        return new ResponseEntity<StatusDTO>(new StatusDTO("Successfully reset your password", "/password-reset/finalise"), HttpStatus.OK)
    }

}
