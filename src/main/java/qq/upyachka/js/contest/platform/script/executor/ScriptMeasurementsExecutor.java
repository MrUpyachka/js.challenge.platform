package qq.upyachka.js.contest.platform.script.executor;

import qq.upyachka.js.contest.platform.script.ScriptExecutionResultDto;

/**
 * Executes specified script with time measurements.
 * Created on 24.02.17.
 * @author upyachka.
 */
public interface ScriptMeasurementsExecutor {

    /**
     * Executes script and returns results.
     * @param script container of script data.
     * @param times number of repeats to calculate average time.
     * @return object with results.
     */
    ScriptExecutionResultDto execute(ScriptExecutionResultDto script, long times);
}
