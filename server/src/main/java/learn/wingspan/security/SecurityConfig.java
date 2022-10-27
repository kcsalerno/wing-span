package learn.wingspan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtConverter converter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtConverter converter, UserDetailsService userDetailsService) {
        this.converter = converter;
        this.userDetailsService = userDetailsService;
    }

    // SecurityFilterChain allows configuring
    // web based security for specific http requests.
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
                .antMatchers("/create_account").permitAll()
                .antMatchers("/refresh_token").authenticated()
                // Need to change all of these.
                .antMatchers(HttpMethod.GET,
                        "/").permitAll()
//                .antMatchers(HttpMethod.GET,
//                        "/api/solarpanel/section/*", "/api/solarpanel/*").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST,
//                        "/api/solarpanel").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers(HttpMethod.PUT,
//                        "/api/solarpanel/*").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers(HttpMethod.DELETE,
//                        "/api/solarpanel/*").hasAnyAuthority("ADMIN")


//                .antMatchers("/**").denyAll()


                .antMatchers("/**").permitAll()
                .and()
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
