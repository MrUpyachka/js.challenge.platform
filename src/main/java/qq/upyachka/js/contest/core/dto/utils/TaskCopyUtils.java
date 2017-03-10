package qq.upyachka.js.contest.core.dto.utils;

import qq.upyachka.js.contest.core.dto.TaskDto;
import qq.upyachka.js.contest.core.model.script.Task;
import qq.upyachka.js.contest.core.model.script.TaskDo;

import static qq.upyachka.js.contest.core.dto.utils.StringLobCopyUtils.parseStringLob;
import static qq.upyachka.js.contest.core.dto.utils.StringLobCopyUtils.wrapString;

/**
 * Utility class for copying of data from {@link TaskDo} to {@link TaskDto} and backward.
 * Created on 04.03.17.
 * @author upyachka.
 */
public final class TaskCopyUtils {

    /**
     * Copies data from {@link TaskDo} source to {@link TaskDto} target.
     * @param source data-source.
     * @param target target to store data.
     */
    public static void parseContent(TaskDo source, TaskDto target) {
        copy(source, target);
        target.setOutput(parseStringLob(source.getOutput()));
        target.setDescription(parseStringLob(source.getDescription()));
        target.setOutputScript(parseStringLob(source.getOutputScript()));
        target.setPreconditionScript(parseStringLob(source.getPreconditionScript()));
    }

    /**
     * Copies data from {@link Task} source to the same type target.
     * @param source data-source.
     * @param target target to store data.
     * @param <S> type parameter of source task.
     * @param <T> type parameter of target task.
     */
    private static <S, T> void copy(Task<S> source, Task<T> target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setTestIterationsNumber(source.getTestIterationsNumber());
        target.setEngine(source.getEngine());
    }

    /**
     * Copies data from {@link TaskDto} source to {@link TaskDo} target.
     * @param source data-source.
     * @param target target to store data.
     */
    public static void parseContent(TaskDto source, TaskDo target) {
        copy(source, target);
        target.setOutput(wrapString(source.getOutput()));
        target.setDescription(wrapString(source.getDescription()));
        target.setOutputScript(wrapString(source.getOutputScript()));
        target.setPreconditionScript(wrapString(source.getPreconditionScript()));
    }
}
