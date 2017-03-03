package qq.upyachka.js.contest.auth.api.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import qq.upyachka.js.contest.auth.service.AuthenticationService;
import qq.upyachka.js.contest.core.model.User;

import static qq.upyachka.js.contest.auth.constants.AuthUrlConst.LOGIN_URL;
import static qq.upyachka.js.contest.core.model.constants.ParamConst.*;
import static qq.upyachka.js.contest.core.model.constants.ViewConst.LOGIN_VIEW;

/**
 * REST API for user authentication.
 * Created on 24.02.17.
 * @author upyachka.
 */
@RestController
@RequestMapping(LOGIN_URL)
public class LoginRestController {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(LoginRestController.class);

    /** Service to access user authentication. */
    @Autowired
    private AuthenticationService authentication;

    /**
     * End-point to show login view.
     * @return login view.
     */
    @GetMapping
    public ModelAndView login(Model model) {
        model.addAttribute(USER_KEY, new User());
        LOG.debug("Show login view.");
        return new ModelAndView(LOGIN_VIEW, model.asMap());
    }

    /**
     * End-point for logging in.
     * @param user user credentials.
     * @param model actual model.
     * @param error error of previous login to attempt.
     * @param logout token that means logout by user.
     * @return login view.
     */
    @PostMapping
    public ModelAndView login(@ModelAttribute(USER_KEY) User user,
                              Model model, String error, String logout) {
        LOG.debug("Try login as {}.", user.getUsername());
        if (error != null) {
            LOG.debug("Add error to attributes: {}", error);
            model.addAttribute(ERROR, error);
        }
        if (logout != null) {
            LOG.debug("Add message to attributes: {}", logout);
            model.addAttribute(MESSAGE, logout);
        }
        return new ModelAndView(LOGIN_VIEW, model.asMap());
    }
}
