package learn.wingspan.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import learn.wingspan.models.AppUser;
import learn.wingspan.models.Avatar;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtConverter {

    // 2. "Configurable" constants
    private final String ISSUER = "wingspan";
    // Temporarily making token refresh a ridiculously long time. Will come back to refresh later.
    private final int EXPIRATION_MINUTES = 15;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;
    // 1. Signing key
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Take an instance of `AppUser` as a parameter, instead of `UserDetails`
    public String getTokenFromUser(AppUser user) {

        String authorities = user.getAuthorities().stream()
                .map(i -> i.getAuthority())
                .collect(Collectors.joining(","));

        // 3. Use JJWT classes to build a token.
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                // new... embed the `appUserId` in the JWT as a claim
                .claim("app_user_id", user.getAppUserId())
                .claim("email", user.getEmail())
                .claim("avatar", user.getAvatar())
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
            // 4. Use JJWT classes to read a token.
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.substring(7));

            String username = jws.getBody().getSubject();
            // new... read the `appUserId` from the JWT body
            int appUserId = (int) jws.getBody().get("app_user_id");
            String email = (String) jws.getBody().get("email");

            // This is a mess. I might just change it so that AppUser has only the avatar ID
            // and make do a GET request for findAvatarById within the client
            Object nestedAvatar = jws.getBody().get("avatar");
            LinkedHashMap avatarHashMap = (LinkedHashMap) nestedAvatar;

            int avatarId = (Integer) avatarHashMap.get("avatarId");
            String avatarImageUrl = (String) avatarHashMap.get("avatarImageUrl");
            String avatarDescription = (String) avatarHashMap.get("avatarDescription");

            Avatar avatar = new Avatar(avatarId, avatarImageUrl, avatarDescription);

            String authStr = (String) jws.getBody().get("authorities");

//            List<SimpleGrantedAuthority> roles = Arrays.stream(authStr.split(","))
//                    .map(r -> new SimpleGrantedAuthority(r))
//                    .collect(Collectors.toList());

            // Replace the Spring Security `User` with our `AppUser`
            AppUser appUser = new AppUser(appUserId, username, null, true, email,
                    Arrays.asList(authStr.split(",")));

            appUser.setAvatar(avatar);

            return appUser;

        } catch (JwtException ex) {
            // 5. JWT failures are modeled as exceptions.
            System.out.println(ex.getMessage());
        }

        return null;
    }
}