package uk.ac.reigate.exceptions


class ConfigurationException extends ApiException {
    
    ConfigurationException() {
        super("There is a problem with the configuration of the application.")
    }
    
    ConfigurationException(String message) {
        super(message)
    }
}
