package qq.upyachka.js.contest.platform.service.impl;

import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qq.upyachka.js.contest.core.dto.TaskDto;
import qq.upyachka.js.contest.core.dto.utils.TaskCopyUtils;
import qq.upyachka.js.contest.core.model.script.TaskDo;
import qq.upyachka.js.contest.core.repository.TaskRepository;
import qq.upyachka.js.contest.platform.service.TaskService;

import java.util.List;

/**
 * Implementation of {@link TaskService}.<br>
 * Created on 05.03.17.
 * @author upyachka.
 */
@Service
public class TaskServiceImpl implements TaskService {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(TaskServiceImpl.class);

    /** Access to tasks data. */
    @Autowired
    private TaskRepository tasks;

    @Override
    public List<TaskDto> getTasks() {
        final List<TaskDo> tasksDoList = tasks.findAll();
        final List<TaskDto> tasks = Lists.newArrayList();
        for (TaskDo source : tasksDoList) {
            TaskDto task = new TaskDto();
            TaskCopyUtils.parseContent(source, task);
            tasks.add(task);
        }
        LOG.debug("Found {} tasks", tasks.size());
        return tasks;
    }

    @Override
    public TaskDto getTask(Long id) {
        TaskDto task = new TaskDto();
        final TaskDo source = tasks.findOne(id);
        TaskCopyUtils.parseContent(source, task);
        return task;
    }

    @Override
    public TaskDto saveTask(TaskDto task) {
        LOG.debug("Try to save task {}.", task.getName());
        TaskDo taskDo = new TaskDo();
        // Replace multiple line-endings caused by html inputs.
        task.setOutput(task.getOutput().replace("\r\n", "\n"));
        TaskCopyUtils.parseContent(task, taskDo);
        taskDo = tasks.save(taskDo);
        LOG.debug("Task {} saved with id {}", taskDo.getName(), taskDo.getId());
        TaskDto result = new TaskDto();
        TaskCopyUtils.parseContent(taskDo, result);
        return result;
    }
}
