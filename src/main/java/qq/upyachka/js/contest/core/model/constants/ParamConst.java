package qq.upyachka.js.contest.core.model.constants;

/**
 * Contains view-model constants.
 * Created on 23.02.17.
 * @author upyachka.
 */
public final class ParamConst {

    /** Key of previous execution result in model or its ID in session context. */
    public static final String PREVIOUS_RESULT = "previousResult";

    /** Key of selected task ID in session context. */
    public static final String TASK_ID = "taskId";

    /** Key of execution details in model. */
    public static final String EXECUTION_DETAILS = "executionDetails";

    /** Key to store last error in model. */
    public static final String ERROR = "error";

    /** Key to store message in model. */
    public static final String MESSAGE = "message";

    /** Key of user data in model. */
    public static final String USER_KEY = "USER";

    /** Key to get top list of executions. */
    public static final String TOP_LIST = "topList";

    /** Key to get tasks list from model. */
    public static final String TASKS_LIST = "tasksList";

    /** Key to get task details from model. */
    public static final String TASK_KEY = "TASK";

    /** Default sign of empty ID. */
    public static final Long EMPTY_ID = Long.valueOf(-1);
}
