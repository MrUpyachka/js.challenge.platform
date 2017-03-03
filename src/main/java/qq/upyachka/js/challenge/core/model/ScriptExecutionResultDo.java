package qq.upyachka.js.challenge.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Contains results of script execution and measurements.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Entity
@Table(name = "script_run")
public class ScriptExecutionResultDo implements Serializable, ScriptExecutionResult {

    /** Unique identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Script body */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLob body = new StringLob();

    /** Output of script execution. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLob output = new StringLob();

    /** Value returned by script. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLob result = new StringLob();

    /** Average execution time in nanoseconds. */
    private Long executionTimeInNanoseconds;

    /** Minimum execution time in nanoseconds. */
    private Long minExecutionTimeInNanoseconds;

    /** Cause of execution error. */
    private Throwable errorCause;

    /** Time of execution start. */
    private Date startTime;

    /** Reference to script-owner. */
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    /** @return value of {@link #owner}. */
    public User getOwner() {
        return owner;
    }

    /** @param owner value for {@link #owner}. */
    public void setOwner(User owner) {
        this.owner = owner;
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
    public StringLob getBody() {
        return body;
    }

    /** @param body value for {@link #body}. */
    public void setBody(StringLob body) {
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
    public StringLob getOutput() {
        return output;
    }

    /** @param output value for {@link #output}. */
    public void setOutput(StringLob output) {
        this.output = output;
    }

    /** @return value of {@link #result}. */
    public StringLob getResult() {
        return result;
    }

    /** @param result value for {@link #result}. */
    public void setResult(StringLob result) {
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

}
