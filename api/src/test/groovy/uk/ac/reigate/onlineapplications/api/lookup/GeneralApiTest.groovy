package uk.ac.reigate.onlineapplications.api.lookup

import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.security.core.userdetails.UserDetailsService

import uk.ac.reigate.onlineapplications.config.security.JwtAuthenticationEntryPoint

@Import(value = [JwtAuthenticationEntryPoint.class])
class GeneralApiTest {
    
    @MockBean
    UserDetailsService userDetailsService    
}
