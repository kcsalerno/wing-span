package learn.wingspan.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import learn.wingspan.models.AppUser;
import learn.wingspan.models.Avatar;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Component
public class JwtConverter {
    private final String ISSUER = "wingspan";
    // Temporarily making token refresh a long time. Will come back to refresh later.
    private final int EXPIRATION_MINUTES = 60;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String getTokenFromUser(AppUser user) {

        String authorities = user.getAuthorities().stream()
                .map(i -> i.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                .claim("app_user_id", user.getAppUserId())
                .claim("email", user.getEmail())
                .claim("avatar", user.getAvatar())
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key)
                .compact();
    }

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
            int appUserId = (int) jws.getBody().get("app_user_id");
            String email = (String) jws.getBody().get("email");

            Object nestedAvatar = jws.getBody().get("avatar");
            LinkedHashMap avatarHashMap = (LinkedHashMap) nestedAvatar;

            int avatarId = (Integer) avatarHashMap.get("avatarId");
            String avatarImageUrl = (String) avatarHashMap.get("avatarImageUrl");
            String avatarDescription = (String) avatarHashMap.get("avatarDescription");

            Avatar avatar = new Avatar(avatarId, avatarImageUrl, avatarDescription);

            String authStr = (String) jws.getBody().get("authorities");

            AppUser appUser = new AppUser(appUserId, username, null, true, email,
                    Arrays.asList(authStr.split(",")));

            appUser.setAvatar(avatar);

            return appUser;

        } catch (JwtException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}