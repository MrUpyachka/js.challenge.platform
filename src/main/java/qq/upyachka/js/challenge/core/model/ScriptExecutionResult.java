package qq.upyachka.js.challenge.core.model;

import java.util.Date;

/**
 * Abstract class for any object which describes script execution results.
 * Created on 02.03.17.
 * @author upyachka.
 */
public interface ScriptExecutionResult {


    /** @returns value of {@link ScriptExecutionResultDo#body}. */
    String getBody();

    /** @param {@link #body} value for {@link ScriptExecutionResultDo#body}. */
    void setBody(String body);

    /** @returns value of {@link ScriptExecutionResultDo#startTime}. */
    Date getStartTime();

    /** @param {@link #startTime} value for {@link ScriptExecutionResultDo#startTime}. */
    void setStartTime(Date startTime);

    /** @returns value of {@link ScriptExecutionResultDo#minExecutionTimeInNanoseconds}. */
    Long getMinExecutionTimeInNanoseconds();

    /** @param {@link #minExecutionTimeInNanoseconds} value for {@link ScriptExecutionResultDo#minExecutionTimeInNanoseconds}. */
    void setMinExecutionTimeInNanoseconds(Long minExecutionTimeInNanoseconds);

    /** @returns value of {@link ScriptExecutionResultDo#output}. */
    String getOutput();

    /** @param {@link #output} value for {@link ScriptExecutionResultDo#output}. */
    void setOutput(String output);

    /** @returns value of {@link ScriptExecutionResultDo#result}. */
    String getResult();

    /** @param {@link #result} value for {@link ScriptExecutionResultDo#result}. */
    void setResult(String result);

    /** @returns value of {@link ScriptExecutionResultDo#executionTimeInNanoseconds}. */
    Long getExecutionTimeInNanoseconds();

    /** @param {@link #executionTimeInNanoseconds} value for {@link ScriptExecutionResultDo#executionTimeInNanoseconds}. */
    void setExecutionTimeInNanoseconds(Long executionTimeInNanoseconds);

    /** @returns value of {@link ScriptExecutionResultDo#errorCause}. */
    Throwable getErrorCause();

    /** @param {@link #errorCause} value for {@link ScriptExecutionResultDo#errorCause}. */
    void setErrorCause(Throwable errorCause);

}
