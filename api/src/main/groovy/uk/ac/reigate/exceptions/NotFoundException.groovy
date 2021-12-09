package uk.ac.reigate.exceptions;

/**
 * This exception is used when the user requests data that cannot be found in the system. 
 * 
 * @author Michael Horgan
 *
 */
public class NotFoundException extends ApiException {
    
	/**
	 * The default constructor to use if not alternative message is supplied. 
	 */
    public NotFoundException() {
        super("Not Found");
    }
    
    /**
     * A constructor to use if an alternative message is supplied.
     */
    public NotFoundException(String message) {
        super(message);
    }
    
    
}
