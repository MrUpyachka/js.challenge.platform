package qq.upyachka.js.challenge.auth.service.impl;

import com.google.common.collect.Sets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qq.upyachka.js.challenge.core.repository.RoleRepository;
import qq.upyachka.js.challenge.core.repository.UserRepository;
import qq.upyachka.js.challenge.auth.service.RegistrationService;
import qq.upyachka.js.challenge.core.model.User;

/**
 * Implementation of {@link RegistrationService}.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(RegistrationServiceImpl.class);

    /** Access to user data. */
    @Autowired
    private UserRepository users;

    /** Access to user-roles information. */
    @Autowired
    private RoleRepository roles;

    /** Encoder for user passwords. */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(User user) {
        LOG.debug("Try register user {}.", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Sets.newHashSet());
        users.save(user);
        LOG.debug("User {} registered.", user.getUsername());
    }

    @Override
    public boolean isRegistered(String username) {
        return users.findByUsername(username) != null;
    }
}
