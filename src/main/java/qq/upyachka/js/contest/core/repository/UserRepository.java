package qq.upyachka.js.contest.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qq.upyachka.js.contest.core.model.user.UserDo;

/**
 * Repository of {@link UserDo}.
 * Created on 24.02.17.
 * @author upyachka.
 */
public interface UserRepository extends JpaRepository<UserDo, Long> {
    /**
     * Returns user by specified {@link UserDo#username}.
     * @param username value in {@link UserDo#username}.
     * @return found user.
     */
    UserDo findByUsername(String username);
}

