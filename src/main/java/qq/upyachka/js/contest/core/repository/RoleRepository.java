package qq.upyachka.js.contest.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qq.upyachka.js.contest.core.model.Role;

/**
 * Repository of {@link Role}.
 * Created on 24.02.17.
 * @author upyachka.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {}