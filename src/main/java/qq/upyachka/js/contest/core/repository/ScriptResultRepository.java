package qq.upyachka.js.contest.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import qq.upyachka.js.contest.core.model.script.ScriptExecutionResultDo;

/**
 * Repository of {@link ScriptExecutionResultDo}.
 * Created on 02.03.17.
 * @author upyachka.
 */
public interface ScriptResultRepository extends JpaRepository<ScriptExecutionResultDo, Long> {

    /** Query to get list of best executions for specified tasks. */
    String TOP_LIST_QUERY = "SELECT r FROM ScriptExecutionResultDo r WHERE r.task.id = :taskId"
                            + " ORDER BY r.minExecutionTimeInNanoseconds ASC, r.startTime ASC";

    /**
     * Request to get the top of the best executions.
     * @param taskId ID of tasks.
     * @param pageable wrapper for pagination.
     * @return page reference.
     */
    @Query(TOP_LIST_QUERY)
    Page<ScriptExecutionResultDo> getTop(@Param("taskId") Long taskId, Pageable pageable);
}
