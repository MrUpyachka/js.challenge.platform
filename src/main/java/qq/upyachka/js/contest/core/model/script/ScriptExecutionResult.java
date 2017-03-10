package qq.upyachka.js.contest.core.model.script;

import qq.upyachka.js.contest.core.model.HasId;

import java.util.Date;

/**
 * Abstract class for any object which describes script execution results.<br>
 * Created on 02.03.17.<br>
 * @param <O> owner reference type.
 * @param <T> task reference type.
 * @param <D> text data content type.
 * @author upyachka.
 */
public interface ScriptExecutionResult<O, T, D> extends HasId<Long> {

    /**
     * Getter of field. Pair for {@link #setStartTime(Date)}.
     * @return time of execution start.
     */
    Date getStartTime();

    /**
     * Setter for field. Pair for {@link #getStartTime()}.
     * @param startTime time of execution start.
     */
    void setStartTime(Date startTime);

    /**
     * Getter of field. Pair for {@link #setMinExecutionTimeInNanoseconds(Long)}.
     * @return minimum execution time in nanoseconds.
     */
    Long getMinExecutionTimeInNanoseconds();

    /**
     * Setter for field. Pair for {@link #getMinExecutionTimeInNanoseconds()}.
     * @param minExecutionTimeInNanoseconds minimum execution time in nanoseconds.
     */
    void setMinExecutionTimeInNanoseconds(Long minExecutionTimeInNanoseconds);

    /**
     * Getter of field. Pair for {@link #setExecutionTimeInNanoseconds(Long)}.
     * @return average execution time in nanoseconds.
     */
    Long getExecutionTimeInNanoseconds();

    /**
     * Setter for field. Pair for {@link #getExecutionTimeInNanoseconds()}.
     * @param executionTimeInNanoseconds average execution time in nanoseconds.
     */
    void setExecutionTimeInNanoseconds(Long executionTimeInNanoseconds);

    /**
     * Getter of field. Pair for {@link #setErrorCause(Throwable)}.
     * @return cause of execution error.
     */
    Throwable getErrorCause();

    /**
     * Setter for field. Pair for {@link #getErrorCause()}.
     * @param errorCause cause of execution error.
     */
    void setErrorCause(Throwable errorCause);

    /**
     * Getter of field. Pair for {@link #setSucceeded(Boolean)}.
     * @return sign which means that execution successfully finished with required output result.
     */
    Boolean getSucceeded();

    /**
     * Setter for field. Pair for {@link #getSucceeded()}.
     * @param succeeded sign which means that execution successfully finished with required output result.
     */
    void setSucceeded(Boolean succeeded);

    /**
     * Getter of field. Pair for {@link #setTask(T)}.
     * @return reference to task which this execution related to.
     */
    T getTask();

    /**
     * Setter for field. Pair for {@link #getTask()}.
     * @param task which this execution related to.
     */
    void setTask(T task);

    /**
     * Getter of field. Pair for {@link #setOwner(O)}.
     * @return reference to script-owner.
     */
    O getOwner();

    /**
     * Setter for field. Pair for {@link #getOwner()}.
     * @param owner reference to script-owner.
     */
    void setOwner(O owner);

    /**
     * Getter of field. Pair for {@link #setBody(D)}.
     * @return script body.
     */
    D getBody();

    /**
     * Setter for field. Pair for {@link #getBody()}.
     * @param body script body.
     */
    void setBody(D body);

    /**
     * Getter of field. Pair for {@link #setOutput(D)}.
     * @return output of script execution.
     */
    D getOutput();

    /**
     * Setter for field. Pair for {@link #getOutput()}.
     * @param output output of script execution.
     */
    void setOutput(D output);

    /**
     * Getter of field. Pair for {@link #setResult(D)}.
     * @return value returned by script.
     */
    D getResult();

    /**
     * Setter for field. Pair for {@link #getResult()}.
     * @param result value returned by script.
     */
    void setResult(D result);
}
