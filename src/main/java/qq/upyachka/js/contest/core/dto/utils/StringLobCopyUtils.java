package qq.upyachka.js.contest.core.dto.utils;

import qq.upyachka.js.contest.core.model.script.StringLobDo;

/**
 * Utility class for copying of {@link StringLobDo} data.
 * Created on 04.03.17.
 * @author upyachka.
 */
public final class StringLobCopyUtils {

    /**
     * Parses content from {@link StringLobDo}.
     * @param source source for parsing.
     * @return content of source or null if there is no source.
     */
    public static String parseStringLob(StringLobDo source) {
        return (source != null) ? source.getContent() : null;
    }


    /**
     * Wraps {@link String} into {@link StringLobDo}.
     * @param source source for parsing.
     * @return wrapped string or null if source is null.
     */
    public static StringLobDo wrapString(String source) {
        return (source != null) ? new StringLobDo(source) : null;
    }

}
