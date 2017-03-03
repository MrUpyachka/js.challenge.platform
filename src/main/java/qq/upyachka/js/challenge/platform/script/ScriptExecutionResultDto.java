package qq.upyachka.js.challenge.platform.script;

import qq.upyachka.js.challenge.core.model.ScriptExecutionResult;

import java.util.Date;

/**
 * Created on 02.03.17.
 * @author upyachka.
 */
public class ScriptExecutionResultDto implements ScriptExecutionResult {

    /** Unique identifier. */
    private Long id;

    /** Name of user who started execution. */
    private String username;

    /** Script body */
    private String body;

    /** Output of script execution. */
    private String output;

    /** Value returned by script. */
    private String result;

    /** Average execution time in nanoseconds. */
    private Long executionTimeInNanoseconds;

    /** Minimum execution time in nanoseconds. */
    private Long minExecutionTimeInNanoseconds;

    /** Cause of execution error. */
    private Throwable errorCause;

    /** Time of execution start. */
    private Date startTime;

    /** @return value of {@link #username}. */
    public String getUsername() {
        return username;
    }

    /** @param username value for {@link #username}. */
    public void setUsername(String username) {
        this.username = username;
    }

    /** @return value of {@link #id}. */
    public Long getId() {
        return id;
    }

    /** @param id value for {@link #id}. */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return value of {@link #body}. */
    public String getBody() {
        return body;
    }

    /** @param body value for {@link #body}. */
    public void setBody(String body) {
        this.body = body;
    }

    /** @return value of {@link #startTime}. */
    public Date getStartTime() {
        return startTime;
    }

    /** @param startTime value for {@link #startTime}. */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /** @return value of {@link #minExecutionTimeInNanoseconds}. */
    public Long getMinExecutionTimeInNanoseconds() {
        return minExecutionTimeInNanoseconds;
    }

    /** @param minExecutionTimeInNanoseconds value for {@link #minExecutionTimeInNanoseconds}. */
    public void setMinExecutionTimeInNanoseconds(Long minExecutionTimeInNanoseconds) {
        this.minExecutionTimeInNanoseconds = minExecutionTimeInNanoseconds;
    }

    /** @return value of {@link #output}. */
    public String getOutput() {
        return output;
    }

    /** @param output value for {@link #output}. */
    public void setOutput(String output) {
        this.output = output;
    }

    /** @return value of {@link #result}. */
    public String getResult() {
        return result;
    }

    /** @param result value for {@link #result}. */
    public void setResult(String result) {
        this.result = result;
    }

    /** @return value of {@link #executionTimeInNanoseconds}. */
    public Long getExecutionTimeInNanoseconds() {
        return executionTimeInNanoseconds;
    }

    /** @param executionTimeInNanoseconds value for {@link #executionTimeInNanoseconds}. */
    public void setExecutionTimeInNanoseconds(Long executionTimeInNanoseconds) {
        this.executionTimeInNanoseconds = executionTimeInNanoseconds;
    }

    /** @return value of {@link #errorCause}. */
    public Throwable getErrorCause() {
        return errorCause;
    }

    /** @param errorCause value for {@link #errorCause}. */
    public void setErrorCause(Throwable errorCause) {
        this.errorCause = errorCause;
    }

    /**
     * Check that script successfully executed.
     * @return true if everything OK, false in opposite case.
     */
    public boolean isSucceeded() {
        return errorCause == null;
    }
}
