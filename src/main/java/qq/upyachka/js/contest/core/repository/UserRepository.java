package qq.upyachka.js.contest.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qq.upyachka.js.contest.core.model.User;

/**
 * Repository of {@link User}.
 * Created on 24.02.17.
 * @author upyachka.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Returns user by specified {@link User#username}.
     * @param username value in {@link User#username}.
     * @return found user.
     */
    User findByUsername(String username);
}

