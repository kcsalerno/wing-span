package learn.wing_span.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppUser implements UserDetails {
    private int appUserId;
    private final String username;
    private String password;
    private boolean enabled;
    private String email;
    private String firstName;
    private String lastName;
    private List<Sighting> sightings = new ArrayList<>();
    private final List<GrantedAuthority> authorities;

    public AppUser(int appUserId, String username, String password, boolean enabled, List<String> roles) {
        this.appUserId = appUserId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
//        this.email = email;
//        this.firstName = user_first_name;
//        this.lastName = user_last_name;
        this.authorities = convertRolesToAuthorities(roles);
    }

    private static List<GrantedAuthority> convertRolesToAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    // Do we need these?
//    public void setUsername(String username) {
//        this.username = username;
//    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Sighting> getSightings() {
        return sightings;
    }

    // Do we need these?
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

//    public void setAuthorities(List<GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    // Do we need this?
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
