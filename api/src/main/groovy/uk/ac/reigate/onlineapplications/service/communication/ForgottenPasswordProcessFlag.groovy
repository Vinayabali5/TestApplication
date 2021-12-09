package uk.ac.reigate.onlineapplications.service.communication

/**
 * This class is used to store references for the reset password flag. 
 * 
 * @author Michael Horgan
 *
 */
class ForgottenPasswordProcessFlag {
    /**
     * This flag is for when a password reset has been initialised.
     */
    static INIT = 1
    
    /**
     * This flag is used when a password reset email has been sent to the user. 
     */
    static SENT = 2
    
    /**
     * This flag is for when a password reset has been completed.
     */
    static COMPLETE = 0
}
