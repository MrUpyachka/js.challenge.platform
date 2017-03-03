package qq.upyachka.js.challenge.platform.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import qq.upyachka.js.challenge.core.error.PlatformException;
import qq.upyachka.js.challenge.platform.script.ScriptExecutionResultDto;
import qq.upyachka.js.challenge.platform.service.JavaScriptExecutionService;
import qq.upyachka.js.challenge.platform.service.TopListService;
import qq.upyachka.js.challenge.platform.utils.FileUtils;

import static qq.upyachka.js.challenge.core.constants.UrlConst.REDIRECT_URL_PREFIX;
import static qq.upyachka.js.challenge.core.model.constants.ParamConst.PREVIOUS_RESULT;
import static qq.upyachka.js.challenge.core.model.constants.ParamConst.TOP_LIST;
import static qq.upyachka.js.challenge.core.model.constants.ViewConst.HOME_VIEW;

/**
 * General controller for REST API for script execution.
 * Created on 23.02.17.
 * @author upyachka.
 */
@RestController
@RequestMapping(produces = "application/json; charset=UTF-8")
public class PlatformRestApiController {

    /** Service which responds for scripts execution. */
    @Autowired
    private JavaScriptExecutionService service;

    /** Service which provides access to the top list. */
    @Autowired
    private TopListService topListService;

    /**
     * Default end-point which displays top 100 of executions.
     * @param model context model.
     * @return view with top of executions and form for new execution.
     */
    @GetMapping
    public ModelAndView runScript(Model model) {
        ScriptExecutionResultDto previousResult = (ScriptExecutionResultDto)model.asMap().get(PREVIOUS_RESULT);
        if (previousResult != null) {
            model.addAttribute(PREVIOUS_RESULT, service.getExecution(previousResult.getId()));
        }
        fillModelWithTopList(model);
        return new ModelAndView(HOME_VIEW, model.asMap());
    }

    /**
     * Saves actual top of best executions to specified model.
     * @param model model to be enriched.
     */
    private void fillModelWithTopList(Model model) {
        model.addAttribute(TOP_LIST, topListService.getTop100());
    }

    /**
     * End-point to run user script.
     * @param file
     * @param model context model.
     * @return view with top of executions and form for new execution.
     * @throws PlatformException
     */
    @PostMapping
    public ModelAndView runScript(@RequestParam MultipartFile file, Model model) throws PlatformException {
        final String script = FileUtils.readFile(file);
        final ScriptExecutionResultDto result = service.execute(script);
        model.addAttribute(PREVIOUS_RESULT, result);
        return new ModelAndView(REDIRECT_URL_PREFIX, model.asMap());
    }
}
