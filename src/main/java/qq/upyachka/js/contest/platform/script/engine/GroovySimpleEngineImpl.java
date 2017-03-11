package qq.upyachka.js.contest.platform.script.engine;

import groovy.lang.GroovyShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.script.ScriptException;

import static qq.upyachka.js.contest.platform.configuration.constants.ConfigurationConst.GROOVY_ENGINE;
import static qq.upyachka.js.contest.platform.configuration.constants.ConfigurationConst.GROOVY_SHELL_NAME;

/**
 * Implementation of {@link SimpleEngine} for Groovy.<br>
 * Created on 11.03.17.
 * @author upyachka.
 */
@Component(GROOVY_ENGINE)
public class GroovySimpleEngineImpl implements SimpleEngine {

    @Autowired
    @Qualifier(GROOVY_SHELL_NAME)
    private GroovyShell groovyShell;

    @Override
    public Object eval(String script) throws ScriptException {
        try {
            return groovyShell.evaluate(script);
        } catch (Exception e) {
            throw new ScriptException(e);
        }
    }
}
