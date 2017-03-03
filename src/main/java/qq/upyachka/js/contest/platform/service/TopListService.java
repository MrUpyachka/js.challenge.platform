package qq.upyachka.js.contest.platform.service;

import qq.upyachka.js.contest.platform.script.ScriptExecutionResultDto;

import java.util.List;

/**
 * Used to access functionality of top list of executions.
 * Created on 03.03.17.
 * @author upyachka.
 */
public interface TopListService {
    /**
     * Returns Top100 list of script executions.
     * @return list of best 100 executions.
     */
    List<ScriptExecutionResultDto> getTop100();
}
