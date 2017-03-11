package qq.upyachka.js.contest.platform.script.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qq.upyachka.js.contest.core.dto.ScriptExecutionResultDto;
import qq.upyachka.js.contest.core.dto.TaskDto;
import qq.upyachka.js.contest.platform.script.engine.SimpleEngine;

import javax.script.ScriptException;

/**
 * Implementation of {@link ScriptMeasurementsExecutor}.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Component
public class ScriptMeasurementsExecutorImpl implements ScriptMeasurementsExecutor {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(ScriptMeasurementsExecutorImpl.class);

    /** Factory to instantiate script engine. */
    @Autowired
    private BeanFactory factory;

    @Override
    public ScriptExecutionResultDto execute(ScriptExecutionResultDto script, TaskDto task) {
        SimpleEngine engine = (SimpleEngine)factory.getBean(task.getEngine());
        testScriptOutput(engine, script, task);
        if (script.getSucceeded()) {
            testScriptExecutionTime(engine, script, task);
        } else {
            LOG.debug("Script not fulfils requirements, prevent testing for execution time.");
        }
        return script;
    }

    /**
     * Tests script that it returns required result.
     * @param engine engine to be used.
     * @param script script details.
     * @param task task details.
     */
    private void testScriptOutput(SimpleEngine engine, ScriptExecutionResultDto script, TaskDto task) {
        Object outputObject = null;
        Object scriptResult = null;
        final String body = script.getBody();
        LOG.debug("Try test script output:\n{}\n:", body);
        try {
            engine.eval(task.getPreconditionScript());
            scriptResult = engine.eval(body);
            outputObject = engine.eval(task.getOutputScript());
        } catch (ScriptException e) {
            LOG.error("Failed to evaluate script", e);
            fillErrorCause(script, e);
        }
        final String output = convertToString(outputObject);
        script.setOutput(output);
        script.setResult(convertToString(scriptResult));
        if (task.getOutput().equals(output) && script.getErrorCause() == null) {
            script.setSucceeded(true);
            LOG.debug("Last execution output equal to task required output - success.");
        } else {
            script.setSucceeded(false);
            LOG.debug("Last execution output NOT equal to task required output OR execution failed.");
        }
    }

    /**
     * Tests script for average and minimum execution time.
     * @param engine engine to be used.
     * @param script script details.
     * @param task task details.
     */
    private void testScriptExecutionTime(SimpleEngine engine, ScriptExecutionResultDto script, TaskDto task) {
        long startTime;
        long stopTime;
        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        final String body = script.getBody();
        final Long testIterationsNumber = task.getTestIterationsNumber();
        LOG.debug("Try test script {} times:\n{}\n:", testIterationsNumber, body);
        try {
            for (long i = 0; i < testIterationsNumber; i++) {
                engine.eval(task.getPreconditionScript());
                startTime = System.nanoTime();
                engine.eval(body);
                stopTime = System.nanoTime();
                // LOG.debug("Iteration time: {}", stopTime - startTime);
                long runTime = stopTime - startTime;
                totalTime += runTime;
                if (runTime < minTime) {
                    minTime = runTime;
                }
            }
        } catch (ScriptException e) {
            LOG.error("Failed to evaluate script", e);
            fillErrorCause(script, e);
        }
        final long executionTimeInNanoseconds = totalTime / testIterationsNumber;
        script.setExecutionTimeInNanoseconds(executionTimeInNanoseconds);
        script.setMinExecutionTimeInNanoseconds(minTime);
        LOG.debug("Script results: average time - {}, minimum time - {}", executionTimeInNanoseconds, minTime);
    }

    /**
     * Fills script details with wrapped exception data.
     * @param script details of script.
     * @param e cause.
     */
    private void fillErrorCause(ScriptExecutionResultDto script, Exception e) {
        script.setErrorCause(new Throwable(e.toString()));
    }

    /**
     * Converts on output object to {@link String}.
     * @param output script result or output.
     * @return String representation.
     */
    private String convertToString(Object output) {
        return (output != null) ? output.toString() : null;
    }
}
