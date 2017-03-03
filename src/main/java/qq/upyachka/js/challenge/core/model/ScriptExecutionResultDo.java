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
    @Column(name = "body", length = Integer.MAX_VALUE)
    private String body;

    /** Output of script execution. */
    @Column(name = "output")
    private String output;

    /** Value returned by script. */
    @Column(name = "result")
    private String result;

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

    /** @returns value of {@link #owner}. */
    public User getOwner() {
        return owner;
    }

    /** @param {@link #owner} value for {@link #owner}. */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /** @returns value of {@link #id}. */
    public Long getId() {
        return id;
    }

    /** @param {@link #id} value for {@link #id}. */
    public void setId(Long id) {
        this.id = id;
    }

    /** @returns value of {@link #body}. */
    public String getBody() {
        return body;
    }

    /** @param {@link #body} value for {@link #body}. */
    public void setBody(String body) {
        this.body = body;
    }

    /** @returns value of {@link #startTime}. */
    public Date getStartTime() {
        return startTime;
    }

    /** @param {@link #startTime} value for {@link #startTime}. */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /** @returns value of {@link #minExecutionTimeInNanoseconds}. */
    public Long getMinExecutionTimeInNanoseconds() {
        return minExecutionTimeInNanoseconds;
    }

    /** @param {@link #minExecutionTimeInNanoseconds} value for {@link #minExecutionTimeInNanoseconds}. */
    public void setMinExecutionTimeInNanoseconds(Long minExecutionTimeInNanoseconds) {
        this.minExecutionTimeInNanoseconds = minExecutionTimeInNanoseconds;
    }

    /** @returns value of {@link #output}. */
    public String getOutput() {
        return output;
    }

    /** @param {@link #output} value for {@link #output}. */
    public void setOutput(String output) {
        this.output = output;
    }

    /** @returns value of {@link #result}. */
    public String getResult() {
        return result;
    }

    /** @param {@link #result} value for {@link #result}. */
    public void setResult(String result) {
        this.result = result;
    }

    /** @returns value of {@link #executionTimeInNanoseconds}. */
    public Long getExecutionTimeInNanoseconds() {
        return executionTimeInNanoseconds;
    }

    /** @param {@link #executionTimeInNanoseconds} value for {@link #executionTimeInNanoseconds}. */
    public void setExecutionTimeInNanoseconds(Long executionTimeInNanoseconds) {
        this.executionTimeInNanoseconds = executionTimeInNanoseconds;
    }

    /** @returns value of {@link #errorCause}. */
    public Throwable getErrorCause() {
        return errorCause;
    }

    /** @param {@link #errorCause} value for {@link #errorCause}. */
    public void setErrorCause(Throwable errorCause) {
        this.errorCause = errorCause;
    }

}
