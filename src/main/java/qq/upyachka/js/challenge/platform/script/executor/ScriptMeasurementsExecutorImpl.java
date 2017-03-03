package qq.upyachka.js.challenge.platform.script.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qq.upyachka.js.challenge.platform.script.ScriptExecutionResultDto;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import static qq.upyachka.js.challenge.platform.configuration.constants.ConfigurationConst.JS_ENGINE;

/**
 * Implementation of {@link ScriptMeasurementsExecutor}.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Component
public class ScriptMeasurementsExecutorImpl implements ScriptMeasurementsExecutor {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(ScriptMeasurementsExecutorImpl.class);

    /** Script which initializes environment. */
    private static final String PRECONDITION_SCRIPT =
            "var output = \"\"; function printChallenge(text) { output += text + \"\\n\" }";

    /** Script which returns output. */
    private static final String RETURN_SCRIPT = "output";

    /** Factory to instantiate script engine. */
    @Autowired
    private BeanFactory factory;

    @Override
    public ScriptExecutionResultDto execute(ScriptExecutionResultDto script, long times) {
        ScriptEngine engine = (ScriptEngine)factory.getBean(JS_ENGINE);
        long startTime;
        long stopTime;
        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        Object output = null;
        Object scriptResult = null;
        final String body = script.getBody();
        LOG.debug("Try test script:\n{}\n{} times:", body, times);
        try {
            for (long i = 0; i < times; i++) {
                engine.eval(PRECONDITION_SCRIPT);
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
            output = engine.eval(RETURN_SCRIPT);
        } catch (ScriptException e) {
            LOG.error("Failed to evaluate script", e);
            script.setErrorCause(e);
        }
        script.setOutput(convertToString(output));
        script.setResult(convertToString(scriptResult));
        script.setExecutionTimeInNanoseconds(totalTime / times);
        script.setMinExecutionTimeInNanoseconds(minTime);
        LOG.debug("Last execution output:\n{}", output);
        return script;
    }

    /**
     * Converts on output object to {@link String}.
     * @param output script result or output.
     * @return String representation.
     */
    private String convertToString(Object output) {
        return (output != null) ? output.toString() : null;
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
