package qq.upyachka.js.challenge.core.error;

import javax.validation.constraints.NotNull;

/**
 * Exception which should be thrown by platform if its impossible to proceed with processing.
 * Created on 23.02.17.
 * @author upyachka.
 */
public class PlatformException extends Exception {
    /**
     * Constructor with required description.
     * @param description text which explain cause.
     */
    public PlatformException(@NotNull String description) {
        super(description);
    }
}
