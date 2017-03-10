package qq.upyachka.js.contest.platform.script.engine;

import javax.script.ScriptException;

/**
 * Represents simple engine to evaluate scripts.<br>
 * Created on 10.03.17.
 * @author upyachka.
 */
public interface SimpleEngine {

    /**
     * Evaluates specified script and returns its result value.
     * @param script user-script.
     * @return result value of script.
     * @throws ScriptException from engine.
     */
    Object eval(String script) throws ScriptException;

}
