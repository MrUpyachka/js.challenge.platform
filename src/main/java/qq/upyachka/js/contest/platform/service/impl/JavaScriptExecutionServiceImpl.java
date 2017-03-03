package qq.upyachka.js.contest.platform.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import qq.upyachka.js.contest.core.error.PlatformException;
import qq.upyachka.js.contest.core.model.ScriptExecutionResultDo;
import qq.upyachka.js.contest.core.model.User;
import qq.upyachka.js.contest.core.repository.ScriptResultRepository;
import qq.upyachka.js.contest.core.repository.UserRepository;
import qq.upyachka.js.contest.platform.script.ScriptExecutionResultDto;
import qq.upyachka.js.contest.platform.script.ScriptExecutionResultParser;
import qq.upyachka.js.contest.platform.service.JavaScriptExecutionService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

import static qq.upyachka.js.contest.platform.api.constants.PlatformConst.ITERATIONS_NUMBER_KEY;
import static qq.upyachka.js.contest.platform.api.constants.PlatformConst.SCRIPT_KEY;
import static qq.upyachka.js.contest.platform.script.ScriptExecutionResultParser.wrapString;
import static qq.upyachka.js.contest.scheduler.SchedulerConst.*;

/**
 * Implementation of {@link JavaScriptExecutionService}.
 * Created on 23.02.17.
 * @author upyachka.
 */
@Service
public class JavaScriptExecutionServiceImpl implements JavaScriptExecutionService {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(JavaScriptExecutionServiceImpl.class);

    /** Number of iterations to test script. */
    private static final long ITERATIONS_NUMBER = 100;

    /** Factory of jobs for scheduled execution. */
    @Autowired
    @Qualifier(JS_EXECUTOR_FACTORY)
    private JobDetailFactoryBean jobFactory;

    /** Scheduler of platform. */
    @Autowired
    @Qualifier(SCHEDULER)
    private Scheduler scheduler;

    /** Access to executions data. */
    @Autowired
    private ScriptResultRepository executions;

    /** Access to user data. */
    @Autowired
    private UserRepository users;

    @Override
    public ScriptExecutionResultDto execute(String script) throws PlatformException {
        if (Strings.isNullOrEmpty(script)) {
            LOG.debug("Nothing to execute. Prevent processing.");
            throw new PlatformException("Nothing to execute.");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        ScriptExecutionResultDo result = new ScriptExecutionResultDo();
        User user = users.findByUsername(username);
        result.setOwner(user);
        result.setBody(wrapString(script));
        result.setStartTime(new Date());
        ScriptExecutionResultDo saved = executions.save(result);
        result.setId(saved.getId());
        scheduleScriptExecution(result);
        return ScriptExecutionResultParser.parse(result);
    }

    /**
     * Schedules script execution.
     * @param result prepared structure to handle results of script exception.
     */
    private void scheduleScriptExecution(ScriptExecutionResultDo result) {
        final Map<String, Object> data = Maps.newHashMap();
        data.put(SCRIPT_KEY, result.getId());
        data.put(ITERATIONS_NUMBER_KEY, ITERATIONS_NUMBER);
        jobFactory.setJobDataAsMap(data);
        final String jobName = getJobNameForScript(result.getId());
        jobFactory.setName(jobName);
        jobFactory.setGroup(JOB_GROUP);
        LOG.debug("Try to schedule execution of {}", jobName);
        try {
            scheduler.scheduleJob(jobFactory.getObject(), getTriggerForJob(jobName));
            LOG.debug("Job {} successfully scheduled.", jobName);
        } catch (SchedulerException e) {
            LOG.error("Failed to schedule Job", e);
        }
    }

    /**
     * Returns name for job depends on script.
     * @param id script data ID.
     * @return name for job.
     */
    private String getJobNameForScript(Long id) {
        return new StringBuilder(JOB_NAME_PREFIX).append('_').append(Long.toString(id)).toString();
    }

    /**
     * Returns trigger for job specified by name;
     * @param jobName name of job.
     * @return trigger to be used to start job.
     */
    private Trigger getTriggerForJob(String jobName) {
        return TriggerBuilder.newTrigger().withIdentity(jobName, JOB_GROUP).startNow().build();
    }

    @Override
    @Transactional
    public ScriptExecutionResultDto getExecution(long id) {
        LOG.debug("Try to get executions results for {}", id);
        return ScriptExecutionResultParser.parse(executions.getOne(id));
    }
}
