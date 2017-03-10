package qq.upyachka.js.contest.core.dto.utils;

import com.google.common.collect.Lists;
import qq.upyachka.js.contest.core.dto.ScriptExecutionResultDto;
import qq.upyachka.js.contest.core.model.script.ScriptExecutionResult;
import qq.upyachka.js.contest.core.model.script.ScriptExecutionResultDo;

import java.util.List;

import static qq.upyachka.js.contest.core.dto.utils.StringLobCopyUtils.parseStringLob;
import static qq.upyachka.js.contest.core.dto.utils.StringLobCopyUtils.wrapString;

/**
 * Utility class for copying data from {@link ScriptExecutionResultDto} to {@link ScriptExecutionResultDo} and backward.
 * Created on 02.03.17.
 * @author upyachka.
 */
public final class ScriptExecutionResultCopyUtils {

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
        parseRefContent(source, target);
        parseCommonContent(source, target);
        parseDataContent(source, target);
        return target;
    }

    /**
     * Parses references from {@link ScriptExecutionResultDo} source to {@link ScriptExecutionResultDto} target.
     * @param source
     * @param target
     */
    public static void parseRefContent(ScriptExecutionResultDo source, ScriptExecutionResultDto target) {
        target.setId(source.getId());
        target.setTask(source.getTask().getId());
        target.setTaskName(source.getTask().getName());
        target.setOwner(source.getOwner().getUsername());
    }

    /**
     * Parses common fields from {@link ScriptExecutionResult} source to the target.<br>
     * NOTE: don't copies ID of object.
     * @param source data-source.
     * @param target target to store data.
     */
    public static void parseCommonContent(ScriptExecutionResult source, ScriptExecutionResult target) {
        target.setMinExecutionTimeInNanoseconds(source.getMinExecutionTimeInNanoseconds());
        target.setExecutionTimeInNanoseconds(source.getExecutionTimeInNanoseconds());
        target.setStartTime(source.getStartTime());
        target.setErrorCause(source.getErrorCause());
        target.setSucceeded(source.getSucceeded());
    }

    /**
     * Parses result data from {@link ScriptExecutionResultDo} source to {@link ScriptExecutionResultDto} target.
     * @param source
     * @param target
     */
    public static void parseDataContent(ScriptExecutionResultDo source, ScriptExecutionResultDto target) {
        target.setBody(parseStringLob(source.getBody()));
        target.setOutput(parseStringLob(source.getOutput()));
        target.setResult(parseStringLob(source.getResult()));
    }

    /**
     * Parses result data from {@link ScriptExecutionResultDto} source to {@link ScriptExecutionResultDo} target.
     * @param source data-source.
     * @param target target to store data.
     */
    public static void parseDataContent(ScriptExecutionResultDto source, ScriptExecutionResultDo target) {
        target.setBody(wrapString(source.getBody()));
        target.setOutput(wrapString(source.getOutput()));
        target.setResult(wrapString(source.getResult()));
    }
}
