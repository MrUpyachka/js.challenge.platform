package qq.upyachka.js.contest.auth.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import qq.upyachka.js.contest.auth.service.AuthenticationService;

/**
 * Implementation of {@link AuthenticationService}.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(AuthenticationServiceImpl.class);

    /** Spring authentication manager. */
    @Autowired
    private AuthenticationManager manager;

    /** Service to get user details. */
    @Autowired
    private UserDetailsService service;

    @Override
    public void login(String username, String password) throws AuthenticationException {
        LOG.debug("Try login as {}", username);
        UserDetails userDetails = service.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        manager.authenticate(usernamePasswordAuthenticationToken);
        LOG.debug("User {} authenticated.", username);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            LOG.debug("User {} successfully logged in.", username);
        } else {
            LOG.debug("Attempt to login with wrong password for {}.", username);
            throw new BadCredentialsException("User with such name and password not found");
        }
    }
}
