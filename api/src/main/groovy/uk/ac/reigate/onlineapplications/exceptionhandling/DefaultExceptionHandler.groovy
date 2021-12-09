package uk.ac.reigate.onlineapplications.exceptionhandling

import javax.net.ssl.SSLHandshakeException

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

import io.jsonwebtoken.ExpiredJwtException
import uk.ac.reigate.exceptions.InvalidDataException
import uk.ac.reigate.exceptions.InvalidOperationException
import uk.ac.reigate.exceptions.InvalidTokenException
import uk.ac.reigate.exceptions.NotFoundException
import uk.ac.reigate.exceptions.UserExistsException
import uk.ac.reigate.exceptions.UserNotFoundException
import uk.ac.reigate.onlineapplications.dto.ErrorDTO

@ControllerAdvice
class DefaultExceptionHandler {
    
    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<ErrorDTO> handleInvalidData(RuntimeException ex, WebRequest request) {
        String error = "Invalid Data Supplied."
        return new ResponseEntity(new ErrorDTO(error, ex.message, request.contextPath), HttpStatus.NOT_ACCEPTABLE)
    }
    
    @ExceptionHandler(value = InvalidOperationException.class)
    public ResponseEntity<ErrorDTO> handleInvalidOperation(RuntimeException ex, WebRequest request) {
        String error = "Invalid Operation."
        return new ResponseEntity(new ErrorDTO(ex.message, error, request.contextPath), HttpStatus.NOT_ACCEPTABLE)
    }

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDTO> handleMissingData(RuntimeException ex, WebRequest request) {
		String error = "There was a problem processing the data supplied or no data was supplied."
		return new ResponseEntity(new ErrorDTO(ex.message, error, request.contextPath), HttpStatus.NOT_ACCEPTABLE)
	}

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> handleDataIntegrityViolationException(RuntimeException ex, WebRequest request) {
        String error = "An error has occured due to data integrity issues."
        return new ResponseEntity(new ErrorDTO(ex.message, error, request.contextPath), HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler(value = UserExistsException.class)
    public ResponseEntity<ErrorDTO> handleApiException(RuntimeException ex, WebRequest request) {
        String error = "A user already exists with the supplied username."
        return new ResponseEntity(new ErrorDTO(ex.message, error, request.contextPath), HttpStatus.CONFLICT)
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFound(RuntimeException ex, WebRequest request) {
        String error = "User not found in database."
        return new ResponseEntity(new ErrorDTO(ex.message, error, request.contextPath), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(RuntimeException ex, WebRequest request) {
        String error = "Data not found."
        return new ResponseEntity(new ErrorDTO(ex.message, error, request.contextPath), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = SSLHandshakeException.class)
    public ResponseEntity<ErrorDTO> handleSSLProblem(RuntimeException ex, WebRequest request) {
        String error = "There is a problem with the configuration of your server. The SSL certificate chain is either not valid of something went wrong with the SSL handshake."
        return new ResponseEntity(new ErrorDTO(ex.message, error, request.contextPath), HttpStatus.INTERNAL_SERVER_ERROR)
    }
    
    @ExceptionHandler(value = [ExpiredJwtException.class, InvalidTokenException.class])
    public ResponseEntity<ErrorDTO> handleExpiredJwtException(RuntimeException ex, WebRequest request) {
        String error = "Your login token has expired."
        return new ResponseEntity(new ErrorDTO(ex.message, error, request.contextPath), HttpStatus.UNAUTHORIZED)
    }
    
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException ex, WebRequest request) {
        String error = "An error occurred."
        return new ResponseEntity(new ErrorDTO(ex.message, error, request.contextPath), HttpStatus.INTERNAL_SERVER_ERROR)
    }

}

