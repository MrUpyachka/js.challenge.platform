package qq.upyachka.js.contest.auth.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qq.upyachka.js.contest.auth.parser.UserDetailsParser;
import qq.upyachka.js.contest.core.model.User;
import qq.upyachka.js.contest.core.repository.UserRepository;

/**
 * Implementation of {@link UserDetailsService}.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(UserDetailsServiceImpl.class);

    /** Access to user data. */
    @Autowired
    private UserRepository users;

    /** Parser of {@link UserDetails}. */
    @Autowired
    private UserDetailsParser parser;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = users.findByUsername(username);
        if (user == null) {
            LOG.debug("User with name {} not found.", username);
            throw new UsernameNotFoundException(String.format("User %s", username));
        }
        return parser.parse(user);
    }
}
