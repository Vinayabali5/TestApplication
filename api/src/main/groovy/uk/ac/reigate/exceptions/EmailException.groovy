package uk.ac.reigate.exceptions


class EmailException extends ApiException {
    
    EmailException() {
        super("Problem creating email message.")
    }
    
    EmailException(String message) {
        super(message)
    }
}
