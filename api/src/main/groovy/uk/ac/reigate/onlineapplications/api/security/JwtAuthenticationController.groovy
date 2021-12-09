package uk.ac.reigate.onlineapplications.api.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import uk.ac.reigate.onlineapplications.domain.User
import uk.ac.reigate.onlineapplications.dto.security.JwtRequest
import uk.ac.reigate.onlineapplications.dto.security.JwtResponse
import uk.ac.reigate.onlineapplications.security.JwtTokenUtil

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @CrossOrigin("*")
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final User user = authenticate(authenticationRequest)
        final String token = JwtTokenUtil.generateToken(user)   
        final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(token)
        Long expirationDate = jwtTokenUtil.getExpirationDateFromToken().getTime()
        
        return ResponseEntity.ok(new JwtResponse(token, user, expirationDate))
    }
    
    /**
     * This method is used to authenticate a user based on a JwtRequest supplied. 
     * 
     * @param request a JwtRequest object containing the JWT token information.
     * @return if successful a User object will be returned
     * @throws Exception 
     */
    private User authenticate(JwtRequest request) throws Exception {
        User user = null;
        
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            user = (User)auth.getPrincipal();
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (AuthenticationException e) {
            throw new Exception("OTHER ERROR", e)
        }
        
        return user;
    }
}