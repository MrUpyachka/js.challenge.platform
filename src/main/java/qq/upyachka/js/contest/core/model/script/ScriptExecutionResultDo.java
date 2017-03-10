package qq.upyachka.js.contest.core.model.script;

import qq.upyachka.js.contest.core.model.user.UserDo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Contains results of script execution and measurements.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Entity
@Table(name = ScriptExecutionResultDo.SCRIPT_RUN_TABLE)
public class ScriptExecutionResultDo implements Serializable, ScriptExecutionResult<UserDo, TaskDo, StringLobDo> {

    /** Name of table to store script execution results. */
    public static final String SCRIPT_RUN_TABLE = "script_run";

    /** Name of reference to get task which this execution related to. */
    public static final String TASK_ID = "task_id";

    /** Unique identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Script body. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLobDo body;

    /** Output of script execution. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLobDo output;

    /** Value returned by script. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLobDo result;

    /** Average execution time in nanoseconds. */
    private Long executionTimeInNanoseconds;

    /** Minimum execution time in nanoseconds. */
    private Long minExecutionTimeInNanoseconds;

    /** Cause of execution error. */
    private Throwable errorCause;

    /** Time of execution start. */
    private Date startTime;

    /** Sign which means that execution successfully finished with required output result. */
    private Boolean succeeded;

    /** Reference to script-owner. */
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserDo owner;

    /** Reference to task which this execution related to. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TASK_ID, nullable = false)
    private TaskDo task;

    @Override
    public TaskDo getTask() {
        return task;
    }

    @Override
    public void setTask(TaskDo task) {
        this.task = task;
    }

    @Override
    public UserDo getOwner() {
        return owner;
    }

    @Override
    public void setOwner(UserDo owner) {
        this.owner = owner;
    }

    @Override
    public StringLobDo getBody() {
        return body;
    }

    @Override
    public void setBody(StringLobDo body) {
        this.body = body;
    }

    @Override
    public StringLobDo getOutput() {
        return output;
    }

    @Override
    public void setOutput(StringLobDo output) {
        this.output = output;
    }

    @Override
    public StringLobDo getResult() {
        return result;
    }

    @Override
    public void setResult(StringLobDo result) {
        this.result = result;
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
    public Boolean getSucceeded() {
        return succeeded;
    }

    @Override
    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
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
