package qq.upyachka.js.contest.platform.api.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import qq.upyachka.js.contest.core.api.rest.AbstractViewNavigationController;
import qq.upyachka.js.contest.core.dto.TaskDto;
import qq.upyachka.js.contest.core.model.constants.ParamConst;
import qq.upyachka.js.contest.platform.service.TaskService;

import javax.servlet.http.HttpSession;

import static qq.upyachka.js.contest.core.constants.UrlConst.REDIRECT_URL_PREFIX;
import static qq.upyachka.js.contest.core.model.constants.ParamConst.*;
import static qq.upyachka.js.contest.core.model.constants.ViewConst.TASK_VIEW;

/**
 * Serves request for operations with tasks.<br>
 * Created on 08.03.17.
 * @author upyachka.
 */
@RestController
@RequestMapping(path = TASK_VIEW, produces = "application/json; charset=UTF-8")
public class TaskRestApiController extends AbstractViewNavigationController {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(TaskRestApiController.class);

    /** Service which provides access to task list. */
    @Autowired
    private TaskService taskService;

    /**
     * End-point which preserves taskId in session and redirects to the view with its details.
     * @param taskId task identifier.
     * @param model context model.
     * @param session session of request.
     * @return redirect to view.
     */
    @GetMapping
    public ModelAndView getPage(@RequestParam(required = false) Long taskId, Model model, HttpSession session) {
        handleModelAndSession(taskId, model, session);
        return new ModelAndView(TASK_VIEW, model.asMap());
    }

    /**
     * Configures session and model for specified task.
     * @param taskId task identifier.
     * @param model context model.
     * @param session session of request.
     */
    private void handleModelAndSession(Long taskId, Model model, HttpSession session) {
        if (!isTaskSelected(taskId)) {
            taskId = (Long)session.getAttribute(TASK_ID);
            LOG.debug("Task not specified. {} returned from session.", taskId);
        }
        model.addAttribute(TASKS_LIST, taskService.getTasks());
        if (isTaskSelected(taskId)) {
            model.addAttribute(TASK_KEY, taskService.getTask(taskId));
            LOG.debug("Task {} details seved to model.", taskId);
        } else {
            final TaskDto taskDto = new TaskDto();
            LOG.debug("Task not specified. Put empty dto to model.");
            model.addAttribute(TASK_KEY, taskDto);
        }
    }

    /**
     * Checks that task selected. {@link ParamConst#EMPTY_ID} means that no task selected.
     * @param taskId task ID.
     * @return true if selected existing task, false in opposite case.
     */
    private boolean isTaskSelected(Long taskId) {
        return taskId != null && !EMPTY_ID.equals(taskId);
    }

    /**
     * Fills session and model with data of specified task.
     * @param taskId task identifier.
     * @param model context model.
     * @param session session of request.
     * @return redirect to normal URL.
     */
    @GetMapping(path = "/{taskId}")
    public ModelAndView redirectToTask(@PathVariable Long taskId, Model model, HttpSession session) {
        if (isTaskSelected(taskId)) {
            LOG.debug("Task id {} saved in session, redirect to normal processing.", taskId);
            session.setAttribute(TASK_ID, taskId);
        } else {
            LOG.debug("Task id removed from session.");
            session.removeAttribute(TASK_ID);
        }
        handleModelAndSession(taskId, model, session);
        return new ModelAndView(REDIRECT_URL_PREFIX + TASK_VIEW);
    }

    /**
     * End-point to start task registration.
     * @param task data from client.
     * @param bindingResult result of validation.
     * @param model model from context.
     * @return registration view or home view.
     */
    @PostMapping
    public ModelAndView tryRegister(@ModelAttribute(TASK_KEY) TaskDto task, BindingResult bindingResult, Model model,
                                    HttpSession session) {
        final String name = task.getName();
        LOG.debug("Try register task with name {}.", name);
        if (bindingResult.hasErrors()) {
            LOG.debug("Validation failed, stay on view.");
            return new ModelAndView(TASK_VIEW, model.asMap());
        }
        if (EMPTY_ID.equals(task.getId())) {
            task.setId(null);
        }
        final TaskDto savedDto = taskService.saveTask(task);
        LOG.debug("Task {} registered.", name);
        session.setAttribute(TASK_ID, savedDto.getId());
        return new ModelAndView(REDIRECT_URL_PREFIX + TASK_VIEW);
    }
}
