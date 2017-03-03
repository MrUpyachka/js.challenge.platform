package qq.upyachka.js.challenge.core.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Large string entity. Script output, for example.
 * Created on 03.03.17.
 * @author upyachka.
 */
@Entity
@Table(name = "string_content")
public class StringLob {

    /** Output of script execution. */
    @Lob
    private String content;
}
