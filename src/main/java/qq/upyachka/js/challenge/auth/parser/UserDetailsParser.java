package qq.upyachka.js.challenge.auth.parser;

import org.springframework.security.core.userdetails.UserDetails;
import qq.upyachka.js.challenge.core.model.User;

/**
 * Parses {@link UserDetails} from {@link User}.
 * Created on 24.02.17.
 * @author upyachka.
 */
public interface UserDetailsParser {
    /**
     * Parses details from {@link User}.
     * @param source user data.
     * @return details of specified user.
     */
    UserDetails parse(User source);
}
