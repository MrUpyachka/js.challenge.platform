package qq.upyachka.js.contest.core.dto;

import qq.upyachka.js.contest.core.model.script.ScriptExecutionResult;
import qq.upyachka.js.contest.core.model.script.ScriptExecutionResultDo;

import java.util.Date;

/**
 * Created on 02.03.17.
 * @author upyachka.
 */
public class ScriptExecutionResultDto implements ScriptExecutionResult<String, Long, String> {

    /** Same as {@link ScriptExecutionResultDo#id}. */
    private Long id;

    /** Reference to {@link ScriptExecutionResultDo#owner}. */
    private String owner;

    /** Content of {@link ScriptExecutionResultDo#body}. */
    private String body;

    /** Content of {@link ScriptExecutionResultDo#output}. */
    private String output;

    /** Content of {@link ScriptExecutionResultDo#result}. */
    private String result;

    /** Same as {@link ScriptExecutionResultDo#executionTimeInNanoseconds}. */
    private Long executionTimeInNanoseconds;

    /** Same as {@link ScriptExecutionResultDo#minExecutionTimeInNanoseconds}. */
    private Long minExecutionTimeInNanoseconds;

    /** Same as {@link ScriptExecutionResultDo#errorCause}. */
    private Throwable errorCause;

    /** Same as {@link ScriptExecutionResultDo#startTime}. */
    private Date startTime;

    /** Reference to {@link ScriptExecutionResultDo#task}. */
    private Long task;

    /** Secondary reference to {@link ScriptExecutionResultDo#task}. */
    private String taskName;

    /** Same as {@link ScriptExecutionResultDo#succeeded}. */
    private Boolean succeeded;

    /** @return value of {@link #taskName}. */
    public String getTaskName() {
        return taskName;
    }

    /** @param taskName value for {@link #taskName}. */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public Boolean getSucceeded() {
        return succeeded;
    }

    @Override
    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    @Override
    public Long getTask() {
        return task;
    }

    @Override
    public void setTask(Long task) {
        this.task = task;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Long getMinExecutionTimeInNanoseconds() {
        return minExecutionTimeInNanoseconds;
    }

    @Override
    public void setMinExecutionTimeInNanoseconds(Long minExecutionTimeInNanoseconds) {
        this.minExecutionTimeInNanoseconds = minExecutionTimeInNanoseconds;
    }

    @Override
    public String getOutput() {
        return output;
    }

    @Override
    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public Long getExecutionTimeInNanoseconds() {
        return executionTimeInNanoseconds;
    }

    @Override
    public void setExecutionTimeInNanoseconds(Long executionTimeInNanoseconds) {
        this.executionTimeInNanoseconds = executionTimeInNanoseconds;
    }

    @Override
    public Throwable getErrorCause() {
        return errorCause;
    }

    @Override
    public void setErrorCause(Throwable errorCause) {
        this.errorCause = errorCause;
    }
}
