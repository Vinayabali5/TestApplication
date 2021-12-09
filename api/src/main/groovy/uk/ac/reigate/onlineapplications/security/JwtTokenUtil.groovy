package uk.ac.reigate.onlineapplications.security

import java.util.function.Function

import org.springframework.security.core.userdetails.UserDetails

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import uk.ac.reigate.exceptions.InvalidTokenException
import uk.ac.reigate.onlineapplications.domain.User

public class JwtTokenUtil {

    private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;
    
    public static final String SECRET = "mysecret";
    
    private String token = null;
    
    public JwtTokenUtil(String jwtToken) {
        this.token = jwtToken;
    }    
    
    public String getUsernameFromToken() {
        final Claims claims = getAllClaimsFromToken();
        return claims.getSubject();
    }
    
    public Date getExpirationDateFromToken() {
        final Claims claims = getAllClaimsFromToken();
        return claims.getExpiration();
    }
    
    public <T> T getClaimFromToken(Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken();
        return claimsResolver.apply(claims);
    }
    
    public String getClaimFromTokenByName(String name) {
        final Claims claims = getAllClaimsFromToken();
        return (String)claims.get(name);
    }
    
    private Claims getAllClaimsFromToken() throws InvalidTokenException {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("Your authentication token has expired. Please try to re-login to the system")
        }
    }
    
    private Boolean isTokenExpired() {
        final Date expiration = getExpirationDateFromToken();
        return expiration.before(new Date());
    }
    
    public static String generateToken(User user) {
        Map<String, Object> claims = addClaims(user); 
        return doGenerateToken(claims, user.getUsername());
    }
    
    private static Map<String, Object> addClaims(User user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", user.getId());
        return claims;
    }   
    
    private static String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }
    
    public Boolean validateToken(UserDetails userDetails) {
        final String username = getUsernameFromToken();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired());
    }
    
    public Boolean validateToken() {
        return (!isTokenExpired());
    }
}
