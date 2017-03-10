package qq.upyachka.js.contest.auth.service.impl;

import com.google.common.collect.Sets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qq.upyachka.js.contest.auth.service.RegistrationService;
import qq.upyachka.js.contest.core.model.user.UserDo;
import qq.upyachka.js.contest.core.repository.RoleRepository;
import qq.upyachka.js.contest.core.repository.UserRepository;

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
    public void register(UserDo userDo) {
        LOG.debug("Try register userDo {}.", userDo.getUsername());
        userDo.setPassword(passwordEncoder.encode(userDo.getPassword()));
        userDo.setRoles(Sets.newHashSet());
        users.save(userDo);
        LOG.debug("UserDo {} registered.", userDo.getUsername());
    }

    @Override
    public boolean isRegistered(String username) {
        return users.findByUsername(username) != null;
    }
}
