package uk.ac.reigate.onlineapplications.service.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import uk.ac.reigate.onlineapplications.domain.User
import uk.ac.reigate.onlineapplications.repository.UserRepository

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired 
    UserService userService 
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
        if (user == null) {
            throw new UsernameNotFoundException("The username suuplied cannot be found. Please register for a user account.")
        }
        return user;
    }
    
    
}