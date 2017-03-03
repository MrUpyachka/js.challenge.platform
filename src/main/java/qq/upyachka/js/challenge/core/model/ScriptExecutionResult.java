package qq.upyachka.js.challenge.core.model;

import java.util.Date;

/**
 * Abstract class for any object which describes script execution results.
 * Created on 02.03.17.
 * @author upyachka.
 */
public interface ScriptExecutionResult {

    /** @return value of {@link ScriptExecutionResultDo#body}. */
    StringLob getBody();

    /** @param body value for {@link ScriptExecutionResultDo#body}. */
    void setBody(StringLob body);

    /** @return value of {@link ScriptExecutionResultDo#startTime}. */
    Date getStartTime();

    /** @param startTime value for {@link ScriptExecutionResultDo#startTime}. */
    void setStartTime(Date startTime);

    /** @return value of {@link ScriptExecutionResultDo#minExecutionTimeInNanoseconds}. */
    Long getMinExecutionTimeInNanoseconds();

    /** @param minExecutionTimeInNanoseconds value for {@link ScriptExecutionResultDo#minExecutionTimeInNanoseconds}. */
    void setMinExecutionTimeInNanoseconds(Long minExecutionTimeInNanoseconds);

    /** @return value of {@link ScriptExecutionResultDo#output}. */
    StringLob getOutput();

    /** @param output value for {@link ScriptExecutionResultDo#output}. */
    void setOutput(StringLob output);

    /** @return value of {@link ScriptExecutionResultDo#result}. */
    StringLob getResult();

    /** @param result value for {@link ScriptExecutionResultDo#result}. */
    void setResult(StringLob result);

    /** @return value of {@link ScriptExecutionResultDo#executionTimeInNanoseconds}. */
    Long getExecutionTimeInNanoseconds();

    /** @param executionTimeInNanoseconds value for {@link ScriptExecutionResultDo#executionTimeInNanoseconds}. */
    void setExecutionTimeInNanoseconds(Long executionTimeInNanoseconds);

    /** @return value of {@link ScriptExecutionResultDo#errorCause}. */
    Throwable getErrorCause();

    /** @param errorCause value for {@link ScriptExecutionResultDo#errorCause}. */
    void setErrorCause(Throwable errorCause);

}
