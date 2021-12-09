package uk.ac.reigate.exceptions


class UserNotFoundException extends ApiException {
    
    UserNotFoundException() {
        super("The user account cannot be found.")
    }
    
    UserNotFoundException(String message) {
        super(message)
    }
}
