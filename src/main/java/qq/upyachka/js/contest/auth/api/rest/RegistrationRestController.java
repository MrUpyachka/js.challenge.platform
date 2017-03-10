package qq.upyachka.js.contest.auth.api.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import qq.upyachka.js.contest.auth.service.AuthenticationService;
import qq.upyachka.js.contest.auth.service.RegistrationService;
import qq.upyachka.js.contest.auth.validation.UserRegistrationValidator;
import qq.upyachka.js.contest.core.api.rest.AbstractViewNavigationController;
import qq.upyachka.js.contest.core.model.user.UserDo;

import static qq.upyachka.js.contest.auth.constants.AuthUrlConst.REGISTRATION_URL;
import static qq.upyachka.js.contest.core.model.constants.ParamConst.USER_KEY;
import static qq.upyachka.js.contest.core.model.constants.ViewConst.REGISTRATION_VIEW;

/**
 *
 * Created on 24.02.17.
 * @author upyachka.
 */
@RestController
@RequestMapping(REGISTRATION_URL)
public class RegistrationRestController extends AbstractViewNavigationController {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(RegistrationRestController.class);

    /** Service to access user authentication. */
    @Autowired
    private AuthenticationService authentication;

    /** Service to access user registration. */
    @Autowired
    private RegistrationService service;

    /** Validator of user-data. */
    @Autowired
    private UserRegistrationValidator validator;

    /**
     * End-point to start user registration.
     * @param model actual model.
     * @return registration view.
     */
    @GetMapping
    public ModelAndView registration(Model model) {
        model.addAttribute(USER_KEY, new UserDo());
        LOG.debug("Show registration view");
        return new ModelAndView(REGISTRATION_VIEW, model.asMap());
    }

    /**
     * End-point to start userDo registration.
     * @param userDo userDo data from client.
     * @param bindingResult result of validation.
     * @param model model from context.
     * @return registration view or home view.
     */
    @PostMapping
    public ModelAndView tryRegister(@ModelAttribute(USER_KEY) UserDo userDo, BindingResult bindingResult, Model model) {
        final String username = userDo.getUsername();
        LOG.debug("Try register userDo with name {}.", username);
        validator.validate(userDo, bindingResult);
        if (bindingResult.hasErrors()) {
            LOG.debug("Validation failed, stay on view.");
            return new ModelAndView(REGISTRATION_VIEW, model.asMap());
        }
        final String password = userDo.getPassword();
        service.register(userDo);
        authentication.login(username, password);
        LOG.debug("UserDo {} registered and automatically logged in.", username);
        return redirectToRoot(model);
    }
}
