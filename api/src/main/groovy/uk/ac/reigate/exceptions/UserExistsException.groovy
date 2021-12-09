package uk.ac.reigate.exceptions

class UserExistsException extends ApiException {
        
    UserExistsException() {
        super("User alreadyt exists.")
    }
    
    UserExistsException(String message) {
        super(message)
    }

}
