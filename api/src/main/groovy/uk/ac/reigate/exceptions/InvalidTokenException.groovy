package uk.ac.reigate.exceptions


/**
 * This class is used to provide error handling for invalid authentication tokens.
 *  
 * @author Michael Horgan
 *
 */
class InvalidTokenException extends ApiException {
    
    InvalidTokenException() {
        super("A problem occurred with you authentication token.")
    }
    
    InvalidTokenException(String message) {
        super(message)
    }
}
