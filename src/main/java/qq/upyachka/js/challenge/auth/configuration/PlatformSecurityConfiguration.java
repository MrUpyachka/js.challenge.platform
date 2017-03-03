package qq.upyachka.js.challenge.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static qq.upyachka.js.challenge.auth.constants.AuthUrlConst.LOGIN_URL;
import static qq.upyachka.js.challenge.auth.constants.AuthUrlConst.REGISTRATION_URL;
import static qq.upyachka.js.challenge.core.constants.UrlConst.RESOURCES_URL_PATTERN;

/**
 * Security configuration.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Configuration
@EnableWebSecurity
public class PlatformSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /** Service to get user details. */
    @Autowired
    private UserDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(RESOURCES_URL_PATTERN, REGISTRATION_URL).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(LOGIN_URL).permitAll()
                .and().logout().permitAll();
    }

    /**
     * Configures {@link AuthenticationManager} with custom implementations.
     * @param auth builder of {@link AuthenticationManager}.
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    /** Encoder to be used for password. */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
