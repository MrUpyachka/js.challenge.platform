package qq.upyachka.js.contest.platform.script.engine;

import org.springframework.stereotype.Component;

import javax.script.ScriptException;

import static qq.upyachka.js.contest.platform.configuration.constants.ConfigurationConst.PYTHON_ENGINE;

/**
 * Implementation of {@link SimpleEngine} for Python.<br>
 * Created on 10.03.17.
 * @author upyachka.
 */
@Component(PYTHON_ENGINE)
public class PythonSimpleEngineImpl implements SimpleEngine {

/*
    @Autowired
    @Qualifier(JYTHON_NAME)
    private PythonInterpreter engine;
*/

    @Override
    public Object eval(String script) throws ScriptException {
        return null; // engine.eval(script);
    }
}
