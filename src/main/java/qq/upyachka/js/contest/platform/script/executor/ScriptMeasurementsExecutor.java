package qq.upyachka.js.contest.platform.script.executor;

import qq.upyachka.js.contest.core.dto.ScriptExecutionResultDto;
import qq.upyachka.js.contest.core.dto.TaskDto;

/**
 * Executes specified script with time measurements.
 * Created on 24.02.17.
 * @author upyachka.
 */
public interface ScriptMeasurementsExecutor {

    /**
     * Executes script and returns results.
     * @param script container of script data.
     * @param task task details.
     * @return object with results.
     */
    ScriptExecutionResultDto execute(ScriptExecutionResultDto script, TaskDto task);
}
