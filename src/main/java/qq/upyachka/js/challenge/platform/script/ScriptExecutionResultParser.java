package qq.upyachka.js.challenge.platform.script;

import com.google.common.collect.Lists;
import qq.upyachka.js.challenge.core.model.ScriptExecutionResult;
import qq.upyachka.js.challenge.core.model.ScriptExecutionResultDo;

import java.util.List;

/**
 * Parses {@link ScriptExecutionResult} from {@link ScriptExecutionResultDo} and backward.
 * Created on 02.03.17.
 * @author upyachka.
 */
public final class ScriptExecutionResultParser {

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
     * Parses result data from source to target.
     * @param source
     * @param target
     */
    public static void parseContent(ScriptExecutionResult source, ScriptExecutionResult target) {
        target.setBody(source.getBody());
        target.setMinExecutionTimeInNanoseconds(source.getMinExecutionTimeInNanoseconds());
        target.setExecutionTimeInNanoseconds(source.getExecutionTimeInNanoseconds());
        target.setOutput(source.getOutput());
        target.setResult(source.getResult());
        target.setStartTime(source.getStartTime());
        target.setErrorCause(source.getErrorCause());
    }
}
