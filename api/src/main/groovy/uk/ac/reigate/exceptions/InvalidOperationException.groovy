package uk.ac.reigate.exceptions

class InvalidOperationException extends ApiException {
    
    InvalidOperationException() {
        super("Invalid Operation")
    }
    
    InvalidOperationException(String message) {
        super(message)
    }
}
