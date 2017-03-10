package qq.upyachka.js.contest.core.dto;

import qq.upyachka.js.contest.core.model.script.Task;
import qq.upyachka.js.contest.core.model.script.TaskDo;

import java.io.Serializable;

/**
 * DTO implementation of {@link Task}.
 * Created on 04.03.17.
 * @author upyachka.
 */
public class TaskDto implements Task<String>, Serializable {

    /** Duplicate of {@link TaskDo#id}. */
    private Long id;

    /** Duplicate of {@link TaskDo#name}. */
    private String name;

    /** Duplicate of {@link TaskDo#output}. */
    private String output;

    /** Duplicate of {@link TaskDo#description}. */
    private String description;

    /** Duplicate of {@link TaskDo#preconditionScript}. */
    private String preconditionScript;

    /** Duplicate of {@link TaskDo#outputScript}. */
    private String outputScript;

    /** Duplicate of {@link TaskDo#testIterationsNumber}. */
    private Long testIterationsNumber;

    /** Duplicate of {@link TaskDo#engine}. */
    private String engine;

    @Override
    public String getEngine() {
        return engine;
    }

    @Override
    public void setEngine(String engine) {
        this.engine = engine;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getPreconditionScript() {
        return preconditionScript;
    }

    @Override
    public void setPreconditionScript(String preconditionScript) {
        this.preconditionScript = preconditionScript;
    }

    @Override
    public String getOutputScript() {
        return outputScript;
    }

    @Override
    public void setOutputScript(String outputScript) {
        this.outputScript = outputScript;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getTestIterationsNumber() {
        return testIterationsNumber;
    }

    @Override
    public void setTestIterationsNumber(Long testIterationsNumber) {
        this.testIterationsNumber = testIterationsNumber;
    }
}
