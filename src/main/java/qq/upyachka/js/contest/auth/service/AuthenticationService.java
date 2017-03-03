package qq.upyachka.js.contest.auth.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

/**
 * Authentication interface for users.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Service
public interface AuthenticationService {
    /**
     * Tries to login with specified credentials.
     * @param username name of user.
     * @param password pass phrase.
     * @throws BadCredentialsException in case of wrong credentials.
     */
    void login(String username, String password) throws BadCredentialsException;
}
