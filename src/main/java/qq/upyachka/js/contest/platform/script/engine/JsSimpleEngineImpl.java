package qq.upyachka.js.contest.platform.script.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import static qq.upyachka.js.contest.platform.configuration.constants.ConfigurationConst.JS_ENGINE;
import static qq.upyachka.js.contest.platform.configuration.constants.ConfigurationConst.RHINO_NAME;

/**
 * Implementation of {@link SimpleEngine} for JS.<br>
 * Created on 10.03.17.
 * @author upyachka.
 */
@Component(JS_ENGINE)
public class JsSimpleEngineImpl implements SimpleEngine {

    @Autowired
    @Qualifier(RHINO_NAME)
    private ScriptEngine engine;

    @Override
    public Object eval(String script) throws ScriptException {
        try {
            return engine.eval(script);
        } catch (Exception e) {
            throw new ScriptException(e);
        }
    }
}
