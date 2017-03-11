package qq.upyachka.js.contest.platform.service;

import qq.upyachka.js.contest.core.dto.TaskDto;
import qq.upyachka.js.contest.core.model.script.Task;

import java.util.List;

/**
 * Used to access {@link Task} related data and functionality.
 * Created on 05.03.17.
 * @author upyachka.
 */
public interface TaskService {
    /**
     * Returns list of available tasks.
     * @return tasks.
     */
    List<TaskDto> getTasks();

    /**
     * Returns task for specified id.
     * @param id task identifier.
     * @return task.
     */
    TaskDto getTask(Long id);

    /**
     * Registers new task on platform.
     * @param task task to be registered.
     * @return created or updated task.
     */
    TaskDto saveTask(TaskDto task);
}
