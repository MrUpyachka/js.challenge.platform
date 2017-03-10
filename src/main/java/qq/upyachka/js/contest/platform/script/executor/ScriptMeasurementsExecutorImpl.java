package qq.upyachka.js.contest.platform.script.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qq.upyachka.js.contest.core.dto.ScriptExecutionResultDto;
import qq.upyachka.js.contest.core.dto.TaskDto;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import static qq.upyachka.js.contest.platform.configuration.constants.ConfigurationConst.JS_ENGINE;

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
        // TODO define engine in task.
        ScriptEngine engine = (ScriptEngine)factory.getBean(JS_ENGINE);
        long startTime;
        long stopTime;
        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        Object outputObject = null;
        Object scriptResult = null;
        final String body = script.getBody();
        final Long testIterationsNumber = task.getTestIterationsNumber();
        LOG.debug("Try test script {} times:\n{}\n:", testIterationsNumber, body);
        try {

            for (long i = 0; i < testIterationsNumber; i++) {
                engine.eval(task.getPreconditionScript());
                startTime = System.nanoTime();
                scriptResult = engine.eval(body);
                stopTime = System.nanoTime();
                LOG.debug("Iteration time: {}", stopTime - startTime);
                long runTime = stopTime - startTime;
                totalTime += runTime;
                if (runTime < minTime) {
                    minTime = runTime;
                }
            }
            outputObject = engine.eval(task.getOutputScript());
        } catch (ScriptException e) {
            LOG.error("Failed to evaluate script", e);
            script.setErrorCause(e);
        }
        final String output = convertToString(outputObject);
        script.setOutput(output);
        script.setResult(convertToString(scriptResult));
        script.setExecutionTimeInNanoseconds(totalTime / testIterationsNumber);
        script.setMinExecutionTimeInNanoseconds(minTime);
        if (task.getOutput().equals(output)) {
            script.setSucceeded(true);
            LOG.debug("Last execution output equal to task required output - success.");
        } else {
            script.setSucceeded(false);
            LOG.debug("Last execution output NOT equal to task required output - failure.");
        }
        // LOG.debug("Last execution output:\n{}", outputObject);
        return script;
    }

    /**
     * Converts on output object to {@link String}.
     * @param output script result or output.
     * @return String representation.
     */
    private String convertToString(Object output) {
        return (output != null) ? new String(output.toString()) : null;
    }
/*
    public static void main(String[] args) throws ScriptException {
        String script = "for(i=0;i<10;i++) { printChallenge(i) }";
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        long startTime;
        long stopTime;
        engine.eval(PRECONDITION_SCRIPT);
        startTime = System.currentTimeMillis();
        engine.eval(script);
        stopTime = System.currentTimeMillis();
        Object result = engine.eval(RETURN_SCRIPT);
        System.out.println(result);
    }
*/
}
