package qq.upyachka.js.challenge.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qq.upyachka.js.challenge.core.model.ScriptExecutionResultDo;

/**
 * Repository of {@link ScriptExecutionResultDo}.
 * Created on 02.03.17.
 * @author upyachka.
 */
public interface ScriptResultRepository extends JpaRepository<ScriptExecutionResultDo, Long> {
    @Query("SELECT r FROM ScriptExecutionResultDo r ORDER BY r.minExecutionTimeInNanoseconds DESC, r.startTime DESC")
    Page<ScriptExecutionResultDo> getTop(Pageable pageable);
}
