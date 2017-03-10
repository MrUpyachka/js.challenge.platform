package qq.upyachka.js.contest.platform.api.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import qq.upyachka.js.contest.core.api.rest.AbstractViewNavigationController;
import qq.upyachka.js.contest.core.dto.ScriptExecutionResultDto;
import qq.upyachka.js.contest.core.dto.TaskDto;
import qq.upyachka.js.contest.core.error.PlatformException;
import qq.upyachka.js.contest.platform.service.JavaScriptExecutionService;
import qq.upyachka.js.contest.platform.service.TaskService;
import qq.upyachka.js.contest.platform.service.TopListService;
import qq.upyachka.js.contest.platform.utils.FileUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

import static qq.upyachka.js.contest.core.constants.UrlConst.EXECUTION_DETAILS_VIEW_PATH;
import static qq.upyachka.js.contest.core.model.constants.ParamConst.*;
import static qq.upyachka.js.contest.core.model.constants.ViewConst.EXECUTION_DETAILS_VIEW;
import static qq.upyachka.js.contest.core.model.constants.ViewConst.HOME_VIEW;

/**
 * General controller for REST API for script execution.
 * Created on 23.02.17.
 * @author upyachka.
 */
@RestController
@RequestMapping(produces = "application/json; charset=UTF-8")
public class PlatformRestApiController extends AbstractViewNavigationController {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(PlatformRestApiController.class);

    /** Service which responds for scripts execution. */
    @Autowired
    private JavaScriptExecutionService service;

    /** Service which provides access to the top list. */
    @Autowired
    private TopListService topListService;

    /** Service which provides access to task list. */
    @Autowired
    private TaskService taskService;

    /**
     * End-point which preserves taskId in session and redirects to the view with TOP list.
     * @param taskId task identifier.
     * @param model context model.
     * @param session session of request.
     * @return redirect to view.
     */
    @GetMapping
    public ModelAndView getPage(@RequestParam(required = false) Long taskId, Model model, HttpSession session) {
        if (taskId == null) {
            taskId = (Long)session.getAttribute(TASK_ID);
        }
        final Long previousResult = (Long)session.getAttribute(PREVIOUS_RESULT);
        if (previousResult != null) {
            LOG.debug("Try update data for execution {}", previousResult);
            model.addAttribute(PREVIOUS_RESULT, service.getExecution(previousResult));
        }
        final List<TaskDto> tasks = taskService.getTasks();
        model.addAttribute(TASKS_LIST, tasks);
        if (taskId == null && tasks.size() > 0) {
            LOG.debug("No task selected in session. Set default as first in tasks.");
            taskId = tasks.get(0).getId();
        }
        if (taskId != null) {
            model.addAttribute(TOP_LIST, topListService.getTop100(taskId));
        }
        if (taskId != null) {
            session.setAttribute(TASK_ID, taskId);
            LOG.debug("Task id {} saved in session.", taskId);
        }
        return new ModelAndView(HOME_VIEW, model.asMap());
    }

    /**
     * Returns view with details about specified execution.
     * @param id execution identifier.
     * @param model context model.
     * @return view with details about execution.
     */
    @GetMapping(EXECUTION_DETAILS_VIEW_PATH)
    public ModelAndView showDetails(@RequestParam Long id, Model model) {
        model.addAttribute(EXECUTION_DETAILS, service.getExecution(id));
        return new ModelAndView(EXECUTION_DETAILS_VIEW, model.asMap());
    }

    /**
     * End-point to run user script.
     * @param file selected script file.
     * @param taskId task identifier.
     * @param model context model.
     * @param session session of request.
     * @return view with top of executions and form for new execution.
     * @throws PlatformException in case of execution errors.
     */
    // TODO exception mapper for errors handling
    @PostMapping
    public ModelAndView runScript(@RequestParam MultipartFile file, @RequestParam Long taskId, Model model,
                                  HttpSession session)
    throws PlatformException {
        final String script = FileUtils.readFile(file);
        final ScriptExecutionResultDto result = service.execute(script, taskId);
        session.setAttribute(PREVIOUS_RESULT, result.getId());
        session.setAttribute(TASK_ID, taskId);
        return redirectToRoot(model);
    }
}
