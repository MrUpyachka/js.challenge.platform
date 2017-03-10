package qq.upyachka.js.contest.auth.parser;

import org.springframework.security.core.userdetails.UserDetails;
import qq.upyachka.js.contest.core.model.user.UserDo;

/**
 * Parses {@link UserDetails} from {@link UserDo}.
 * Created on 24.02.17.
 * @author upyachka.
 */
public interface UserDetailsParser {
    /**
     * Parses details from {@link UserDo}.
     * @param source user data.
     * @return details of specified user.
     */
    UserDetails parse(UserDo source);
}
