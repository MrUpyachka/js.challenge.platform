package qq.upyachka.js.contest.core.model;

/**
 * Represents DO with unique ID.<br>
 * @param <T> type of key.
 * Created on 05.03.17.
 * @author upyachka.
 */
public interface HasId<T> {

    /** @return unique object ID. */
    T getId();

    /** @param id value unique object ID. */
    void setId(T id);

}
