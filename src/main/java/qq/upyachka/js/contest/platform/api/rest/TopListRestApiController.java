package qq.upyachka.js.contest.platform.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import qq.upyachka.js.contest.platform.service.TopListService;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static qq.upyachka.js.contest.core.model.constants.ParamConst.TASK_ID;
import static qq.upyachka.js.contest.core.model.constants.ParamConst.TOP_LIST;
import static qq.upyachka.js.contest.core.model.constants.ViewConst.HOME_VIEW;

/**
 * Controller for REST API to access Top list functionality.
 * Created on 03.03.17.
 * @author upyachka.
 */
@RestController
@RequestMapping(path = "/top-list",produces = "application/json; charset=UTF-8")
public class TopListRestApiController {

    /** Service to access top lists. */
    @Autowired
    private TopListService service;

    @GetMapping
    public ModelAndView showTop(@RequestParam Long taskId, Model model, HttpSession session) {
        final Map<String, Object> params = model.asMap();
        params.put(TOP_LIST, service.getTop100(taskId));
        session.setAttribute(TASK_ID, taskId);
        return new ModelAndView(HOME_VIEW, params);
    }

}
