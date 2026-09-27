package delivary.foodapp.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static final String SECRET_KEY ="MySuperSecreteKey12345#129v51234yfufuy";

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(getSignedKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignedKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

   public String extractExpiration(String token){
        return extractClaim(token, Claims::getSubject);
   }
    public Date extractUsername(String token) {
        return extractClaim(token , Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignedKey())
                .parseClaimsJws(token)
                .getBody();

    }
    public boolean isTokenExpired(String token) {
        try {
            final Date expiration = extractAllClaims(token).getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true; // If we can't parse it, treat it as expired
        }
    }
    public boolean validateToken(String token, UserDetails userDetails){

        return !isTokenExpired(token);
    }
}
