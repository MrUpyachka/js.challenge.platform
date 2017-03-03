package qq.upyachka.js.challenge.platform.script;

import com.google.common.collect.Lists;
import qq.upyachka.js.challenge.core.model.ScriptExecutionResult;
import qq.upyachka.js.challenge.core.model.ScriptExecutionResultDo;
import qq.upyachka.js.challenge.core.model.StringLob;

import java.util.List;

/**
 * Parses {@link ScriptExecutionResult} from {@link ScriptExecutionResultDo} and backward.
 * Created on 02.03.17.
 * @author upyachka.
 */
public final class ScriptExecutionResultParser {

    /**
     * Parses data to list of new objects.
     * @param source list of source objects.
     * @return list of parsed objects.
     */
    public static List<ScriptExecutionResultDto> parse(List<ScriptExecutionResultDo> source) {
        List<ScriptExecutionResultDto> result = Lists.newArrayList();
        for (ScriptExecutionResultDo item : source) {
            result.add(parse(item));
        }
        return result;
    }

    /**
     * Creates new instance and fills it with data from source.
     * @param source source data.
     * @return new object with data from source.
     */
    public static ScriptExecutionResultDto parse(ScriptExecutionResultDo source) {
        final ScriptExecutionResultDto target = new ScriptExecutionResultDto();
        target.setId(source.getId());
        target.setUsername(source.getOwner().getUsername());
        parseContent(source, target);
        return target;
    }

    /**
     * Parses result data from {@link ScriptExecutionResultDo} source to {@link ScriptExecutionResultDto} target.
     * @param source
     * @param target
     */
    public static void parseContent(ScriptExecutionResultDo source, ScriptExecutionResultDto target) {
        target.setBody(parseStringLob(source.getBody()));
        target.setMinExecutionTimeInNanoseconds(source.getMinExecutionTimeInNanoseconds());
        target.setExecutionTimeInNanoseconds(source.getExecutionTimeInNanoseconds());
        target.setOutput(parseStringLob(source.getOutput()));
        target.setResult(parseStringLob(source.getResult()));
        target.setStartTime(source.getStartTime());
        target.setErrorCause(source.getErrorCause());
    }

    /**
     * Parses content from {@link StringLob}.
     * @param source source for parsing.
     * @return content of source or null if there is no source.
     */
    public static String parseStringLob(StringLob source) {
        return (source != null) ? source.getContent() : null;
    }

    /**
     * Parses result data from {@link ScriptExecutionResultDto} source to {@link ScriptExecutionResultDo} target.
     * @param source
     * @param target
     */
    public static void parseContent(ScriptExecutionResultDto source, ScriptExecutionResultDo target) {
        target.setBody(wrapString(source.getBody()));
        target.setMinExecutionTimeInNanoseconds(source.getMinExecutionTimeInNanoseconds());
        target.setExecutionTimeInNanoseconds(source.getExecutionTimeInNanoseconds());
        target.setOutput(wrapString(source.getOutput()));
        target.setResult(wrapString(source.getResult()));
        target.setStartTime(source.getStartTime());
        target.setErrorCause(source.getErrorCause());
    }

    /**
     * Wraps {@link String} into {@link StringLob}.
     * @param source source for parsing.
     * @return wrapped string or null if source is null.
     */
    public static StringLob wrapString(String source) {
        return (source != null) ? new StringLob(source) : null;
    }
}
