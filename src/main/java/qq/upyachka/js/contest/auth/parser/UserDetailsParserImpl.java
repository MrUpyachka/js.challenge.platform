package qq.upyachka.js.contest.auth.parser;

import com.google.common.collect.Sets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import qq.upyachka.js.contest.core.model.Role;
import qq.upyachka.js.contest.core.model.User;

import java.util.Set;

/**
 * Implementation of {@link UserDetailsParser}.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Component
public class UserDetailsParserImpl implements UserDetailsParser {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(UserDetailsParserImpl.class);

    @Override
    public UserDetails parse(User source) {
        Set<GrantedAuthority> authorities = Sets.newHashSet();
        for (Role role : source.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("User {} has granted authorities: {}", source.getUsername(), authorities);
        }
        return new org.springframework.security.core.userdetails.User(source.getUsername(), source.getPassword(),
                                                                      authorities);
    }
}
