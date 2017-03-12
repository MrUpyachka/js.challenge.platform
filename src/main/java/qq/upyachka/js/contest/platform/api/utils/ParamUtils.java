package qq.upyachka.js.contest.platform.api.utils;

import qq.upyachka.js.contest.core.model.constants.ParamConst;

import static qq.upyachka.js.contest.core.model.constants.ParamConst.EMPTY_ID;

/**
 * Utility class to help with params processing.<br>
 * Created on 12.03.17.
 * @author upyachka.
 */
public final class ParamUtils {

    /**
     * Checks that item selected. {@link ParamConst#EMPTY_ID} means that no item selected.
     * @param id task ID.
     * @return true if selected existing task, false in opposite case.
     */
    public static boolean isItemSelected(Long id) {
        return id != null && !EMPTY_ID.equals(id);
    }

}
