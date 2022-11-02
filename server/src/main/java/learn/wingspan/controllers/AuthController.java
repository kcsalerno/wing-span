package learn.wingspan.controllers;

import learn.wingspan.domain.Result;
import learn.wingspan.models.AppUser;
import learn.wingspan.security.AppUserService;
import learn.wingspan.security.JwtConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    // The `AuthenticationManager` interface defines a single method `authenticate()`
    // that processes an Authentication request.
    private final AuthenticationManager authenticationManager;
    // New
    private final JwtConverter converter;
    // New
    private final AppUserService appUserService;

    // Updated for JWT
    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter,
                          AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        this.appUserService = appUserService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        // The `UsernamePasswordAuthenticationToken` class is an `Authentication` implementation
        // that is designed for simple presentation of a username and password.
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));

        try {
            // The `Authentication` interface Represents the token for an authentication request
            // or for an authenticated principal once the request has been processed by the //
            // `AuthenticationManager.authenticate(Authentication)` method.
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                // Added JWT, updated cast to AppUser instead of UserDetails
                String jwtToken = converter.getTokenFromUser((AppUser) authentication.getPrincipal());

                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);

                return new ResponseEntity<>(map, HttpStatus.OK);
            }

        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/refresh_token")
    // new... inject our `AppUser`, set by the `JwtRequestFilter`
    public ResponseEntity<Map<String, String>> refreshToken(@AuthenticationPrincipal AppUser appUser) {
        String jwtToken = converter.getTokenFromUser(appUser);
//        User user = new User(principal.getName(), principal.getName(), principal.getAuthorities());
//        String jwtToken = converter.getTokenFromUser(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("jwt_token", jwtToken);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody Map<String, String> credentials) {

        String username = credentials.get("username");
        String password = credentials.get("password");
        String email = credentials.get("email");
        int avatarId = Integer.parseInt(credentials.get("avatarId"));
        String avatarDescription = credentials.get("avatarDescription");
        String avatarImgageUrl = credentials.get("avatarImageUrl");

        Result<AppUser> result = appUserService.create(username, password, email, avatarId, avatarDescription, avatarImgageUrl);

        // unhappy path...
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }

        // happy path...
        HashMap<String, Integer> map = new HashMap<>();
        map.put("appUserId", result.getPayload().getAppUserId());

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}