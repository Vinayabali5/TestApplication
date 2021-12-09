package uk.ac.reigate.onlineapplications.dto.security

import uk.ac.reigate.onlineapplications.domain.User

class JwtResponse {
    
    private final String token;
    private final User user;
    private final Long expirationDate;

    public JwtResponse(String token, User user, Long expirationDate) {
        this.token = token
        this.user = user
        this.expirationDate = expirationDate
    }

    public String getToken() {
        return this.token
    }

    public User getUser() {
        return this.user
    }
    
    public Long getExpirationDate() {
        return this.expirationDate
    }
}
