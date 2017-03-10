package qq.upyachka.js.contest.auth.service;

import qq.upyachka.js.contest.core.model.user.UserDo;

/**
 * Provides registration functionality.
 * Created on 24.02.17.
 * @author upyachka.
 */
public interface RegistrationService {
    /**
     * Registers userDo with specified information.
     * @param userDo userDo-information.
     */
    void register(UserDo userDo);

    /**
     * Checks that specified username already occupied.
     * @param username identifier of user.
     * @return true if name already occupied, false in opposite case.
     */
    boolean isRegistered(String username);
}
