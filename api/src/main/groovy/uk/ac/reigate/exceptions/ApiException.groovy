package uk.ac.reigate.exceptions

/**
 * This abstract class is used to provide a base object for all exceptions thrown by the API. 
 * 
 * @author Michael Horgan
 *
 */
abstract class ApiException extends RuntimeException {
    
	/**
	 * A constructor that requires a message string. 
	 * 
	 * @param message the message string to use for the exception.
	 */
    public ApiException(String message) {
        super(message)
    }
}
