package learn.wing_span.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AppUser implements UserDetails {
    private final String username;
    private final List<GrantedAuthority> authorities;
    private final List<Sighting> sightings = new ArrayList<>();
    private int appUserId;
    private String password;
    private boolean enabled;
    private String email;
    private String firstName;
    private String lastName;

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

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Do we need this?
//    public void setUsername(String username) {
//        this.username = username;
//    }

    @Override
    public String getUsername() {
        return username;
    }

    public List<Sighting> getSightings() {
        return sightings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public boolean equals(Object obj) {
        // If this is the object, return true.
        if (this == obj)
            return true;
        // Check that object is not null, nor is the object's class different from the current runtime class,
        // if either is true, return false.
        if (obj == null || getClass() != obj.getClass())
            return false;
        // Cast the object's type.
        AppUser appUser = (AppUser) obj;
        // Compare the fields between object and the forage.
        return (Objects.equals(username, appUser.username)
                && Objects.equals(password, appUser.password));
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
