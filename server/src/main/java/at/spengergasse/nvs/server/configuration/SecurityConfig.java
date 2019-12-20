package at.spengergasse.nvs.server.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

/**
 * Used to handle Web Access Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Handles the access to certain pages
     * At the Moment it it is allowed to access all pages for testing purposes
     * @param httpSecurity httpSecurity is Edited to our needs
     * @throws Exception throws Exception if any Error occurs during httpSecurity creation
     */
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()//Cross Mapping is disabled at the moment for testing purposes (Postman)
                .authorizeRequests()
                    .antMatchers("/users/register",
                                             "/users/login",
                                             "/**").permitAll() // For Testing purposes enabled at the moment!
                    .anyRequest().authenticated()
                .and()
                    .logout()
                .and()
                    .exceptionHandling()
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }
}
