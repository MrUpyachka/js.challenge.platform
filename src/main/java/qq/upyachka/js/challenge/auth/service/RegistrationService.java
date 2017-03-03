package qq.upyachka.js.challenge.auth.service;

import qq.upyachka.js.challenge.core.model.User;

/**
 * Provides registration functionality.
 * Created on 24.02.17.
 * @author upyachka.
 */
public interface RegistrationService {
    /**
     * Registers user with specified information.
     * @param user user-information.
     */
    void register(User user);

    /**
     * Checks that specified username already occupied.
     * @param username identifier of user.
     * @return true if name already occupied, false in opposite case.
     */
    boolean isRegistered(String username);
}
