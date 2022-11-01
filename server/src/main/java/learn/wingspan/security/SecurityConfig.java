package learn.wingspan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // SecurityFilterChain allows configuring
    // web based security for specific http requests.

    // JWT
    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    // new... add the parameter: `AuthenticationConfiguration authConfig`
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
        // we're not using HTML forms in our app
        //so disable CSRF (Cross Site Request Forgery)
        http.csrf().disable();

        // this configures Spring Security to allow
        //CORS related requests (such as preflight checks)
        http.cors();

        // the order of the antMatchers() method calls is important
        // as they're evaluated in the order that they're added
        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                // new
                .antMatchers("/create_account").permitAll()
                // new...
                .antMatchers("/refresh_token").authenticated()
                .antMatchers(HttpMethod.GET, "/api/sighting", "/api/sighting/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/sighting").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/sighting/*").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/sighting/*").hasAnyAuthority("USER" ,"ADMIN")
                .antMatchers(HttpMethod.GET, "/api/trait", "/api/trait/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/trait").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/trait/*").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/trait/*").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/bird", "/api/bird/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/bird").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/bird/*").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/bird/*").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/badge", "/api/badge/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/badge").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/badge/*").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/badge/*").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/avatar", "/api/avatar/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/avatar").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/avatar/*").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/avatar/*").hasAnyAuthority("ADMIN")
                .antMatchers("/**").denyAll()
                .and()
                // New...
                .addFilter(new JwtRequestFilter(authenticationManager(authConfig), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}