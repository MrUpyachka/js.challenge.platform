package qq.upyachka.js.contest.core.model.script;

import qq.upyachka.js.contest.core.model.HasId;

/**
 * Represents contest task.<br>
 * Created on 04.03.17.
 * @param <D> text data type.
 * @author upyachka.
 */
public interface Task<D> extends HasId<Long> {

    /**
     * Getter of field. Pair for {@link #setName(String)}.
     * @return user-friendly identifier.
     */
    String getName();

    /**
     * Setter for field. Pair for {@link #getName()}.
     * @param name user-friendly identifier.
     */
    void setName(String name);

    /**
     * Getter of field. Pair for {@link #setTestIterationsNumber(Long)}.
     * @return number of iterations for script testing.
     */
    Long getTestIterationsNumber();

    /**
     * Setter for field. Pair for {@link #getTestIterationsNumber()}.
     * @param testIterationsNumber number of iterations for script testing.
     */
    void setTestIterationsNumber(Long testIterationsNumber);

    /**
     * Getter of field. Pair for {@link #setEngine(String)}.
     * @return script engine name in platform.
     */
    String getEngine();

    /**
     * Setter for field. Pair for {@link #getEngine()}.
     * @param engine script engine name in platform.
     */
    void setEngine(String engine);

    /**
     * Getter of field. Pair for {@link #setOutput(D)}.
     * @return required output retrieved after script execution.
     */
    D getOutput();

    /**
     * Setter for field. Pair for {@link #getOutput()}.
     * @param output required output retrieved after script execution.
     */
    void setOutput(D output);

    /**
     * Getter of field. Pair for {@link #setDescription(D)}.
     * @return description of task.
     */
    D getDescription();

    /**
     * Setter for field. Pair for {@link #getDescription()}.
     * @param description description of task.
     */
    void setDescription(D description);

    /**
     * Getter of field. Pair for {@link #setPreconditionScript(D)}.
     * @return script which defines environment for task-script execution.
     */
    D getPreconditionScript();

    /**
     * Setter for field. Pair for {@link #getPreconditionScript()}.
     * @param preconditionScript script which defines environment for task-script execution.
     */
    void setPreconditionScript(D preconditionScript);

    /**
     * Getter of field. Pair for {@link #setOutputScript(D)}.
     * @return script to be executed to retrieve task-script output.
     */
    D getOutputScript();

    /**
     * Setter for field. Pair for {@link #getOutputScript()}.
     * @param outputScript script to be executed to retrieve task-script output.
     */
    void setOutputScript(D outputScript);

}
