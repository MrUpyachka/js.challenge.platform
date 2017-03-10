package qq.upyachka.js.contest.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qq.upyachka.js.contest.core.model.script.TaskDo;

/**
 * Repository of {@link TaskDo}.
 * Created on 04.03.17.
 * @author upyachka.
 */
public interface TaskRepository extends JpaRepository<TaskDo, Long> {}
