package uk.ac.reigate.onlineapplications.exceptionhandling

import javax.mail.AuthenticationFailedException

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

import uk.ac.reigate.onlineapplications.dto.ErrorDTO

@ControllerAdvice
class EmailExceptionHandler {

    @ExceptionHandler(value = AuthenticationFailedException.class)
    public ResponseEntity<ErrorDTO> handleEmailServerLogin(RuntimeException ex, WebRequest request) {
        String error = "The username and password supplied for the SMTP server is not valid."
        return new ResponseEntity(new ErrorDTO(error, ex.message, request.contextPath), HttpStatus.NOT_ACCEPTABLE)
    }
    

}
