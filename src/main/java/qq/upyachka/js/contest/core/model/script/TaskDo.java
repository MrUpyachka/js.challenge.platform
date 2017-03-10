package qq.upyachka.js.contest.core.model.script;

import javax.persistence.*;

/**
 * DO implementation of {@link Task}.
 * Created on 04.03.17.
 * @author upyachka.
 */
@Entity
@Table(name = TaskDo.TASK_TABLE)
public class TaskDo implements Task<StringLobDo> {

    /** Name of table with tasks. */
    public static final String TASK_TABLE = "task";

    /** Unique identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** User-friendly identifier. */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /** Required output after script execution. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLobDo output;

    /** Description of task. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLobDo description;

    /** Precondition script for task execution. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLobDo preconditionScript;

    /** Script to be executed to return output. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StringLobDo outputScript;

    /** Number of iterations for script testing. */
    @Column(name = "test_iterations_number", nullable = false)
    private Long testIterationsNumber;

    /** Script engine name. */
    @Column(name = "engine", nullable = false)
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
    public Long getTestIterationsNumber() {
        return testIterationsNumber;
    }

    @Override
    public void setTestIterationsNumber(Long testIterationsNumber) {
        this.testIterationsNumber = testIterationsNumber;
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
    public StringLobDo getDescription() {
        return description;
    }

    @Override
    public void setDescription(StringLobDo description) {
        this.description = description;
    }

    @Override
    public StringLobDo getPreconditionScript() {
        return preconditionScript;
    }

    @Override
    public void setPreconditionScript(StringLobDo preconditionScript) {
        this.preconditionScript = preconditionScript;
    }

    @Override
    public StringLobDo getOutputScript() {
        return outputScript;
    }

    @Override
    public void setOutputScript(StringLobDo outputScript) {
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
}
