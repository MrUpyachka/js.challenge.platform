package qq.upyachka.js.contest.platform.service;

import qq.upyachka.js.contest.core.dto.ScriptExecutionResultDto;
import qq.upyachka.js.contest.core.error.PlatformException;
import qq.upyachka.js.contest.core.model.script.Task;

/**
 * Used to execute JavaScript and retrieve results.
 * Created on 23.02.17.
 * @author upyachka.
 */
public interface JavaScriptExecutionService {

    /**
     * Executes script from specified {@link String}.
     * @param script JavaScript to execute.
     * @param taskId reference to {@link Task}.
     * @return output data after evaluation.
     * @throws PlatformException if it's impossible to evaluate script.
     */
    ScriptExecutionResultDto execute(String script, Long taskId) throws PlatformException;

    /**
     * Returns actual data of execution results.
     * @param id identifier of execution.
     * @return actual execution results.
     */
    ScriptExecutionResultDto getExecution(long id);
}
