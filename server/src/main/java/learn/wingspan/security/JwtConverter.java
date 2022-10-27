package learn.wingspan.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import learn.wingspan.models.AppUser;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtConverter {

    // "Configurable" constants
    private final String ISSUER = "wingspane-api";
    private final int EXPIRATION_MINUTES = 15;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;
    // Signing key
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Take an instance of `AppUser` as a parameter, instead of `UserDetails`
    public String getTokenFromUser(AppUser user) {

        String authorities = user.getAuthorities().stream()
                .map(i -> i.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                // embed the `appUserId` in the JWT as a claim
                .claim("app_user_id", user.getAppUserId())
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key)
                .compact();
    }

    // Return an instance of `AppUser`
    public AppUser getUserFromToken(String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.substring(7));

            String username = jws.getBody().getSubject();
            // read the `appUserId` from the JWT body
            int appUserId = (int) jws.getBody().get("app_user_id");
            String authStr = (String) jws.getBody().get("authorities");

            // Replace the Spring Security `User` with our `AppUser`
            return new AppUser(appUserId, username, null, true,
                    Arrays.asList(authStr.split(",")));

        } catch (JwtException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}