package qq.upyachka.js.contest.core.api.rest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static qq.upyachka.js.contest.core.constants.UrlConst.REDIRECT_URL_PREFIX;

/**
 * Base class for all controllers which uses views and redirects as return-value.
 * Created on 03.03.17.
 * @author upyachka.
 */
public abstract class AbstractViewNavigationController {
    /**
     * Sends redirect to root view with specified model.
     * @param model model to be used in context.
     * @return redirect.
     */
    protected ModelAndView redirectToRoot(Model model) {
        // TODO investigate redirect possibilities.
        return new ModelAndView(REDIRECT_URL_PREFIX, model.asMap());
    }
}
