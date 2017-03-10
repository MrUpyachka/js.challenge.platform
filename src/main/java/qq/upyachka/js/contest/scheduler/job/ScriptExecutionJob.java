package qq.upyachka.js.contest.scheduler.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import qq.upyachka.js.contest.core.dto.ScriptExecutionResultDto;
import qq.upyachka.js.contest.core.dto.TaskDto;
import qq.upyachka.js.contest.core.dto.utils.TaskCopyUtils;
import qq.upyachka.js.contest.core.model.script.ScriptExecutionResultDo;
import qq.upyachka.js.contest.core.repository.ScriptResultRepository;
import qq.upyachka.js.contest.platform.script.executor.ScriptMeasurementsExecutor;

import static qq.upyachka.js.contest.core.dto.utils.ScriptExecutionResultCopyUtils.*;
import static qq.upyachka.js.contest.platform.api.constants.PlatformConst.ITERATIONS_NUMBER_KEY;
import static qq.upyachka.js.contest.platform.api.constants.PlatformConst.SCRIPT_KEY;

/**
 * Created on 01.03.17.
 * @author upyachka.
 */
@Component
@DisallowConcurrentExecution
public class ScriptExecutionJob extends QuartzJobBean {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(ScriptExecutionJob.class);

    /** Executor for JS. */
    @Autowired
    private ScriptMeasurementsExecutor executor;

    /** Access to executions data. */
    @Autowired
    private ScriptResultRepository executions;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        final JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        final Long scriptId = (Long)dataMap.get(SCRIPT_KEY);
        final Long iterationsNumber = (Long)dataMap.get(ITERATIONS_NUMBER_KEY);
        LOG.debug("Try handle script with ID {}", scriptId);
        ScriptExecutionResultDo execution = executions.findOne(scriptId);
        TaskDto task = new TaskDto();
        TaskCopyUtils.parseContent(execution.getTask(), task);
        ScriptExecutionResultDto result = executor.execute(parse(execution), task);
        parseDataContent(result, execution);
        parseCommonContent(result, execution);
        if (result.getErrorCause() == null) {
            LOG.debug("Script successfully executed. Output validation: {}.",
                      (result.getSucceeded()) ? "succeeded" : "failed");
        } else {
            LOG.debug("Script {} of {} execution failed", execution.getId(), execution.getOwner().getUsername());
        }
        executions.save(execution);
        LOG.debug("Script execution results saved to DB.");
    }
}
