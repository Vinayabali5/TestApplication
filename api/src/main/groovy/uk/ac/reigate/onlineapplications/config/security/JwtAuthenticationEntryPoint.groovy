package uk.ac.reigate.onlineapplications.config.security

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.security.authentication.DisabledException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter

import uk.ac.reigate.onlineapplications.dto.ErrorDTO

@Component
class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String message = "There was a problem with the authentication of the user. The error message returned was: %s"
        ErrorDTO error = new ErrorDTO(String.format(message, authException.getMessage()), authException.getMessage(), request.contextPath + request.servletPath)
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(error);
        if (authException instanceof DisabledException) {
            response.setStatus(403);
        } else {
            response.setStatus(401);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
