package qq.upyachka.js.challenge.platform.script.executor;

import qq.upyachka.js.challenge.core.model.ScriptExecutionResult;

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
    ScriptExecutionResult execute(ScriptExecutionResult script, long times);
}
